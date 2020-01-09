<template>
    <div>
        <el-tabs type="border-card">
            <el-tab-pane label="分公司报表">
                <el-form :inline="true" :model="dataForm" @keyup.enter.native="getBranchData()">
                    <el-form-item>
                        <el-date-picker
                                v-model="dataForm.branchQueryDate"
                                type="date" style="width: 100%"
                                placeholder="选择日期"
                                value-format="yyyy-MM-dd">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item>
                        <el-button size="mini" @click="getBranchData()">查询</el-button>
                        <el-button type="primary" size="mini" @click="branchExportHandle()">导出</el-button>
                    </el-form-item>
                </el-form>
                <el-table
                        :data="branchList"
                        border
                        v-loading="dataListLoading"
                        stripe
                        style="height: auto; width: auto">
                    <el-table-column
                            fixed
                            prop="sdate"
                            header-align="center"
                            align="center"
                            label="时间">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="branch"
                            header-align="center"
                            align="center"
                            label="分公司">
                    </el-table-column>
                    <el-table-column header-align="center" align="center" label="任务">
                        <el-table-column

                                prop="task"
                                header-align="center"
                                align="center"
                                label="总量">
                        </el-table-column>
                        <el-table-column

                                prop="taskIncrement"
                                align="center"
                                header-align="center"
                                label="增量">
                        </el-table-column>
                    </el-table-column>
                    <el-table-column header-align="center" align="center" label="征址">
                        <el-table-column

                                prop="locationNeg"
                                header-align="center"
                                align="center"
                                label="总量">
                        </el-table-column>
                        <el-table-column

                                prop="locationIncrement"
                                align="center"
                                header-align="center"
                                label="增量">
                        </el-table-column>
                    </el-table-column>
                    <el-table-column header-align="center" align="center" label="开工">
                        <el-table-column

                                prop="projectStart"
                                header-align="center"
                                align="center"
                                label="总量">
                        </el-table-column>
                        <el-table-column

                                prop="startIncrement"
                                align="center"
                                header-align="center"
                                label="增量">
                        </el-table-column>
                    </el-table-column>
                    <el-table-column header-align="center" align="center" label="完工">
                        <el-table-column

                                prop="complete"
                                header-align="center"
                                align="center"
                                label="总量">
                        </el-table-column>
                        <el-table-column

                                prop="completeIncrement"
                                align="center"
                                header-align="center"
                                label="增量">
                        </el-table-column>
                    </el-table-column>
                    <el-table-column
                            prop="ratioLocationNeg"
                            align="center"
                            header-align="center"
                            label="征址率">
                        <template slot-scope="scope">
                            <el-progress
                                    :stroke-width="18"
                                    :text-inside="true"
                                    :percentage="scope.row.ratioLocationNeg"
                            ></el-progress>
                        </template>
                    </el-table-column>
                    <el-table-column

                            prop="ratioComplete"
                            align="center"
                            header-align="center"
                            label="完工率">
                        <template slot-scope="scope">
                            <el-progress
                                    status="success"
                                    :stroke-width="18"
                                    :text-inside="true"
                                    :percentage="scope.row.ratioComplete"
                            ></el-progress>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination
                        @size-change="branchSizeChangeHandle"
                        @current-change="branchCurrentChangeHandle"
                        :current-page="branchPageIndex"
                        :page-sizes="[10, 20, 50, 100]"
                        :page-size="branchPageSize"
                        :total="branchTotalPage"
                        layout="total, sizes, prev, pager, next, jumper">
                </el-pagination>
            </el-tab-pane>
            <el-tab-pane label="区县报表">
                <el-form :inline="true" :model="dataForm" @keyup.enter.native="getCountyData()">
                    <el-form-item>
                        <el-date-picker
                                v-model="dataForm.countyQueryDate"
                                type="date" style="width: 100%"
                                placeholder="选择日期"
                                value-format="yyyy-MM-dd">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item>
                        <el-button size="mini" @click="getCountyData()">查询</el-button>
                        <el-button type="primary" size="mini" @click="countyExportHandle()">导出</el-button>
                    </el-form-item>
                </el-form>
                <el-table
                        :data="countyList"
                        border
                        v-loading="dataListLoading"
                        :row-class-name="tableRowClassName"
                        style="height: auto; width: auto">
                    <el-table-column
                            fixed
                            prop="sdate"
                            header-align="center"
                            align="center"
                            label="时间">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="branch"
                            header-align="center"
                            align="center"
                            label="分公司">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="county"
                            header-align="center"
                            align="center"
                            label="市县">
                    </el-table-column>
                    <el-table-column header-align="center" align="center" label="任务">
                        <el-table-column

                                prop="taskNumber"
                                header-align="center"
                                align="center"
                                label="总量">
                        </el-table-column>
                        <el-table-column

                                prop="taskIncrement"
                                align="center"
                                header-align="center"
                                label="增量">
                        </el-table-column>
                    </el-table-column>
                    <el-table-column header-align="center" align="center" label="征址">
                        <el-table-column

                                prop="locationNegNumber"
                                header-align="center"
                                align="center"
                                label="总量">
                        </el-table-column>
                        <el-table-column

                                prop="locationIncrement"
                                align="center"
                                header-align="center"
                                label="增量">
                        </el-table-column>
                    </el-table-column>
                    <el-table-column header-align="center" align="center" label="开工">
                        <el-table-column

                                prop="projectStartNumber"
                                header-align="center"
                                align="center"
                                label="总量">
                        </el-table-column>
                        <el-table-column

                                prop="startIncrement"
                                align="center"
                                header-align="center"
                                label="增量">
                        </el-table-column>
                    </el-table-column>
                    <el-table-column header-align="center" align="center" label="完工">
                        <el-table-column

                                prop="completeNumber"
                                header-align="center"
                                align="center"
                                label="总量">
                        </el-table-column>
                        <el-table-column

                                prop="completeIncrement"
                                align="center"
                                header-align="center"
                                label="增量">
                        </el-table-column>
                    </el-table-column>
                    <el-table-column
                            prop="ratioLocationNeg"
                            align="center"
                            header-align="center"
                            label="征址率">
                        <template slot-scope="scope">
                            <el-progress
                                    :stroke-width="18"
                                    :text-inside="true"
                                    :percentage="scope.row.ratioLocationNeg"
                            ></el-progress>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="ratioComplete"
                            align="center"
                            header-align="center"
                            label="完工率">
                        <template slot-scope="scope">
                            <el-progress
                                    status="success"
                                    :stroke-width="18"
                                    :text-inside="true"
                                    :percentage="scope.row.ratioComplete"
                            ></el-progress>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination
                        @size-change="countySizeChangeHandle"
                        @current-change="countyCurrentChangeHandle"
                        :current-page="countyPageIndex"
                        :page-sizes="[10, 20, 50, 100]"
                        :page-size="countyPageSize"
                        :total="countyTotalPage"
                        layout="total, sizes, prev, pager, next, jumper">
                </el-pagination>
            </el-tab-pane>
            <!--<el-tab-pane label="运营商报表">角色管理</el-tab-pane>-->
            <!--<el-tab-pane label="特殊站点报表">定时任务补偿</el-tab-pane>-->
        </el-tabs>
    </div>
