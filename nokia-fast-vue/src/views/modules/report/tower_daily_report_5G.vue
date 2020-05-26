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
                        <el-button @click="getBranchData()">查询</el-button>
                        <el-button type="primary" @click="branchExportHandle()">导出</el-button>
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
                            prop="branchCompany"
                            header-align="center"
                            align="center"
                            label="分公司">
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
                        <el-button @click="getCountyData()">查询</el-button>
                        <el-button type="primary" @click="countyExportHandle()">导出</el-button>
                    </el-form-item>
                </el-form>
                <el-table
                        :data="countyList"
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
                            prop="branchCompany"
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
            <el-tab-pane label="运营商报表">
                <el-form :inline="true" :model="dataForm" @keyup.enter.native="getOperatorData()">
                    <el-form-item>
                        <el-date-picker
                                v-model="dataForm.operatorQueryDate"
                                type="date" style="width: 100%"
                                placeholder="选择日期"
                                value-format="yyyy-MM-dd">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="getOperatorData()">查询</el-button>
                        <el-button type="primary"  @click="operatorExportHandle()">导出</el-button>
                    </el-form-item>
                </el-form>
                <el-table
                        :data="operatorList"
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
                            prop="branchCompany"
                            header-align="center"
                            align="center"
                            label="分公司">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="operatorName"
                            header-align="center"
                            align="center"
                            label="运营商">
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
                    <el-table-column header-align="center" align="center" label="交付">
                        <el-table-column
                                prop="deliverNumber"
                                align="center"
                                header-align="center"
                                label="总量">
                        </el-table-column>
                        <el-table-column
                                prop="deliverIncrement"
                                align="center"
                                header-align="center"
                                label="增量">
                        </el-table-column>
                    </el-table-column>
                    <el-table-column header-align="center" align="center" label="起租">
                        <el-table-column
                                prop="hireNumber"
                                align="center"
                                header-align="center"
                                label="总量">
                        </el-table-column>
                        <el-table-column
                                prop="hireIncrement"
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
                        @size-change="operatorSizeChangeHandle"
                        @current-change="operatorCurrentChangeHandle"
                        :current-page="operatorPageIndex"
                        :page-sizes="[10, 20, 50, 100]"
                        :page-size="operatorPageSize"
                        :total="operatorTotalPage"
                        layout="total, sizes, prev, pager, next, jumper">
                </el-pagination>
            </el-tab-pane>
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
    import {formatDate} from '@/utils/initDateFormat'

    export default {
        name: "jlb_report",
        data() {
            return {
                dataForm: {
                    param: '',
                    branchQueryDate: formatDate(new Date(), 'yyyy-MM-dd'),
                    countyQueryDate: formatDate(new Date(), 'yyyy-MM-dd'),
                    operatorQueryDate: formatDate(new Date(), 'yyyy-MM-dd')
                },
                branchList: [],
                countyList: [],
                operatorList: [],
                branchPageIndex: 1,
                branchPageSize: 10,
                branchTotalPage: 0,
                countyPageIndex: 1,
                countyPageSize: 20,
                countyTotalPage: 0,
                operatorPageIndex: 1,
                operatorPageSize: 20,
                operatorTotalPage: 0,
                dataListLoading: false,
                showChangeConfirmVisible: false
            }
        },
        created() {
            this.getBranchData();
            this.getCountyData();
            this.getOperatorData();
        },
        components: {},
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
            getOperatorData() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/report/queryOperatorDailyReport'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.operatorPageIndex,
                        'limit': this.operatorPageSize,
                        'queryParam': this.dataForm
                    })
                }).then(({data}) => {
                    if (data.page != null && data.code === 0) {
                        this.operatorList = data.page.list;
                        this.operatorTotalPage = data.page.totalCount
                    } else {
                        this.operatorList = [];
                        this.operatorTotalPage = 0
                    }
                    this.dataListLoading = false
                })
            },
            // 每页数
            operatorSizeChangeHandle(val) {
                this.operatorPageSize = val;
                this.operatorPageIndex = 1;
                this.getOperatorData()
            },
            // 当前页
            operatorCurrentChangeHandle(val) {
                this.operatorPageIndex = val;
                this.getOperatorData()
            },
            branchExportHandle() {
                window.location.href = this.$http.adornUrl(`/api/report/exportBranchReport?branchQueryDate=${this.dataForm.branchQueryDate}&token=${this.$cookie.get('token')}`)
            },
            countyExportHandle() {
                window.location.href = this.$http.adornUrl(`/api/report/exportCountyReport?countyQueryDate=${this.dataForm.countyQueryDate}&token=${this.$cookie.get('token')}`)
            },
            operatorExportHandle() {
                window.location.href = this.$http.adornUrl(`/api/report/exportOperatorReport?operatorQueryDate=${this.dataForm.operatorQueryDate}&token=${this.$cookie.get('token')}`)
            }
        }
    }
</script>

