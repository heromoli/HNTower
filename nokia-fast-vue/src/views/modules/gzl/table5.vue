<template>
    <div class="mod-log">
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
                <el-input size="mini" v-model="dataForm.param" placeholder="用户名／用户操作" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="mini" @click="getDataList()">查询</el-button>
                <el-button v-if="isAuth('gzl:table5:update')" type="success" size="mini" @click="uploadHandle()">导入</el-button>
                <el-button type="primary" size="mini" @click="exportHandle()">导出</el-button>
            </el-form-item>
        </el-form>
        <el-row>
            <el-col :span="24">
                <el-steps :active="2" finish-status="success" simple style="margin-top: 10px;margin-bottom: 10px">
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
                    label="电信企业">
            </el-table-column>
            <el-table-column

                    prop="branchCompany"
                    header-align="center"
                    align="center"
                    label="分公司">
            </el-table-column>
            <el-table-column

                    prop="stationName"
                    header-align="center"
                    width="100"
                    align="center"
                    label="站点名称">
            </el-table-column>
            <el-table-column

                    prop="buildType"
                    header-align="center"
                    align="center"
                    label="建设方式">
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
                        width="180"
                        :show-overflow-tooltip=true
                        label="详细地址">
                </el-table-column>
            </el-table-column>
            <el-table-column

                    prop="towerType"
                    header-align="center"
                    align="center"
                    width="100"
                    label="铁塔类型">
            </el-table-column>
            <el-table-column

                    prop="towerTypeDetail"
                    header-align="center"
                    align="center"
                    width="100"
                    label="铁塔类型细分">
            </el-table-column>
            <el-table-column

                    prop="roomType"
                    header-align="center"
                    align="center"
                    width="100"
                    label="机房类型">
            </el-table-column>
            <el-table-column

                    prop="productConfig"
                    header-align="center"
                    align="center"
                    width="150"
                    label="产品配置">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    header-align="center"
                    align="center"
                    label="操作"
                    width="230">
                <template size="mini" slot-scope="{row,$index}">
                    <el-button type="primary" size="small" @click.native="showProcessesHiList(row)">查看</el-button>
                    <el-button v-if="isAuth('gzl:table5:update')" size="small" @click.native="changeProcess(row)">变更</el-button>
                    <el-button v-if="isAuth('gzl:table5:update')" type="success" size="small" @click.native="submitProcess(row)">完成</el-button>
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
    import ShowProcesses from './table5_detail'
    import ShowChangeConfirm from './table6_detail'
    import Upload from './upload'

    export default {
        data() {
            return {
                dataForm: {
                    actProcInstId: '',
                    actProcStatus: '',
                    approve: '',
                    param :'',
                    key: 'usertask3',
                    groupId: '5'
                },
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                uploadVisible: false,
                dataListLoading: false,
                showProcessesVisible: false,
                showChangeConfirmVisible: false,
                selectionDataList: [],
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
                    url: this.$http.adornUrl('/api/wf/findConfirmProcessByGroup'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': this.pageSize,
                        'key': this.dataForm.key,
                        'groupId': this.dataForm.groupId,
                        'param': this.dataForm.param
                    })
                }).then(({data}) => {
                    if (data.page != null && data.code === 0) {
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
            tableRowClassName({row, rowIndex}) {
                if (rowIndex % 2 === 1) {
                    return 'success-row';
                }
                return '';
            },
            submitProcess(row) {
                row.approve = '1';
                row.groupId = '5';
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
            },
            changeProcess(row) {
                row.tableId = '5';
                this.showChangeConfirmVisible = true;
                this.$nextTick(() => {
                    this.$refs.showChangeConfirm.init(row);
                })
            },
            showProcessesHiList(row) {
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
