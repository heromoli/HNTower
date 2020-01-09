<template>
    <div>
        <div class="mod-log">
            <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
                <el-form-item>
                    <el-input size="mini" v-model="dataForm.project_number" placeholder="项目编号" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input size="mini" v-model="dataForm.project_name" placeholder="项目名称" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input size="mini" v-model="dataForm.planning_Station_Name" placeholder="规划站名"
                              clearable></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button size="mini" @click="getDataList()">查询</el-button>
                    <el-button size="mini" type="success" @click="uploadHandle()">导入规则站点明细数据</el-button>
                </el-form-item>
            </el-form>
            <el-table
                    :data="dataList"
                    border
                    v-loading="dataListLoading"
                    style="width: 100%">
                <el-table-column
                        prop="projectNumber"
                        header-align="center"
                        align="center"
                        width="130"
                        label="项目编号">
                    <!--<template slot-scope="scope">-->
                    <!--<a :href="'http://'+scope.row.solutionName"-->
                    <!--target="_blank">{{scope.row.serialNumber}}</a>-->
                    <!--</template>-->
                </el-table-column>
                <el-table-column
                        prop="projectName"
                        header-align="center"
                        align="center"
                        width="250"
                        :show-overflow-tooltip="true"
                        label="项目名称">
                </el-table-column>
                <el-table-column
                        prop="planningStationName"
                        header-align="center"
                        align="center"
                        width="200"
                        :show-overflow-tooltip="true"
                        label="规划站名">
                </el-table-column>
                <el-table-column
                        prop="longitude"
                        header-align="center"
                        align="center"
                        width="100"
                        label="经度">
                </el-table-column>
                <el-table-column
                        prop="latitude"
                        header-align="center"
                        align="center"
                        width="100"
                        label="纬度">
                </el-table-column>
                <el-table-column
                        prop="netType"
                        header-align="center"
                        align="center"
                        width="100"
                        label="网络类型">
                </el-table-column>
                <el-table-column
                        prop="demandOperator"
                        header-align="center"
                        align="center"
                        width="150"
                        label="需求运营商">
                </el-table-column>
                <el-table-column
                        prop="stationType"
                        header-align="center"
                        align="center"
                        width="80"
                        label="基站类型">
                </el-table-column>
                <el-table-column
                        prop="towerType"
                        header-align="center"
                        align="center"
                        width="130"
                        label="铁塔类型">
                </el-table-column>
                <el-table-column
                        prop="height"
                        header-align="center"
                        align="center"
                        width="50"
                        label="高度">
                </el-table-column>
                <el-table-column
                        prop="buildType"
                        header-align="center"
                        align="center"
                        width="80"
                        label="建设方式">
                </el-table-column>
                <el-table-column
                        prop="mobile"
                        header-align="center"
                        align="center"
                        width="80"
                        label="移动">
                </el-table-column>
                <el-table-column
                        prop="telecom"
                        header-align="center"
                        align="center"
                        width="80"
                        label="电信">
                </el-table-column>
                <el-table-column
                        prop="unicom"
                        header-align="center"
                        align="center"
                        width="80"
                        label="联通">
                </el-table-column>

            </el-table>
            <el-pagination
                    @size-change="sizeChangeHandle"
                    @current-change="currentChangeHandle"
                    :current-page="pageIndex"
                    :page-sizes="[5,10, 20, 50]"
                    :page-size="pageSize"
                    :total="totalPage"
                    layout="total, sizes, prev, pager, next, jumper">
            </el-pagination>
            <upload v-if="uploadVisible" ref="upload" @refreshDataList="getDataList"></upload>
        </div>
        <div id="container" class="amap-page-container">
            <!--<el-amap ref="map" vid="'amap-vue'" :amap-manager="amapManager" :center="center" :zoom="zoom"-->
                     <!--:plugin="plugin"-->
                     <!--:events="events" class="amap-demo" v-loading="dataListLoading">-->
            <!--</el-amap>-->
        </div>
    </div>
</template>

<script>
    import Upload from './tower_solution_detail_upload';
    import {AMapManager, lazyAMapApiLoaderInstance} from 'vue-amap';
    // import {isLongitude, isLatitude} from '@/utils/validate';
    import base_station from '@/icons/base_station.png';

    let amapManager = new AMapManager();
    export default {
        data() {
            return {
                dataForm: {
                    project_number: this.$route.params.serialNumber,
                    project_name: '',
                    planning_Station_Name: ''
                },
                dataList: [],
                uploadVisible: false,
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
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
            }
        },
        created() {
            this.getDataList()
        },
        components: {
            Upload
        },
        methods: {
            // 获取数据列表
            getDataList() {
                var params = this.$route.params.serialNumber;

                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/queryTowerSolutionDetail'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': this.pageSize,
                        'queryParam': this.dataForm
                    })
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataList = data.page.list;
                        this.totalPage = data.page.totalCount
                    } else {
                        this.dataList = [];
                        this.totalPage = 0
                    }
                    this.dataListLoading = false
                })
            },
            // 每页数
            sizeChangeHandle(val) {
                this.pageSize = val;
                this.pageIndex = 1;
                this.getDataList()
            },
            // 当前页
            currentChangeHandle(val) {
                this.pageIndex = val;
                this.getDataList()
            },
            // 上传文件
            uploadHandle() {
                this.uploadVisible = true;
                this.$nextTick(() => {
                    this.$refs.upload.init()
                })
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