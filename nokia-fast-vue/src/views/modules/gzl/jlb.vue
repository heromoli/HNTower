<template>
    <div class="mod-log">
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
                <el-input size="mini" v-model="dataForm.branchCompany" placeholder="分公司" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-input size="mini" v-model="dataForm.stationName" placeholder="站点名称" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-input size="mini" v-model="dataForm.address" placeholder="地址" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="getDataList()">查询</el-button>
                <el-button type="success" size="mini" @click="uploadHandle()">导入</el-button>
            </el-form-item>
        </el-form>
        <el-table
                :data="dataList"
                border
                v-loading="dataListLoading"
                :row-class-name="tableRowClassName"
                style="height: auto; width: auto">
            <el-table-column
                    prop="id"
                    header-align="center"
                    align="center"
                    width="80"
                    label="ID">
            </el-table-column>
            <el-table-column
                    prop="branchCompany"
                    header-align="center"
                    align="center"
                    width="150"
                    label="分公司">
            </el-table-column>
            <el-table-column
                    prop="demandNum"
                    header-align="center"
                    align="center"
                    width="150"
                    label="需求编号">
            </el-table-column>
            <el-table-column
                    prop="stationName"
                    header-align="center"
                    align="center"
                    width="180"
                    :show-overflow-tooltip="true"
                    label="站点名称">
            </el-table-column>
            <el-table-column
                    prop="address"
                    header-align="center"
                    align="center"
                    :show-overflow-tooltip="true"
                    label="详细地址">
            </el-table-column>
            <el-table-column
                    prop="demandSource"
                    header-align="center"
                    align="center"
                    :show-overflow-tooltip="true"
                    label="需求来源">
            </el-table-column>
            <el-table-column
                    prop="deliveryTime"
                    header-align="center"
                    align="center"
                    width="120"
                    label="交付时间">
            </el-table-column>
            <el-table-column
                    prop="totalFinishTime"
                    header-align="center"
                    align="center"
                    width="120"
                    label="完成时间">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    header-align="center"
                    label="操作"
                    align="center"
                    width="80">
                <template size="mini" slot-scope="{row,$index}">
                    <el-button type="primary" size="small" @click.native="showDetail(row)">查看</el-button>
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
        <show-detail v-if="showDetailVisible" ref="showDetail"></show-detail>
        <upload v-if="uploadVisible" ref="upload" @refreshDataList="getDataList"></upload>
    </div>
</template>

<script>
    import ShowDetail from './jlb_detail'
    import Upload from './jlb_upload';

    export default {
        data() {
            return {
                dataForm: {
                    branchCompany: '',
                    stationName: '',
                    address: '',
                },
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
                selectionDataList: [],
                showDetailVisible: false,
                uploadVisible: false,
            }
        },
        created() {
            this.getDataList()
        },
        components: {
            ShowDetail,
            Upload
        },
        methods: {
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/wf/findSupervisor'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': this.pageSize,
                        'branchCompany': this.dataForm.branchCompany,
                        'stationName': this.dataForm.stationName,
                        'address': this.dataForm.address
                    })
                }).then(({data}) => {
                    if (data && data.code === 0) {
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
            showDetail(row) {
                this.showDetailVisible = true;
                this.$nextTick(() => {
                    this.$refs.showDetail.init(row);
                })
            },
            // 上传文件
            uploadHandle() {
                this.uploadVisible = true;
                this.$nextTick(() => {
                    this.$refs.upload.init()
                })
            }
        }
    }
</script>
