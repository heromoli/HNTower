<template>
    <div style="width: 100%">
        <el-row style="top: 33%">
            <el-col :span="6" :offset="17">
                <el-table
                        :data="dataList"
                        border
                        stripe
                        height="530"
                        size="mini"
                        style="height: auto; width: auto">
                    <el-table-column
                            fixed
                            prop="stationName"
                            header-align="center"
                            align="center"
                            :show-overflow-tooltip="true"
                            label="站址名称">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="belongOperator"
                            header-align="center"
                            align="center"
                            :show-overflow-tooltip="true"
                            label="运营商">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="alarmName"
                            header-align="center"
                            align="center"
                            :show-overflow-tooltip="true"
                            label="告警名称">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="ifTimeout"
                            header-align="center"
                            align="center"
                            label="是否超时">
                    </el-table-column>
                </el-table>
            </el-col>
        </el-row>
        <el-row class="el-row">
            <el-col :span="17">
                <div id="container" class="amap-page-container">
                    <el-amap ref="map" vid="'amap-vue'" :amap-manager="amapManager" :center="center" :zoom="zoom"
                             :plugin="plugin" class="amap-demo" v-loading="dataListLoading">
                    </el-amap>
                    <el-form :inline="true" :model="queryParam" ref="queryParam" @keyup.enter.native="massSearch()"
                             style="margin-left: 80px">
                        <el-form-item prop="county" style="width: 150px">
                            <el-select size="mini" v-model="queryParam.operator">
                                <el-option
                                        v-for="item in operatorOptions"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item prop="rangeValue" style="width: 150px">
                            <el-select size="mini" v-model="queryParam.level_value" filterable allow-create
                                       default-first-option>
                                <el-option
                                        v-for="item in levelOptions"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item prop="biz_scene" style="width: 150px">
                            <el-select size="mini" v-model="queryParam.biz_scene">
                                <el-option
                                        v-for="item in sceneOptions"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item>
                            <el-button size="mini" type="success" icon="el-icon-search" @click="linkQuery()">查询
                            </el-button>
                            <!--<el-button size="mini" type="primary" icon="el-icon-download" @click="exportHandle()">导出-->
                            <!--</el-button>-->
                            <el-button size="mini" type="primary" icon="el-icon-picture-outline" @click="cloud()">
                                卫星云图
                            </el-button>
                            <el-button size="mini" type="primary" icon="el-icon-news" @click="typhoonTrajectory()">
                                台风轨迹
                            </el-button>
                            <el-button size="mini" type="danger" @click="clearMap()">
                                清除覆盖
                            </el-button>
                        </el-form-item>
                    </el-form>
                    <el-form v-if="typhoonDateVisible" style="margin-top: 680px; margin-left: 980px">
                        <el-form-item>
                            <div class="info">
                                云图拍摄时间: {{this.typhoonDate}}
                            </div>
                        </el-form-item>
                    </el-form>
                </div>
            </el-col>
            <el-col :span="6">
                <el-table
                        :data="dataAmount"
                        border
                        stripe
                        size="mini"
                        style="height: auto; width: auto">
                    <el-table-column
                            fixed
                            prop="item"
                            header-align="center"
                            align="center"
                            label="告警名称">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="amount"
                            header-align="center"
                            align="center"
                            label="告警数量">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="amountRate"
                            header-align="center"
                            align="center"
                            label="告警占比（%）">
                    </el-table-column>
                </el-table>
            </el-col>
        </el-row>
    </div>
</template>

