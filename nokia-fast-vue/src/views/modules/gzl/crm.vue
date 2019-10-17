<template>
    <div class="mod-log">

        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
                <el-input size="mini" v-model="dataForm.param" placeholder="站点名称" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="mini" @click="getDataList()">查询</el-button>
                <el-button type="success" size="mini" @click="uploadHandle()">导入</el-button>
                <!--<el-button type="primary" size="mini" @click="exportHandle()">导出</el-button>-->
            </el-form-item>
        </el-form>
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
                    prop="orderNum"
                    header-align="center"
                    align="center"
                    width="140"
                    label="订单号">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="operatorName"
                    header-align="center"
                    align="center"
                    width="80"
                    label="运营商">
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
                    prop="towerStationName"
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
            <el-table-column header-align="center" align="center" label="站点位置及要求" >
                <el-table-column
                        fixed
                        prop="longitude"
                        header-align="center"
                        align="center"
                        width="140"
                        label="经度">
                </el-table-column>
                <el-table-column
                        fixed
                        prop="latitude"
                        header-align="center"
                        align="center"
                        width="140"
                        label="纬度">
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
                    prop="towerType"
                    header-align="center"
                    align="center"
                    width="80"
                    label="铁塔种类">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    header-align="center"
                    label="操作"
                    align="center"
                    width="230">
                <template size="mini" slot-scope="{row,$index}">
                    <el-button type="primary" size="small" @click.native="showProcessesHiList(row)">查看</el-button>
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
        <upload v-if="uploadVisible" ref="upload" @refreshDataList="getDataList"></upload>
    </div>
</template>

<script>
    import ShowProcesses from './table3_detail'
    import Upload from './crm_upload'

    export default {
        data() {
            return {
                dataForm: {
                    param :''
                },
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
                showProcessesVisible: false,
                uploadVisible: false,
                selectionDataList: []
            }
        },
        created() {
            this.getDataList()
        },
        components: {
            ShowProcesses,
            Upload
        },
        methods: {
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/wf/queryCRMData'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': this.pageSize,
                        'queryParam':this.dataForm.param
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
            // 上传文件
            uploadHandle() {
                this.uploadVisible = true;
                this.$nextTick(() => {
                    this.$refs.upload.init(this.dataForm.groupId)
                })
            },
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
