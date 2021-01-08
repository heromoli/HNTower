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
                <el-button v-if="isAuth('gzl:table3:add')" type="success" size="mini" @click="uploadHandle()">导入</el-button>
                <el-button type="primary" size="mini" @click="exportHandle()">导出</el-button>
                <el-button v-if="isAuth('gzl:table3:add')" type="success" size="mini" @click="startWF()">新需求</el-button>
            </el-form-item>
        </el-form>
        <el-row>
            <el-col :span="24">
                <el-steps :active="0" finish-status="success" simple style="margin-top: 10px;margin-bottom: 10px">
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
                stripe
                size="mini"
                style="height: auto; width: auto">
            <el-table-column
                    fixed
                    prop="id"
                    header-align="center"
                    align="center"
                    width="80"
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
                    prop="region"
                    header-align="center"
                    align="center"
                    width="80"
                    label="区县">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="stationName"
                    header-align="center"
                    align="center"
                    width="150"
                    label="站点名称">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="scene"
                    header-align="center"
                    align="center"
                    width="100"
                    label="场景划分">
            </el-table-column>
            <el-table-column header-align="center" align="center" label="站点位置及要求">
                <el-table-column
                        fixed
                        prop="longitude"
                        header-align="center"
                        align="center"
                        width="140"
                        label="经度">
                    <!--<template slot-scope="{row,$index}">-->
                        <!--<el-input size="mini" placeholder="请输入内容" style="width: 100px"-->
                                  <!--v-model="row.longitude"></el-input>-->
                    <!--</template>-->
                </el-table-column>
                <el-table-column
                        fixed
                        prop="latitude"
                        header-align="center"
                        align="center"
                        width="140"
                        label="纬度">
                    <!--<template slot-scope="{row,$index}">-->
                        <!--<el-input size="mini" placeholder="请输入内容" style="width: 100px"-->
                                  <!--v-model="row.latitude"></el-input>-->
                    <!--</template>-->
                </el-table-column>
                <el-table-column
                        fixed="right"
                        prop="address"
                        header-align="center"
                        align="center"
                        :show-overflow-tooltip=true
                        label="详细地址">
                </el-table-column>
            </el-table-column>
            <el-table-column
                    fixed="right"
                    header-align="center"
                    label="操作"
                    align="center"
                    width="230">
                <template size="mini" slot-scope="{row,$index}">
                    <el-button type="primary" size="small" @click.native="showProcessesHiList(row)">查看</el-button>
                    <el-button v-if="isAuth('gzl:table3:update')" size="small" @click.native="cancelProcess(row)">不同意</el-button>
                    <el-button v-if="isAuth('gzl:table3:update')" type="success" size="small" @click.native="submitProcess(row)">同意</el-button>
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
        <new-processes v-if="newProcessesVisible" ref="newProcesses"></new-processes>
    </div>
</template>

<script>
    import NewProcesses from './new_station_demand'
    import ShowProcesses from './table3_detail'
    import ShowChangeConfirm from './table6_detail'
    import Upload from './upload'

    export default {
        data() {
            return {
                newProcessesVisible: false,
                dataForm: {
                    actProcInstId: '',
                    actProcStatus: '',
                    approve: '',
                    stationName: '',
                    demandNum: '',
                    key: 'usertask1',
                    groupId: '3'
                },
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
                showProcessesVisible: false,
                showChangeConfirmVisible: false,
                uploadVisible: false,
                selectionDataList: []
            }
        },
        created() {
            this.getDataList()
        },
        components: {
            NewProcesses,
            ShowProcesses,
            ShowChangeConfirm,
            Upload
        },
        methods: {
            startWF() {
                this.newProcessesVisible = true;
                this.$nextTick(() => {
                    this.$refs.newProcesses.init()
                })
            },
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/wf/findStartProcessByGroup'),
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
            submitProcess(row) {
                row.approve = '1';
                row.groupId = '3';
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
                row.groupId = '3';
                this.$confirm('该操作会中止本次需求申请，是否确定？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$http({
                        url: this.$http.adornUrl('/api/wf/shutDownProcess'),
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
                });

                // this.showChangeConfirmVisible = true;
                // this.$nextTick(() => {
                //     this.$refs.showChangeConfirm.init(row);
                // })
            },
            showProcessesHiList(row) {
                this.showProcessesVisible = true;
                this.$nextTick(() => {
                    this.$refs.showProcesses.init(row);
                })
            },
            // 上传文件
            uploadHandle() {
                this.uploadVisible = true;
                this.$nextTick(() => {
                    this.$refs.upload.init(this.dataForm.groupId)
                })
            },
            exportHandle() {
                window.location.href = this.$http.adornUrl(`/api/wf/export?groupId=${this.dataForm.groupId}&token=${this.$cookie.get('token')}`)
            }
        }
    }
</script>
