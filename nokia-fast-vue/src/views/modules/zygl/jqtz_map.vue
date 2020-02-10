<template>
    <div id="container" class="amap-demo amap-page-container">
        <!--<img src="@/icons/base_station.png">-->
        <el-amap ref="map" vid="'amap-vue'" :amap-manager="amapManager" :center="center" :zoom="zoom" :plugin="plugin"
                 :events="events" class="amap-demo" v-loading="dataListLoading">
        </el-amap>
        <el-form :inline="true" :model="queryParam" ref="queryParam" @keyup.enter.native="massSearch()"
                 :rules="dataRule" style="margin-left: 80px">
            <el-form-item label=" " prop="county">
                <el-select size="mini" v-model="queryParam.project_name" placeholder="项目名称" >
                    <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item style="">
                <el-button size="small" type="success" @click="massSearch()">查询</el-button>
            </el-form-item>
            <el-form-item>
                <!--<el-button size="small" type="primary"  @click="exportHandle()">导出</el-button>-->
            </el-form-item>
        </el-form>
    </div>
</template>

<script>

    import {AMapManager, lazyAMapApiLoaderInstance} from 'vue-amap';
    import {isLongitude, isLatitude} from '@/utils/validate';
    import base_station from '@/icons/base_station.png';

    let amapManager = new AMapManager();
    export default {
        components: {},
        name: "vue-amap",
        data() {
            return {
                dataForm: {
                    project_name: '',
                    longitude: '',
                    latitude: ''
                },
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
                queryParam: {
                    project_name: '',
                    longitude: '',
                    latitude: ''
                },
                options: [],
                dataRule: {
                    longitude: [
                        {message: '经度不符合格式', trigger: 'blur'}, {validator: isLongitude, trigger: 'blur'}
                    ],
                    latitude: [
                        {message: '纬度不符合格式', trigger: 'blur'}, {validator: isLatitude, trigger: 'blur'}
                    ]
                },
                amapManager,
                zoom: 12,
                center: [110.317312, 20.022712],
                events: {
                    init: (o) => {
                        lazyAMapApiLoaderInstance.load().then(() => {
                            // console.log(o);
                            // o.getCity(result => { })
                            // this.massMarksSearch();
                        });
                    },
                    'moveend': () => {
                    },
                    'zoomchange': () => {
                    },
                    'click': (e) => {
                    }
                },
                plugin: ['ToolBar', {
                    pName: 'MapType',
                    defaultType: 0,
                    events: {
                        init(o) {

                        }
                    }
                }]
            };
        },
        created() {
            this.selectProjectName();
        },
        methods: {
            selectProjectName() {
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/selectProjectName'),
                    method: 'get'
                }).then(({data}) => {
                    if (data.projectNameList != null && data.code === 0) {
                        // this.options.push({value: "", label: "---全部市县---"});
                        for (var i = 0; i < data.projectNameList.length; i++) {
                            this.options.push({value: data.projectNameList[i], label: data.projectNameList[i]})
                        }
                    } else {
                        this.options = [];
                    }
                });
            },
            fewSearch() {
                var map = this.amapManager.getMap();
                map.clearMap();  //清除所有覆盖物

                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/queryStationAddressManagement'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': "200",
                        'queryParam': this.queryParam
                    })
                }).then(({data}) => {
                    if (data.page != null && data.code === 0) {
                        this.dataList = data.page.list;
                        this.dataList.forEach(element => {
                            var marker = new AMap.Marker({
                                map: map,
                                position: [element.longitude, element.latitude],
                                icon: base_station,
                                title: element.stationName,
                                animation: "AMAP_ANIMATION_DROP",
                            });
                            // map.add(marker);
                            marker.on('click', function () {
                                var info = [];
                                info.push("<div><div><img style=\"float:left;\" src=\" https://webapi.amap.com/images/autonavi.png \"/></div> ");
                                info.push("<div style=\"padding:0px 0px 0px 4px;\"><b>" + element.stationName + "</b>");
                                info.push("经度：" + element.longitude + " 纬度: " + element.latitude);
                                info.push("地址： " + element.address);
                                info.push("共享情况： " + element.ifOperatorShare);
                                info.push("移动：" + element.mobileDeviceSystem + " 联通: " + element.unicomDeviceSystem + " 电信: " + element.telecomDeviceSystem + "</div></div>");
                                var infoWindow = new AMap.InfoWindow({
                                    content: info.join("<br/>")  //使用默认信息窗体框样式，显示信息内容
                                });
                                infoWindow.open(map, marker.getPosition());
                            });
                            map.setFitView();
                        });

                    } else {
                        this.dataList = [];
                    }
                });

                if (this.queryParam.latitude != '' && this.queryParam.longitude != '') {
                    var circle = new AMap.Circle({
                        center: [this.queryParam.longitude, this.queryParam.latitude],
                        radius: this.queryParam.rangeValue, //半径
                        borderWeight: 3,
                        strokeColor: "#FF33FF",
                        strokeOpacity: 1,
                        strokeWeight: 6,
                        // strokeOpacity: 0.2,
                        fillOpacity: 0.4,
                        strokeStyle: 'dashed',
                        strokeDasharray: [10, 10],
                        // 线样式还支持 'dashed'
                        fillColor: '#1791fc',
                        zIndex: 50,
                    });
                    circle.setMap(map);
                    map.setFitView();
                }
            },
            massSearch() {
                var map = this.amapManager.getMap();
                map.clearMap();  //清除所有覆盖物
                if (window.pointSimplifierIns) {
                    //清空上次查询的海量点
                    window.pointSimplifierIns.setData([]);
                }

                var pointsData = [];
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/wf/queryPlanningStationDetail'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': "50000",
                        'queryParam': this.queryParam
                    })
                }).then(({data}) => {
                    if (data.page != null && data.code === 0) {
                        this.dataList = data.page.list;
                        this.dataList.forEach(element => {
                            pointsData.push({
                                title: element.planningStationName,
                                icon: base_station,
                                position: [element.longitude, element.latitude]
                            })
                        });

                        AMapUI.loadUI(['misc/PointSimplifier'], function (PointSimplifier) {
                            if (!PointSimplifier.supportCanvas) {
                                alert('当前环境不支持 Canvas,请使用IE9以上浏览器！');
                                return;
                            }
                            let pointSimplifierIns = new PointSimplifier({
                                map: map,
                                // zIndex: 200, //绘制用图层的叠加顺序值,选高一点的
                                // data : {},
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
                                    return '' + dataItem.title;
                                },
                                renderOptions: {
                                    pointStyle: {
                                        fillStyle: 'blue', //颜色填充 blue
                                        // content: PointSimplifier.Render.Canvas.getImageContent(base_station,
                                        //     function onload() {
                                        //         pointSimplifierIns.renderLater();
                                        //     },
                                        //     function onerror(e) {
                                        //         alert('图片加载失败！');
                                        //     }),
                                        width: 12,
                                        //高度
                                        height: 12,
                                    },
                                    hoverTitleStyle: {
                                        position: 'top'
                                    },
                                },
                            });


                            window.pointSimplifierIns = pointSimplifierIns;

                            pointSimplifierIns.setData(pointsData);
                            pointSimplifierIns.on('pointClick', function (event, point) {
                                let info = [];
                                info.push("<div><div><img style=\"float:left;\" src=\" https://webapi.amap.com/images/autonavi.png \"/></div> ");
                                info.push("<div style=\"padding:0px 0px 0px 4px;\"><b>" + point.data.title + "</b>");
                                info.push("坐标：" + point.data.position);
                                info.push("地址： " + point.data.address);
                                info.push("共享情况： " + point.data.operatorShare);

                                let infoWindow = new AMap.InfoWindow({
                                    content: info.join("<br/>")  //使用默认信息窗体框样式，显示信息内容
                                });
                                infoWindow.open(map, point.data.position);
                            });

                        });
                    }
                    this.dataListLoading = false
                });

                if (this.queryParam.latitude != '' && this.queryParam.longitude != '') {
                    var circle = new AMap.Circle({
                        center: [this.queryParam.longitude, this.queryParam.latitude],
                        radius: this.queryParam.rangeValue, //半径
                        borderWeight: 3,
                        strokeColor: "#FF33FF",
                        strokeOpacity: 1,
                        strokeWeight: 6,
                        // strokeOpacity: 0.2,
                        fillOpacity: 0.4,
                        strokeStyle: 'dashed',
                        strokeDasharray: [10, 10],
                        // 线样式还支持 'dashed'
                        fillColor: '#1791fc',
                        zIndex: 50,
                    });
                    circle.setMap(map);
                    map.setFitView();
                }

            },
            exportHandle() {
                window.location.href = this.$http.adornUrl(`/api/zhzygl/exportStationAddress?county=${this.queryParam.county}&station_name=${this.queryParam.station_name}&address=${this.queryParam.address}&longitude=${this.queryParam.longitude}&latitude=${this.queryParam.latitude}&rangeValue=${this.queryParam.rangeValue}&biz_scene=${this.queryParam.biz_scene}&token=${this.$cookie.get('token')}`)
            },
        }
    }
</script>

<style scoped>
    .amap-demo {
        /*height: 640px;*/
        height: 99%;
        width: 99%;
        position: absolute;
        /*top: 5px;*/
        /*right: 10px;*/
        /*left: 15px;*/
    }
</style>