<template>
    <div class="mod-log">
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
                <el-input size="mini" v-model="dataForm.demandNum" placeholder="需求编号" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-input size="mini" v-model="dataForm.stationName" placeholder="站点名称" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="mini" @click="getDataList()">查询</el-button>
                <!--<el-button type="success" size="mini">导入</el-button>-->
                <!--<el-button type="primary" size="mini">导出</el-button>-->
            </el-form-item>
        </el-form>
        <el-row>
            <el-col :span="24">
                <el-steps :active="3" finish-status="success" simple style="margin-top: 10px;margin-bottom: 10px">
                    <el-step title="需求收集"></el-step>
                    <el-step title="站址筛查"></el-step>
                    <el-step title="建站确认"></el-step>
                    <el-step title="需求变更"></el-step>
                </el-steps>
            </el-col>
        </el-row>
        <el-table
                :data="dataList"
                border
                v-loading="dataListLoading"
                :row-class-name="tableRowClassName"
                size="mini"
                style="height: auto; width: auto">
            <el-table-column
                    fixed
                    prop="id"
                    header-align="center"
                    align="center"
                    width="50"
                    label="ID">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="demandNum"
                    header-align="center"
                    align="center"
                    width="140"
                    label="需求编号">
            </el-table-column>
            <el-table-column
                    prop="operatorName"
                    header-align="center"
                    align="center"
                    width="80"
                    label="电信企业">
            </el-table-column>
            <el-table-column
                    prop="branchCompany"
                    header-align="center"
                    align="center"
                    width="80"
                    label="分公司">
            </el-table-column>
            <el-table-column
                    prop="stationName"
                    header-align="center"
                    align="center"
                    width="150"
                    label="站点名称">
            </el-table-column>
            <el-table-column align="center" label="站点位置及要求">
                <el-table-column
                        prop="longitude"
                        header-align="center"
                        align="center"
                        width="90"
                        label="经度">
                </el-table-column>
                <el-table-column
                        prop="latitude"
                        header-align="center"
                        align="center"
                        width="90"
                        label="纬度">
                </el-table-column>
                <el-table-column
                        prop="address"
                        header-align="center"
                        align="center"
                        width="240"
                        :show-overflow-tooltip=true
                        label="详细地址">
                </el-table-column>
            </el-table-column>
            <el-table-column
                    prop="changeReason"
                    header-align="center"
                    align="center"
                    label="变更原因">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    header-align="center"
                    align="center"
                    label="操作"
                    width="80">
                <template size="mini" slot-scope="{row,$index}">
                    <el-button type="success" size="small" @click.native="showProcessesHiList(row)">查看</el-button>
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
        <show-processes v-if="showProcessesVisible" ref="showProcesses"></show-processes>
    </div>
</template>

<script>
    import ShowProcesses from './table6_confirm'

    export default {
        data() {
            return {
                dataForm: {
                    actProcInstId: '',
                    actProcStatus: '',
                    approve: '',
                    stationName: '',
                    demandNum: '',
                    key: 'usertask4',
                    groupId: '6'
                },
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
                showProcessesVisible: false,
                selectionDataList: [],
            }
        },
        created() {
            this.getDataList()
        },
        components: {
            ShowProcesses
        },
        methods: {
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/wf/findDemandChangeDataByGroup'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': this.pageSize,
                        'key': this.dataForm.key,
                        'groupId': this.dataForm.groupId,
                        'queryParam': this.dataForm
                    })
                }).then(({data}) => {
                    if (data.page != null && data.code === 0) {
                        this.dataList = data.page.list;
                        this.totalPage = data.page.totalCount;
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
            tableRowClassName({row, rowIndex}) {
                if (rowIndex % 2 === 1) {
                    return 'success-row';
                }
                return '';
            },
            showProcessesHiList(row) {
                this.showProcessesVisible = true;
                this.$nextTick(() => {
                    this.$refs.showProcesses.init(row);
                })
            }
        }
    }
</script>

