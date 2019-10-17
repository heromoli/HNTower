<template>
    <div id="container" class="amap-page-container">
        <!--<img src="@/icons/base_station.png">-->
        <el-amap ref="map" vid="amapDemo" :amap-manager="amapManager" :center="center" :zoom="zoom" :plugin="plugin"
                 :events="events" class="amap-demo" >
        </el-amap>
        <el-form :inline="true" :model="queryParam" ref="queryParam" @keyup.enter.native="localSearch()" :rules="dataRule" style="margin-left: 80px" >
            <el-form-item label="   " prop="county">
                <el-select size="mini" v-model="queryParam.county" placeholder="区县" value="" >
                    <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.label">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="   " prop="address">
                <el-input size="mini" v-model="queryParam.address" placeholder="地址" clearable suffix-icon="el-icon-search" ></el-input>
            </el-form-item>
            <el-form-item label="   " prop="station_name">
                <el-input size="mini" v-model="queryParam.station_name" placeholder="站点名称" clearable
                          suffix-icon="el-icon-search" ></el-input>
            </el-form-item>
            <el-form-item label="   " prop="longitude">
                <el-input size="mini" type="number" v-model="queryParam.longitude" placeholder="经度" clearable ></el-input>
            </el-form-item>
            <el-form-item label="   " prop="latitude">
                <el-input size="mini" type="number" v-model="queryParam.latitude" placeholder="纬度" clearable ></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="mini" type="success" @click="localSearch()" >查询</el-button>
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
                    latitude: ''
                },
                options: [],
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
                            console.log(result)
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
                        this.dataList = data.countyList;
                        for (var i = 0; i < data.countyList.length; i++) {
                            this.options.push({label: data.countyList[i]})
                        }
                    } else {
                        this.dataList = [];
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
                        'limit': this.pageSize,
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
                                info.push("地址： " + element.address + "</div></div>");
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