<script>

    import {AMapManager, lazyAMapApiLoaderInstance} from 'vue-amap';
    import {isLongitude, isLatitude} from '@/utils/validate';
    import typhoon from '@/icons/typhoon.png';
    import base_station_red from '@/icons/base_station_red.png';
    import base_station_orange from '@/icons/base_station_orange.png';
    import base_station_green from '@/icons/base_station_green.png';
    import base_station_blue from '@/icons/base_station_blue.png';
    import base_station_white from '@/icons/base_station_white.png';

    let amapManager = new AMapManager();
    export default {
        components: {},
        data() {
            return {
                dataAmount: [],
                dataList: [],
                marksList: [],
                typhoonList: [],
                typhoonDate: '',
                typhoonDateVisible: false,
                dataListLoading: false,
                pointSimplifierIns: null,
                queryParam: {
                    operator: '',
                    county: '',
                    biz_scene: '',
                    station_name: '',
                    level_value: ''
                },
                countyOptions: [],
                sceneOptions: [],
                operatorOptions: [
                    {
                        value: '',
                        label: '---运营商---'
                    },
                    {
                        value: '移动',
                        label: '移动'
                    }, {
                        value: '电信',
                        label: '电信'
                    }, {
                        value: '联通',
                        label: '联通'
                    }],
                levelOptions: [
                    {
                        value: '',
                        label: '---保障等级---'
                    }, {
                        value: 'L1',
                        label: 'L1-运营商传输节点站'
                    }, {
                        value: 'L2',
                        label: 'L2-党政军重保站'
                    }, {
                        value: 'L3',
                        label: 'L3-运营商重保站'
                    }, {
                        value: 'L4',
                        label: 'L4-运营商高等级站'
                    }, {
                        value: 'L5',
                        label: 'L5-普通站址'
                    }],
                amapManager,
                zoom: 9,
                center: [109.724, 19.22],
                plugin: ['ToolBar',
                    {
                        pName: 'MapType',
                        defaultType: 0,
                        showTraffic: false,
                        showRoad: false
                    }, 'Scale'
                ]
            };
        },
        mounted() {
            setInterval(() => {
                this.linkQuery()
            }, 180000);

            setInterval(() => {
                this.getAlarmAmount()
            }, 600000);
        },
        created() {
            this.getAlarmAmount();
            this.getAlarmList();
            this.getBizScene();
            this.scroll();
        },
        beforeDestroy() {
            clearInterval(this.timer);
        },
        methods: {
            change() {
                this.dataList.push(this.dataList[0]);//把第一条数据插入数组最有一条
                this.dataList.shift();//删除数组中第一条数据
            },
            scroll() {
                setInterval(this.change, 3000);//每三秒执行一次插入删除操作
            },
            linkQuery() {
                this.massSearch();
                this.getAlarmAmount();
            },
            getCounty() {
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getStationCounty'),
                    method: 'get'
                }).then(({data}) => {
                    if (data.countyList != null && data.code === 0) {
                        this.countyOptions.push({value: "", label: "---全部市县---"});
                        for (var i = 0; i < data.countyList.length; i++) {
                            this.countyOptions.push({value: data.countyList[i], label: data.countyList[i]})
                        }
                    } else {
                        this.countyOptions = [];
                    }
                });
            },
            getBizScene() {
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getBizScene'),
                    method: 'get'
                }).then(({data}) => {
                    if (data.sceneList != null && data.code === 0) {
                        this.sceneOptions.push({value: "", label: "---全部场景---"});
                        for (var i = 0; i < data.sceneList.length; i++) {
                            this.sceneOptions.push({value: data.sceneList[i], label: data.sceneList[i]})
                        }
                    } else {
                        this.sceneOptions = [];
                    }
                });
            },
            // 告警分类数量
            getAlarmAmount() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/disaster/selectAlarmTypeAmount'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'queryParam': this.queryParam
                    })
                }).then(({data}) => {
                    if (data.amountList != null && data.code === 0) {
                        this.dataAmount = data.amountList;
                    } else {
                        this.dataAmount = [];
                    }
                    this.dataListLoading = false
                })
            },
            //告警详情列表
            getAlarmList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/disaster/queryAmm'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'queryParam': this.queryParam
                    })
                }).then(({data}) => {
                    if (data.list != null && data.code === 0) {
                        this.dataList = data.list;
                    } else {
                        this.dataList = [];
                    }
                    this.dataListLoading = false
                })
            },
            //告警站点标记
            massSearch() {
                let map = this.amapManager.getMap();

                if (window.pointSimplifierIns) {
                    //清空上次查询的海量点
                    window.pointSimplifierIns.setData([]);
                }
                let pointsData = [];
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/disaster/queryAmm'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'queryParam': this.queryParam
                    })
                }).then(({data}) => {
                    if (data.list != null && data.code === 0) {
                        this.marksList = data.list;
                        this.marksList.forEach(element => {
                            pointsData.push({
                                stationName: element.stationName,
                                operator: element.belongOperator,
                                position: [element.longitude, element.latitude],
                                alarmName: element.alarmName,
                                level: element.stationSecurityLevel
                            })
                        });

                        lazyAMapApiLoaderInstance.load().then(() => {
                            // let iconGroup = [base_station_red, base_station_orange, base_station_blue, base_station_green, base_station_white];
                            let colorGroup = [
                                '#7030a0',
                                '#fa0000',
                                '#ff9a07',
                                '#f3ee0e',
                                '#7af366'
                            ];
                            AMapUI.loadUI(['misc/PointSimplifier'], function (PointSimplifier) {
                                if (!PointSimplifier.supportCanvas) {
                                    alert('当前环境不支持 Canvas,请使用IE9以上浏览器！');
                                    return;
                                }
                                let pointSimplifierIns = new PointSimplifier({
                                    map: map,
                                    // zIndex: 200, //绘制用图层的叠加顺序值,选高一点的
                                    autoSetFitView: false,  //不自动调整地图视野以适合全部点
                                    compareDataItem: function (a, b, aIndex, bIndex) {
                                        //数据源中靠后的元素优先，index大的排到前面去
                                        return aIndex > bIndex ? -1 : 1;
                                    },
                                    getPosition: function (dataItem) {
                                        //返回数据项的经纬度，AMap.LngLat实例或者经纬度数组
                                        return dataItem.position;
                                    },
                                    getHoverTitle: function (dataItem, idx) {
                                        //返回数据项的Title信息，鼠标hover时显示
                                        return '' + dataItem.stationName;
                                    },
                                    renderConstructor: PointSimplifier.Render.Canvas.GroupStyleRender,
                                    renderOptions: {
                                        pointStyle: {
                                            fillStyle: '#000000',
                                            width: 16,
                                            height: 16
                                        },
                                        getGroupId: function (item, idx) {
                                            if (item.level == null) {
                                                return 4
                                            } else {
                                                return item.level.slice(1, 2) - 1;
                                            }
                                        },
                                        groupStyleOptions: function (gid) {
                                            let size = 14;
                                            return {
                                                pointStyle: {
                                                    fillStyle: colorGroup[gid],
                                                    // content: PointSimplifier.Render.Canvas.getImageContent(iconGroup[gid],
                                                    //     function onLoad() {
                                                    //         pointSimplifierIns.renderLater();
                                                    //     },
                                                    //     function onError(e) {
                                                    //         alert('图片加载失败！');
                                                    //     }),
                                                    width: size,
                                                    height: size,
                                                    offset: ['-50%', '-50%'],
                                                },
                                                pointHardcoreStyle: {
                                                    width: size - 2,
                                                    height: size - 2
                                                }
                                            };
                                        },
                                        hoverTitleStyle: {
                                            position: 'top'
                                        },
                                    },
                                });

                                window.pointSimplifierIns = pointSimplifierIns;
                                pointSimplifierIns.on('pointClick', function (event, point) {
                                    let info = [];
                                    info.push("<div style=\"padding:0px 0px 0px 4px;\"><b>" + point.data.stationName + "</b>");
                                    info.push("坐标：" + point.data.position);
                                    info.push("站点名称： " + point.data.stationName);
                                    info.push("告警名称： " + point.data.alarmName);
                                    info.push("保障等级： " + point.data.level);
                                    let infoWindow = new AMap.InfoWindow({
                                        content: info.join("<br/>")  //使用默认信息窗体框样式，显示信息内容
                                    });
                                    infoWindow.open(map, point.data.position);
                                });
                                pointSimplifierIns.setData(pointsData);
                            });
                        });
                    }
                    this.dataListLoading = false
                });
            },
            exportHandle() {
                window.location.href = this.$http.adornUrl(`/api/zhzygl/exportStationAddress?county=${this.queryParam.county}&station_name=${this.queryParam.station_name}&address=${this.queryParam.address}&longitude=${this.queryParam.longitude}&latitude=${this.queryParam.latitude}&rangeValue=${this.queryParam.rangeValue}&biz_scene=${this.queryParam.biz_scene}&token=${this.$cookie.get('token')}`)
            },
            cloud() {  //卫星云图
                let map = this.amapManager.getMap();
                this.$http({
                    url: this.$http.adornUrl('/api/weather/clouds_list'),
                    method: 'get',
                }).then(({data}) => {
                    if (data.json != null && data.code === 0) {
                        // console.log(data.json);
                        this.typhoonDateVisible = true;
                        this.typhoonDate = data.json.formatTime;
                        let imageLayer0 = new AMap.ImageLayer({
                            map: map,
                            visible: true,
                            opacity: 0.5,
                            url: data.json.imglist[0],
                            bounds: new AMap.Bounds(
                                [69.916992, 34.99054], [104.974365, 55.040614]
                            ),
                            zooms: [3, 18]
                        });
                        let imageLayer1 = new AMap.ImageLayer({
                            map: map,
                            visible: true,
                            opacity: 0.5,
                            url: data.json.imglist[1],
                            bounds: new AMap.Bounds(
                                [69.916992, 14.934170], [104.974365, 34.99054]
                            ),
                            zooms: [3, 18]
                        });
                        let imageLayer2 = new AMap.ImageLayer({
                            map: map,
                            visible: true,
                            opacity: 0.5,
                            url: data.json.imglist[2],
                            bounds: new AMap.Bounds(
                                [104.974365, 34.99054], [140.042725, 55.040614]
                            ),
                            zooms: [3, 18]
                        });

                        let imageLayer3 = new AMap.ImageLayer({
                            map: map,
                            visible: true,
                            opacity: 0.5,
                            url: data.json.imglist[3],
                            bounds: new AMap.Bounds(
                                [104.974365, 14.934170], [140.042725, 34.99054]
                            ),
                            zooms: [3, 18]
                        });
                        // imageLayer.show();
                        map.add(imageLayer0);
                        map.add(imageLayer1);
                        map.add(imageLayer2);
                        map.add(imageLayer3);
                    }
                });
            },
            clearMap() { //清除标记
                this.typhoonDateVisible = false;
                let map = this.amapManager.getMap();
                map.clearMap();
                if (window.pointSimplifierIns) {
                    //清空上次查询的海量点
                    window.pointSimplifierIns.setData([]);
                }
                let layerList = map.getLayers();
                // console.log(layerList);

                layerList.forEach(element => {
                    if (element.CLASS_NAME == "AMap.ImageLayer") {
                        map.remove(element);
                    }
                })
            },
            typhoonTrajectory() {  //台风轨迹
                let map = this.amapManager.getMap();
                map.clearMap();
                this.$http({
                    url: this.$http.adornUrl('/api/weather/typhoon_list'),
                    method: 'get',
                }).then(({data}) => {
                    if (data.json != null && data.code === 0) {
                        this.typhoonList = data.json.typhoonList;
                        this.typhoonList.forEach(element => {
                            this.$http({
                                url: this.$http.adornUrl('/api/weather/typhoon_info'),
                                method: 'get',
                                params: this.$http.adornParams({
                                    'id': element[0]
                                })
                            }).then(({data}) => {
                                let pointPath = [];
                                data.json.typhoon[8].forEach(item => {
                                    let typhoonMarker = new AMap.Marker({
                                        map: map,
                                        offset: new AMap.Pixel(-8, -8),  //图标基准位置在右上角，要根据大小调整到正中间
                                        position: [item[4], item[5]],
                                        icon: typhoon,
                                        title: item[12][1]
                                    });
                                    typhoonMarker.on('click', function () {
                                        let info = [];
                                        info.push("<div style=\"padding:0px 0px 0px 5px;\"><b>" + data.json.typhoon[2] + "(" + data.json.typhoon[1] + ") 状态：" + data.json.typhoon[7] + " </b>");
                                        info.push("中心经度：" + item[4] + " 纬度：" + item[5]);
                                        info.push("到达时间：" + item[1]);
                                        info.push("风速：" + item[7] + "米/秒");
                                        info.push("中心气压：" + item[6] + "(百帕)");
                                        info.push("未来移速：" + item[9] + "公里/小时");
                                        info.push("</div></div>");
                                        let infoWindow = new AMap.InfoWindow({
                                            content: info.join("<br/>"), //使用默认信息窗体框样式，显示信息内容
                                            anchor: 'top-center'
                                        });
                                        infoWindow.open(map, typhoonMarker.getPosition());
                                    });
                                    map.add(typhoonMarker);
                                    pointPath.push([item[4], item[5]]);
                                    let polyline = new AMap.Polyline({
                                        map: map,
                                        path: pointPath,
                                        isOutline: false,
                                        borderWeight: 2,
                                        strokeColor: "#ff9a07",
                                        strokeOpacity: 1,
                                        strokeWeight: 1,
                                        strokeStyle: "solid",
                                        lineJoin: 'round',
                                        lineCap: 'round'
                                    });
                                    map.add(polyline);
                                })
                            });
                        })
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .amap-demo {
        height: 94%;
        width: 70%;
        position: absolute;
    }

    .el-row {
        position: absolute;
        width: 100%;
        height: 99%;
    }

    .info {
        font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
        line-height: 5;
        font-size: 16px;
        font-weight: 600;
        color: rgb(250, 0, 0);
    }

</style>