</template>

<style scoped>
    .el-table .warning-row {
        background: oldlace;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }
</style>

<script>
    export default {
        name: "jlb_report",
        data() {
            return {
                dataForm: {
                    param: '',
                    branchQueryDate:new Date(),
                    countyQueryDate:new Date()
                },
                branchList: [],
                countyList: [],
                branchPageIndex: 1,
                branchPageSize: 10,
                branchTotalPage: 0,
                countyPageIndex: 1,
                countyPageSize: 10,
                countyTotalPage: 0,
                dataListLoading: false,
                showChangeConfirmVisible: false
            }
        },
        created() {
            this.getBranchData();
            this.getCountyData();
        },
        components: {

        },
        methods: {
            getBranchData() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/report/queryBranchDailyReport'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.branchPageIndex,
                        'limit': this.branchPageSize,
                        'queryParam': this.dataForm
                    })
                }).then(({data}) => {
                    if (data.page != null && data.code === 0) {
                        this.branchList = data.page.list;
                        this.branchTotalPage = data.page.totalCount
                    } else {
                        this.branchList = [];
                        this.branchTotalPage = 0
                    }
                    this.dataListLoading = false
                })
            },
            // 每页数
            branchSizeChangeHandle(val) {
                this.branchPageSize = val;
                this.branchPageIndex = 1;
                this.getBranchData()
            },
            // 当前页
            branchCurrentChangeHandle(val) {
                this.branchPageIndex = val;
                this.getBranchData()
            },
            getCountyData() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/report/queryCountyDailyReport'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.countyPageIndex,
                        'limit': this.countyPageSize,
                        'queryParam': this.dataForm
                    })
                }).then(({data}) => {
                    if (data.page != null && data.code === 0) {
                        this.countyList = data.page.list;
                        this.countyTotalPage = data.page.totalCount
                    } else {
                        this.countyList = [];
                        this.countyTotalPage = 0
                    }
                    this.dataListLoading = false
                })
            },
            // 每页数
            countySizeChangeHandle(val) {
                this.countyPageSize = val;
                this.countyPageIndex = 1;
                this.getCountyData()
            },
            // 当前页
            countyCurrentChangeHandle(val) {
                this.countyPageIndex = val;
                this.getCountyData()
            },
            branchExportHandle() {
                window.location.href = this.$http.adornUrl(`/api/report/exportBranchReport?branchQueryDate=${this.dataForm.branchQueryDate}&token=${this.$cookie.get('token')}`)
            },
            countyExportHandle() {
                window.location.href = this.$http.adornUrl(`/api/report/exportCountyReport?countyQueryDate=${this.dataForm.countyQueryDate}&token=${this.$cookie.get('token')}`)
            }
        }
    }
</script>

