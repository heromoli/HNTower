<template>
    <div class="mod-log">
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
                <el-input size="mini"  v-model="dataForm.key" placeholder="用户名／用户操作" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="getDataList()">查询</el-button>
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
                    prop="operatorName"
                    header-align="center"
                    align="center"
                    width="150"
                    label="电信企业">
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
                    prop="remarks"
                    header-align="center"
                    align="center"

                    :show-overflow-tooltip="true"
                    label="备注">
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
    </div>
</template>

<script>
    export default {
        data() {
            return {
                dataForm: {
                    key: ''
                },
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
                selectionDataList: []
            }
        },
        created() {
            this.getDataList()
        },
        methods: {
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/wf/findProcessHistoryByUser'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': this.pageSize,
                        'key': this.dataForm.key
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
            tableRowClassName({row, rowIndex}) {
                if (rowIndex % 2 === 1) {
                    return 'success-row';
                }
                return '';
            },
        }
    }
</script>
