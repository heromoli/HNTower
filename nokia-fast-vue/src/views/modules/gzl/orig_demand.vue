<template>
    <div class="mod-log">
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
                <el-input size="mini" v-model="dataForm.param" placeholder="站点名称" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="mini" @click="getDataList()">查询</el-button>
                <el-button type="primary" size="mini" @click="exportHandle()">导出</el-button>
            </el-form-item>
        </el-form>
        <el-table
                :data="dataList"
                border
                v-loading="dataListLoading"
                stripe
                style="height: auto; width: auto">
            <el-table-column
                    fixed
                    prop="demandNum"
                    header-align="center"
                    align="center"
                    width="130"
                    label="需求编号">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="operatorName"
                    header-align="center"
                    align="center"
                    width="60"
                    label="电信企业">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="branchCompany"
                    header-align="center"
                    align="center"
                    width="60"
                    label="地市">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="region"
                    header-align="center"
                    align="center"
                    width="60"
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
                    prop="scene"
                    header-align="center"
                    align="center"
                    width="80"
                    label="场景划分">
            </el-table-column>
            <el-table-column
                    prop="stationLevel"
                    header-align="center"
                    align="center"
                    width="70"
                    label="站点级别">
            </el-table-column>
            <el-table-column header-align="center" align="center" label="站点位置及要求">
                <el-table-column
                        fixed
                        prop="longitude"
                        header-align="center"
                        align="center"
                        width="100"
                        label="经度">
                </el-table-column>
                <el-table-column
                        fixed
                        prop="latitude"
                        header-align="center"
                        align="center"
                        width="100"
                        label="纬度">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        prop="address"
                        header-align="center"
                        align="center"
                        width="150"
                        :show-overflow-tooltip=true
                        label="详细地址">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        prop="coverRangeDescribe"
                        header-align="center"
                        align="center"
                        width="120"
                        :show-overflow-tooltip=true
                        label="覆盖范围描述">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        prop="deviateRadius"
                        header-align="center"
                        align="center"
                        width="80"
                        label="允许偏离半径">
                </el-table-column>
            </el-table-column>
            <el-table-column
                    prop="deliveryTime"
                    header-align="center"
                    align="center"
                    width="80"
                    label="交付时间要求">
            </el-table-column>
            <el-table-column
                    prop="systemNum"
                    header-align="center"
                    align="center"
                    width="80"
                    label="系统数">
            </el-table-column>
            <el-table-column
                    prop="bbuNum"
                    header-align="center"
                    align="center"
                    width="80"
                    label="BBU个数">
            </el-table-column>
            <el-table-column
                    prop="rruNum"
                    header-align="center"
                    align="center"
                    width="80"
                    label="RRU个数">
            </el-table-column>
            <el-table-column header-align="center" align="center" label="天线参数">
                <el-table-column
                        prop="antennaHeight"
                        header-align="center"
                        align="center"
                        width="80"
                        label="天线挂高范围">
                </el-table-column>
                <el-table-column
                        prop="antennaAngle"
                        header-align="center"
                        align="center"
                        width="80"
                        label="天线方向角">
                </el-table-column>
                <el-table-column
                        prop="antennaNum"
                        header-align="center"
                        align="center"
                        width="80"
                        label="天线数">
                </el-table-column>
            </el-table-column>
            <el-table-column
                    prop="contact"
                    header-align="center"
                    align="center"
                    width="80"
                    label="需求具体联系人">
            </el-table-column>
            <el-table-column
                    prop="remarks"
                    header-align="center"
                    align="center"
                    width="120"
                    :show-overflow-tooltip=true
                    label="备注">
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
        <show-change-confirm v-if="showChangeConfirmVisible" ref="showChangeConfirm"></show-change-confirm>
    </div>
</template>

<script>

    export default {
        data() {
            return {
                dataForm: {
                    approve: '',
                    param: '',
                },
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
                showChangeConfirmVisible: false,
                selectionDataList: []
            }
        },
        created() {
            this.getDataList()
        },
        components: {},
        methods: {
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/wf/queryOrigDemand'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': this.pageSize,
                        'queryParam': this.dataForm.param
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
            exportHandle() {
                window.location.href = this.$http.adornUrl(`/api/wf/export?groupId=${this.dataForm.groupId}&token=${this.$cookie.get('token')}`)
            }
        }
    }
</script>

