<template>
    <div class="mod-log">
        <baidu-map class="baiduMap" id="container"
                   :zoom="12"
                   :max-zoom="25"
                   :min-zoom="10"
                   :scroll-wheel-zoom="true"
                   @ready="initMap">
            <bml-marker-clusterer :averageCenter="true">
                <bm-marker v-for="item in markerPosition" :position="item" :dragging="true"
                           animation="BMAP_ANIMATION_DROP">
                </bm-marker>
            </bml-marker-clusterer>
            <!--<bml-heatmap :data="option" :max="100" :radius="20"></bml-heatmap>-->
            <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']"
                         anchor="BMAP_ANCHOR_TOP_LEFT"></bm-map-type>
            <!--<bm-overview-map anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :isOpen="true"></bm-overview-map>  :mapStyle="{styleJson:styleJson}"-->
            <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
            <!--<bm-local-search :keyword="queryParam.address" :location="queryParam.city" :auto-viewport="true"-->
            <!--:forceLocal="true" style="display: none"></bm-local-search>-->
        </baidu-map>
        <el-form :inline="true" :model="queryParam" ref="queryParam" @keyup.enter.native="localSearch()"
                 :rules="dataRule">
            <el-form-item label="地市" prop="operatorName">
                <el-select size="mini" v-model="queryParam.city" value="海口" style="width: 100%">
                    <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.label">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="地址" prop="address">
                <el-input size="mini" v-model="queryParam.address" clearable suffix-icon="el-icon-search"></el-input>
            </el-form-item>
            <el-form-item label="站点名称" prop="station_name">
                <el-input size="mini" v-model="queryParam.station_name" clearable
                          suffix-icon="el-icon-search"></el-input>
            </el-form-item>
            <el-form-item label="经度" prop="longitude">
                <el-input size="mini" type="number" v-model="queryParam.longitude" clearable></el-input>
            </el-form-item>
            <el-form-item label="纬度" prop="latitude">
                <el-input size="mini" type="number" v-model="queryParam.latitude" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="mini" type="success" @click="localSearch()">查询</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script>
    import {drawSectorOmitAngle} from '@/utils/map'
    import {isLongitude, isLatitude} from '@/utils/validate'
    import {BmlMarkerClusterer, BmlHeatmap} from 'vue-baidu-map'

    export default {
        components: {
            BmlMarkerClusterer,
            BmlHeatmap
        },
        data() {
            return {
                dataForm: {
                    city: '',
                    station_name: '',
                    address: '',
                    longitude: '',
                    latitude: ''
                },
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                queryParam: {
                    city: '海口',
                    station_name: '',
                    address: '',
                    longitude: '',
                    latitude: ''
                },
                options: [{
                    value: '海口',
                    label: '海口',

                }, {
                    value: '琼海',
                    label: '琼海'
                }, {
                    value: '三亚',
                    label: '三亚'
                }, {
                    value: '儋州',
                    label: '儋州'
                }],
                styleJson: [
                    {
                        "featureType": "all",
                        "elementType": "geometry",
                        "styles": {
                            "hue": "#007fff",
                            "saturation": 89,
                        },
                    },
                    {
                        "featureType": "water",
                        "elementType": "all",
                        "styles": {
                            "color": "#ffffff",
                        },
                    }
                ],
                markerPosition: [],
                dataRule: {
                    longitude: [
                        {message: '经度不符合格式', trigger: 'blur'}, {validator: isLongitude, trigger: 'blur'}
                    ],
                    latitude: [
                        {message: '纬度不符合格式', trigger: 'blur'}, {validator: isLatitude, trigger: 'blur'}
                    ]
                }
            }
        },
        created() {
            // this.getDataList();
            // this.initMap();
        },
        methods: {
            initMap({BMap, map}) {
                map.centerAndZoom("海口");
                map.addEventListener("click", function (e) {
                    //取消百度地图原本的点击事件
                });
                // var bound = new BMap.Bounds(new BMap.Point(116.027143, 39.772348), new BMap.Point(116.832025, 40.126349));
                // var point = new BMap.Point(110.355883, 20.025781);
                // var marker = new BMap.Marker(point);        // 创建标注
                // map.addOverlay(marker);                     // 将标注添加到地图中
                // marker.addEventListener("click", function(type, target){
                //     console.log(type, target)
                // });
                // for (var i = 0; i < 5; i++) {
                //     this.markerPosition.push({
                //         "lng": parseFloat("110.35" + this.randomNum(4)),
                //         "lat": parseFloat("20.02" + this.randomNum(4))
                //     })
                // }

            },
            // 获取数据列表
            getDataList() {

            },
            drawPolygon(lng, lat, sDegree, map, angle) {
                let polygon = drawSectorOmitAngle(lng, lat, sDegree, map, angle);
                if (polygon == -1) {
                    this.$message.warning("输入角度有误")
                } else {
                    polygon.addEventListener("click", function (e) {
                        console.log(e.target);
                        var p = e.target;

                    });
                    map.addOverlay(polygon);
                }
            },
            drawMap({el, BMap, map}) {

            },
            randomNum(num) {
                return Math.floor((Math.random() * Math.pow(10, num)));
            },
            localSearch() {
                var map = new BMap.Map("container");
                map.centerAndZoom(this.queryParam.city, 15);
                map.enableScrollWheelZoom();
                var ls = new BMap.LocalSearch(this.queryParam.city, {
                    renderOptions: {
                        map: map,
                        autoViewport: true
                        // selectFirstResult:true
                    }
                });
                ls.search(this.queryParam.address, {forceLocal: true});

                this.$http({
                    url: this.$http.adornUrl('/api/wf/queryStationAddressInfo'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': this.pageSize,
                        'queryParam': this.queryParam
                    })
                }).then(({data}) => {
                    if (data.page != null && data.code === 0) {
                        this.dataList = data.page.list;
                        this.totalPage = data.page.totalCount;
                        for (var i = 0; i < this.dataList.length; i++) {
                            var lng = this.dataList[i].longitude;
                            var lat = this.dataList[i].latitude;
                            console.log(lng,lat);
                            var point = new BMap.Point(lng, lat);
                            var marker = new BMap.Marker(point);
                            map.addOverlay(marker);
                        }
                    } else {
                        this.dataList = [];
                        this.totalPage = 0
                    }

                });


                // ls.setSearchCompleteCallback(function (rs) {
                //     if (ls.getStatus() == BMAP_STATUS_SUCCESS) {
                //
                //         for (var i = 0; i < rs.getCurrentNumPois(); i++) {
                //             var poi = rs.getPoi(i);
                //             // console.log(poi);
                //             // var div = "<div style='padding:10px;border-bottom:1px solid #f1f1f1' οnclick=\"deliver_addr('" + poi.title + "','" + poi.address + "','" + poi.point.lng + "','" + poi.point.lat + "')\">";
                //             // div += "<div>" + poi.title + "</div>";
                //             // div += "<div>" + poi.address + "</div>";
                //             // div += "</div>";
                //             // addr_res.append(div);
                //         }
                //     }
                // });
            }
        }

    }
</script>
<style type="less">
    .baiduMap {
        height: 640px;
        width: 100%;
    }

    .anchorBL {
        display: none;
    }
</style>
