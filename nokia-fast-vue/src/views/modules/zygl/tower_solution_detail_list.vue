<template>
    <div>
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()" style="margin-top:-15px">
            <el-form-item>
                <el-input size="small" v-model="dataForm.project_number" placeholder="项目编号" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-input size="small" v-model="dataForm.project_name" placeholder="项目名称" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-input size="small" v-model="dataForm.planning_Station_Name" placeholder="规划站名"
                          clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="small" @click="getDataList()">查询</el-button>
                <el-button size="small" type="success" @click="uploadHandle()">导入规则站点明细数据</el-button>
                <el-button size="small" type="primary" @click="drawMarkerHandle()">一键标点</el-button>
                <el-button size="small" @click="clearAllMarkers()">清除标点</el-button>
            </el-form-item>
        </el-form>
        <el-table
                :data="dataList"
                size="small"
                border
                stripe
                v-loading="dataListLoading"
                style="width: 100%;margin-top:-15px"
                @selection-change="handleSelectionChange">
            <el-table-column
                    fixed="left"
                    type="selection"
                    width="40">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="projectNumber"
                    header-align="center"
                    align="center"
                    width="130"
                    label="项目编号">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="projectName"
                    header-align="center"
                    align="center"
                    width="220"
                    :show-overflow-tooltip="true"
                    label="项目名称">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="planningStationName"
                    header-align="center"
                    align="center"
                    width="250"
                    :show-overflow-tooltip="true"
                    label="规划站名">
                <!--<template slot-scope="scope">-->
                    <!--<el-button type="text" size="small"-->
                               <!--@click.native="drawMarker(scope.row.planningStationName,scope.row.longitude,scope.row.latitude)">-->
                        <!--{{scope.row.planningStationName}}-->
                    <!--</el-button>-->
                <!--</template>-->
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="longitude"
                    header-align="center"
                    align="center"
                    width="100"
                    label="经度">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="latitude"
                    header-align="center"
                    align="center"
                    width="100"
                    label="纬度">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="netType"
                    header-align="center"
                    align="center"
                    width="90"
                    label="网络类型">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="demandOperator"
                    header-align="center"
                    align="center"
                    width="150"
                    label="需求运营商">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="stationType"
                    header-align="center"
                    align="center"
                    width="80"
                    label="基站类型">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="towerType"
                    header-align="center"
                    align="center"
                    width="100"
                    label="铁塔类型">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="height"
                    header-align="center"
                    align="center"
                    width="50"
                    label="高度">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="buildType"
                    header-align="center"
                    align="center"
                    width="80"
                    label="建设方式">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="mobile"
                    header-align="center"
                    align="center"
                    width="80"
                    label="移动">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="telecom"
                    header-align="center"
                    align="center"
                    width="80"
                    label="电信">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="unicom"
                    header-align="center"
                    align="center"
                    width="80"
                    label="联通">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    prop="remarks"
                    header-align="center"
                    align="center"

                    label="备注">
            </el-table-column>


        </el-table>
        <upload v-if="uploadVisible" ref="upload" @refreshDataList="getDataList"></upload>
        <towerMap v-if="mapVisible" ref="towerMap" @refreshDataList="drawMarkerHandle"></towerMap>
    </div>
</template>

<script>
    import Upload from './tower_solution_detail_upload';
    import towerMap from './tower_solution_map';

    export default {
        data() {
            return {
                dataForm: {
                    project_number: this.$route.params.serialNumber,
                    project_name: '',
                    planning_Station_Name: ''
                },
                dataList: [],
                multipleSelection: [],
                uploadVisible: false,
                mapVisible: false,
                dataListLoading: false
            }
        },
        created() {
            this.getDataList()
        },
        components: {
            Upload,
            towerMap
        },
        methods: {
            handleSelectionChange(val) {
                // console.log(val);
                this.multipleSelection = val;
            },
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/queryTowerSolutionDetailList'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': this.pageSize,
                        'queryParam': this.dataForm
                    })
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataList = data.list;
                        // this.totalPage = data.page.totalCount
                    } else {
                        this.dataList = [];
                        // this.totalPage = 0
                    }
                    this.dataListLoading = false
                })
            },
            // 上传文件
            uploadHandle() {
                this.uploadVisible = true;
                this.$nextTick(() => {
                    this.$refs.upload.init()
                })
            },
            drawMarkerHandle() {
                this.mapVisible = true;
                this.$nextTick(() => {
                    this.$refs.towerMap.init(this.multipleSelection);
                });
            }
        }
    }
</script>
