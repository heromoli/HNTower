<template>
    <div class="mod-log">

        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
                <el-input size="mini" v-model="dataForm.param" placeholder="用户名／用户操作" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="mini" @click="getDataList()">查询</el-button>
                <el-button type="success" size="mini" @click="uploadHandle()">导入</el-button>
                <el-button type="primary" size="mini" @click="exportHandle()">导出</el-button>
            </el-form-item>
        </el-form>
        <el-row>
            <el-col :span="24">
                <el-steps :active="1" finish-status="success" simple style="margin-top: 10px;margin-bottom: 10px">
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
                    width="150"
                    label="需求编号">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="operatorName"
                    header-align="center"
                    align="center"
                    width="80"
                    label="电信企业">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="branchCompany"
                    header-align="center"
                    align="center"
                    width="80"
                    label="分公司">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="stationName"
                    header-align="center"
                    align="center"
                    width="150"
                    label="站点名称">
            </el-table-column>
            <!--<el-table-column align="left" label="站点位置及要求">-->
            <el-table-column
                    fixed
                    prop="longitude"
                    header-align="center"
                    align="center"
                    width="90"
                    label="经度">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="latitude"
                    header-align="center"
                    align="center"
                    width="90"
                    label="纬度">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="address"
                    header-align="center"
                    align="center"
                    width="200"
                    :show-overflow-tooltip=true
                    label="详细地址">
            </el-table-column>
            <!--</el-table-column>-->
            <!--<el-table-column align="center" label="共享共建信息">-->
            <el-table-column
                    fixed
                    prop="ifCanOrder"
                    header-align="center"
                    align="center"
                    width="80"
                    label="能否形成订单">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="不能形成订单原因"
                    header-align="center"
                    align="center"
                    width="150"
                    label="不能形成订单原因">
            </el-table-column>
            <!--</el-table-column>-->
            <el-table-column
                    fixed="right"
                    header-align="center"
                    align="center"
                    label="操作"
                    width="230">
                <template size="mini" slot-scope="{row,$index}">
                    <el-button type="primary" size="small" @click.native="showProcessesHiList(row)">查看</el-button>
                    <el-button size="small" @click.native="cancelProcess(row)">不同意</el-button>
                    <el-button type="success" size="small" @click.native="submitProcess(row)">同意</el-button>
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
        <show-change-confirm v-if="showChangeConfirmVisible" ref="showChangeConfirm"></show-change-confirm>
        <upload v-if="uploadVisible" ref="upload" @refreshDataList="getDataList"></upload>
    </div>
</template>

<script>
    import ShowProcesses from './table4_detail'
    import ShowChangeConfirm from './table6_detail'
    import Upload from './upload'

    export default {
        data() {
            return {
                dataForm: {
                    actProcInstId: '',
                    actProcStatus: '',
                    approve: '',
                    param: '',
                    key: 'usertask2',
                    groupId: '4'
                },
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                uploadVisible: false,
                dataListLoading: false,
                showProcessesVisible: false,
                showChangeConfirmVisible: false,
                selectionDataList: []
            }
        },
        created() {
            this.getDataList()
        },
        components: {
            ShowProcesses,
            ShowChangeConfirm,
            Upload
        },
        methods: {
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/wf/findCheckProcessByGroup'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': this.pageSize,
                        'key': this.dataForm.key,
                        'groupId': this.dataForm.groupId
                    })
                }).then(({data}) => {
                    if (data.page != null && data.code === 0) {
                        this.dataList = data.page.list;
                        this.totalPage = data.page.totalCount;
                        console.log(this.dataList);
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
            submitProcess(row) {
                row.approve = '1';
                row.groupId = '4';
                this.$confirm('确定提交工单?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$http({
                        url: this.$http.adornUrl('/api/wf/complete'),
                        method: 'post',
                        data: this.$http.adornData(row)
                    }).then(({data}) => {
                        if (data && data.code === 0) {
                            this.$message({
                                message: '操作成功',
                                type: 'success',
                                duration: 1500,
                                onClose: () => {
                                    this.getDataList();
                                }
                            })
                        } else {
                            this.$message.error(data.msg)
                        }
                    })
                })
            },
            cancelProcess(row) {
                row.tableId = '4';
                this.showChangeConfirmVisible = true;
                this.$nextTick(() => {
                    this.$refs.showChangeConfirm.init(row);
                })
            },
            showProcessesHiList(row) {
                console.log(row);
                this.showProcessesVisible = true;
                this.$nextTick(() => {
                    this.$refs.showProcesses.init(row);
                })
            },
            // 导入
            uploadHandle() {
                this.uploadVisible = true;
                this.$nextTick(() => {
                    this.$refs.upload.init(this.dataForm.groupId)
                })
            },
            //导出
            exportHandle() {
                // console.log(this)
                window.location.href = this.$http.adornUrl(`/api/wf/export?groupId=${this.dataForm.groupId}&token=${this.$cookie.get('token')}`)
            }
        }
    }
</script>
<style>
    .el-table .success-row {
        background: #f0f9eb;
    }
</style>
