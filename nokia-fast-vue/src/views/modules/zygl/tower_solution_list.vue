<template>
    <div class="mod-log">
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
                <el-input v-model="dataForm.solution_name" placeholder="项目名称" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-input v-model="dataForm.county" placeholder="区县" clearable></el-input>
            </el-form-item>
            <el-form-item>

                <el-select v-model="dataForm.net_type" placeholder="网络类型" style="width: 100%">
                    <el-option
                            v-for="item in netTypeOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item>

                <el-select v-model="dataForm.plan_type" placeholder="方案类型" style="width: 100%">
                    <el-option
                            v-for="item in planTypeOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item>

                <el-select v-model="dataForm.plan_status" placeholder="方案状态" style="width: 100%">
                    <el-option
                            v-for="item in planStatusOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button @click="getDataList()">查询</el-button>
                <el-button type="success" @click="uploadHandle()">导入综合解决方案数据</el-button>
            </el-form-item>
        </el-form>
        <el-table
                :data="dataList"
                border
                stripe
                v-loading="dataListLoading"
                style="width: 100%">
            <el-table-column
                    prop="serialNumber"
                    header-align="center"
                    align="center"
                    width="180"
                    label="项目编号">
                <template slot-scope="scope">
                    <!--<a :href="'http://'+scope.row.solutionName"-->
                    <!--target="_blank">{{scope.row.serialNumber}}</a>-->
                    <el-button type="text" size="small" @click.native="jumpToDetail(scope.row.serialNumber)">
                        {{scope.row.serialNumber}}
                    </el-button>
                </template>
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="cityBranchCompany"
                    header-align="center"
                    align="center"
                    width="100"
                    :show-overflow-tooltip="true"
                    label="分公司">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="county"
                    header-align="center"
                    align="center"
                    width="100"
                    label="区县">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="solutionName"
                    header-align="center"
                    align="center"
                    :show-overflow-tooltip="true"
                    label="项目名称">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="sceneDivide"
                    header-align="center"
                    align="center"
                    width="150"
                    :show-overflow-tooltip="true"
                    label="场景划分">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="longitude"
                    header-align="center"
                    align="center"
                    width="100"
                    label="中心经度">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="latitude"
                    header-align="center"
                    align="center"
                    width="100"
                    label="中心纬度">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="netType"
                    header-align="center"
                    align="center"
                    width="100"
                    label="网络类型">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="planType"
                    header-align="center"
                    align="center"
                    width="200"
                    label="方案类型">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="planFormTime"
                    header-align="center"
                    align="center"
                    width="100"
                    label="编制时间">
            </el-table-column>
            <el-table-column
                    fixed="left"
                    prop="planStatus"
                    header-align="center"
                    align="center"
                    width="100"
                    label="方案状态">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    header-align="center"
                    label="附件"
                    align="center"
                    width="100">
                <template size="mini" slot-scope="{row,$index}">
                    <el-button type="success" size="small" @click.native="downLoadFile(row)">下载</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="sizeChangeHandle"
                @current-change="currentChangeHandle"
                :current-page="pageIndex"
                :page-sizes="[10, 20, 50, 100]"
                :page-size="pageSize"
                :total="totalPage"
                layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>
        <upload v-if="uploadVisible" ref="upload" @refreshDataList="getDataList"></upload>
    </div>
</template>

<script>
    import Upload from './tower_solution_upload';

    export default {
        data() {
            return {
                dataForm: {
                    county: '',
                    solution_name: '',
                    plan_form_time: '',
                    net_type:'',
                    plan_type: '',
                    plan_status: ''
                },
                dataList: [],
                uploadVisible: false,
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
                netTypeOptions: [{
                    value: '',
                    label: '----全选----'
                },{
                    value: '4G',
                    label: '4G'
                }, {
                    value: '5G',
                    label: '5G'
                }],
                planTypeOptions: [{
                    value: '',
                    label: '----全选----'
                },{
                    value: '移动',
                    label: '移动'
                }, {
                    value: '联通',
                    label: '联通'
                }, {
                    value: '电信',
                    label: '电信'
                }, {
                    value: '广电',
                    label: '广电'
                }],
                planStatusOptions: [
                    {
                        value: '',
                        label: '----全选----'
                    }, {
                        value: '待推送',
                        label: '待推送'
                    }, {
                        value: '已确认',
                        label: '已确认'
                    }, {
                        value: '已下需求',
                        label: '已下需求'
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
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/queryTowerSolution'),
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
            jumpToDetail(serialNumber) {
                // this.$router.push({name: 'zygl-tower_solution_detail', params: {serialNumber: serialNumber}});
                this.$router.push({name: 'zygl-tower_solution_detail_list', params: {serialNumber: serialNumber}})
            },
            downLoadFile(row) {
                this.dataListLoading = true;
                var county = row.county;
                var solution_name = row.solutionName;
                var plan_form_time = row.planFormTime;

                window.location.href = this.$http.adornUrl(`/api/zhzygl/downloadFile?county=${county}&solution_name=${solution_name}&plan_form_time=${plan_form_time}&token=${this.$cookie.get('token')}`);
                // this.$http({
                //     url: this.$http.adornUrl('/api/zhzygl/downloadFile'),
                //     method: 'get',
                //     params: this.$http.adornParams({
                //         'county': row.county,
                //         'solution_name': row.solutionName,
                //         'plan_form_time': row.planFormTime
                //     })
                // }).then(({data}) => {

                // });
                this.dataListLoading = false;
            }
        }
    }
</script>
