<template>
    <el-dialog
            center
            width="85%"
            :append-to-body="true"
            :lock-scroll="true"
            :modal="true"
            :close-on-click-modal="false"
            @close="closeHandle"
            :visible.sync="mapVisible">
        <div id="container" class="amap-demo">
            <el-amap ref="map" vid="'amap-vue'" :amap-manager="amapManager" :center="center" :zoom="zoom"
                     :plugin="plugin"  v-loading="dataListLoading">
            </el-amap>
        </div>
    </el-dialog>

</template>

<script>

    import {AMapManager, lazyAMapApiLoaderInstance} from 'vue-amap';
    import {isLongitude, isLatitude} from '@/utils/validate';
    import base_station from '@/icons/base_station.png';

    let amapManager = new AMapManager();
    export default {
        components: {},
        name: "tower-solution-map",
        data() {
            return {
                dataForm: {
                    county: '',
                    biz_scene: '',
                    station_name: '',
                    address: '',
                    longitude: '',
                    latitude: ''
                },
                mapVisible: false,
                dataList: [],
                dataListLoading: false,
                amapManager,
                zoom: 12,
                center: [110.317312, 20.022712],
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
        methods: {
            initMapUI(){
                let map = this.amapManager.getMap();
                AMapUI.loadUI(['misc/PointSimplifier'], function (PointSimplifier) {
                        let pointSimplifierIns1 = new PointSimplifier({
                            map: map
                        });
                    window.pointSimplifierIns = pointSimplifierIns1;
                });
            },
            init(dataList) {
                this.mapVisible = true;
                this.dataList = dataList;
                let map = this.amapManager.getMap();
                if (window.pointSimplifierIns) {
                    //清空上次查询的海量点
                    window.pointSimplifierIns.setData([]);
                }
                let pointsData = [];
                this.dataList.forEach(element => {
                    pointsData.push({
                        title: element.projectName,
                        position: [element.longitude, element.latitude],
                        address: element.planningStationName
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
                                // fillStyle: '#f08200', //颜色填充 blue
                                content: PointSimplifier.Render.Canvas.getImageContent(
                                    base_station,
                                    function onload() {
                                        pointSimplifierIns.renderLater();
                                    },
                                    function onerror(e) {
                                        alert('图片加载失败！');
                                    }),
                                width: 18,
                                //高度
                                height: 18,
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

            },
            // 弹窗关闭时
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {
                    });
            },
            closeHandle() {
                this.dataList = [];
                // this.$emit('refreshDataList')
            }

        }
    }
</script>

<style scoped>
    .amap-demo {
        height: 650px;
        width: 100%;
        margin-top: -15px;
    }
</style>