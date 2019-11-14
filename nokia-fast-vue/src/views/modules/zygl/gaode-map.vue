<template>
    <div id="container" class="amap-page-container">
        <!--<img src="@/icons/base_station.png">-->
        <el-amap ref="map" vid="amapDemo" :amap-manager="amapManager" :center="center" :zoom="zoom" :plugin="plugin"
                 :events="events" class="amap-demo">
        </el-amap>
        <el-form :inline="true" :model="queryParam" ref="queryParam" @keyup.enter.native="localSearch()"
                 :rules="dataRule" style="margin-left: 80px">
            <el-form-item label="   " prop="county">
                <el-select size="mini" v-model="queryParam.county"  placeholder="区县">
                    <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="   " prop="address">
                <el-input size="mini" v-model="queryParam.address" placeholder="地址" clearable
                          suffix-icon="el-icon-search"></el-input>
            </el-form-item>
            <el-form-item label="   " prop="station_name">
                <el-input size="mini" v-model="queryParam.station_name" placeholder="站点名称" clearable
                          suffix-icon="el-icon-search"></el-input>
            </el-form-item>
            <el-form-item label="   " prop="longitude">
                <el-input size="mini" type="number" v-model="queryParam.longitude" placeholder="经度"
                          clearable></el-input>
            </el-form-item>
            <el-form-item label="   " prop="latitude">
                <el-input size="mini" type="number" v-model="queryParam.latitude" placeholder="纬度" clearable></el-input>
            </el-form-item>
            <el-form-item label="   " prop="rangeValue">
                <el-select size="mini" v-model="queryParam.rangeValue"  placeholder="范围">
                    <el-option
                            v-for="item in rangeOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button size="mini" type="success" @click="localSearch()">查询</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" size="mini" @click="exportHandle()">导出</el-button>
            </el-form-item>

        </el-form>
    </div>
</template>

<script>

    import {AMapManager} from 'vue-amap';
    import {isLongitude, isLatitude} from '@/utils/validate';
    import base_station from '@/icons/base_station.png';

    let amapManager = new AMapManager();
    export default {
        components: {
            AMapManager
        },
        name: "vue-amap",
        data() {
            return {
                dataForm: {
                    county: '',
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
                    county: '',
                    station_name: '',
                    address: '',
                    longitude: '',
                    latitude: '',
                    rangeValue: '300'
                },
                options: [],
                rangeOptions: [{
                    value: '300',
                    label: '城区300米'
                }, {
                    value: '1000',
                    label: '郊区1000米'
                }, {
                    value: '2000',
                    label: '乡镇2000米'
                }],
                markerPosition: [],
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
                        o.getCity(result => {
                            // console.log(result)
                        })
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
            this.getCounty();
        },
        methods: {
            getCounty() {
                this.$http({
                    url: this.$http.adornUrl('/api/wf/getStationCounty'),
                    method: 'get'
                }).then(({data}) => {
                    if (data.countyList != null && data.code === 0) {
                        // this.dataList = data.countyList;
                        this.options.push({value:"",label:"----全选----"});
                        for (var i = 0; i < data.countyList.length; i++) {
                            this.options.push({value: data.countyList[i],label: data.countyList[i]})
                        }
                    } else {
                        this.options = [];
                    }
                });
            },
            localSearch() {
                var map = this.amapManager.getMap();
                // map.setCity(this.queryParam.county);
                map.clearMap();  //清除所有覆盖物

                this.$http({
                    url: this.$http.adornUrl('/api/wf/queryStationAddressManagement'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': "100",
                        'queryParam': this.queryParam
                    })
                }).then(({data}) => {
                    if (data.page != null && data.code === 0) {
                        this.dataList = data.page.list;
                        this.totalPage = data.page.totalCount;
                        this.dataList.forEach(element => {
                            var marker = new AMap.Marker({
                                map: map,
                                position: [element.longitude, element.latitude],
                                icon: base_station,
                                title: element.stationName,
                                animation: "AMAP_ANIMATION_DROP",
                            });
                            // map.add(marker);
                            // marker.on('click', this.openInfoWin(element, marker));
                            marker.on('click', function () {
                                var info = [];
                                info.push("<div><div><img style=\"float:left;\" src=\" https://webapi.amap.com/images/autonavi.png \"/></div> ");
                                info.push("<div style=\"padding:0px 0px 0px 4px;\"><b>" + element.stationName + "</b>");
                                info.push("经度：" + element.longitude + " 纬度: " + element.latitude);
                                info.push("地址： " + element.address);
                                info.push("共享情况： " + element.ifOperatorShare );
                                info.push("移动：" + element.mobileDeviceSystem + " 联通: " + element.unicomDeviceSystem + " 电信: " + element.telecomDeviceSystem+ "</div></div>");
                                var infoWindow = new AMap.InfoWindow({
                                    content: info.join("<br/>")  //使用默认信息窗体框样式，显示信息内容
                                });
                                infoWindow.open(map, marker.getPosition());
                            });
                            map.setFitView();
                        })
                    } else {
                        this.dataList = [];
                        this.totalPage = 0
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
            exportHandle() {
                // var responseUrl = "";
                // this.$http({
                //     url: this.$http.adornUrl('/api/wf/exportStationAddress'),
                //     method: 'get',
                //     params: this.$http.adornParams({
                //         'page': 1,
                //         'limit': 10000,
                //         'queryParam': this.queryParam
                //     })
                // }).then(function(response){
                //     console.log(response);
                //     responseUrl = response;
                // });
                // console.log(responseUrl);
                window.location.href = this.$http.adornUrl(`/api/wf/exportStationAddress?county=${this.queryParam.county}&station_name=${this.queryParam.station_name}&address=${this.queryParam.address}&longitude=${this.queryParam.longitude}&latitude=${this.queryParam.latitude}&rangeValue=${this.queryParam.rangeValue}&token=${this.$cookie.get('token')}`)
            }
        }
    }
</script>

<style scoped>
    .amap-demo {
        /*height: 640px;*/
        height: 97%;
        width: 97%;
        position: absolute;
        top: 5px;
        right: 20px;
    }
</style>