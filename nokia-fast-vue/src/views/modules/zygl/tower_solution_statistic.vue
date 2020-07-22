<template>
    <div class="mod-log">
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
                <el-date-picker
                        v-model="dataForm.smonth"
                        type="month" style="width: 100%"
                        placeholder="选择月份"
                        :clearable=false
                        value-format="yyyyMM">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button @click="getDataList()">查询</el-button>
            </el-form-item>
        </el-form>
        <!--:span-method="objectSpanMethod"-->
        <el-table :data="dataList"
                  border
                  stripe
                  :span-method="objectSpanMethod"
                  @cell-mouse-leave="cellMouseLeave"
                  @cell-mouse-enter="cellMouseEnter"
                  v-loading="dataListLoading"
                  style="width: 100%">
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
            <el-table-column header-align="center" align="center" label="方案编制">
                <el-table-column
                        fixed
                        prop="soluAll"
                        header-align="center"
                        align="center"
                        label="累计">
                </el-table-column>
                <el-table-column
                        fixed
                        prop="soluCurMon"
                        header-align="center"
                        align="center"
                        label="本月">
                </el-table-column>
            </el-table-column>
            <el-table-column header-align="center" align="center" label="规划点位">
                <el-table-column header-align="center" align="center" label="规划总需求">
                    <el-table-column
                            fixed
                            prop="sumPlanAll"
                            header-align="center"
                            align="center"
                            label="累计">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="sumPlanCurMon"
                            header-align="center"
                            align="center"
                            label="本月">
                    </el-table-column>
                </el-table-column>
                <el-table-column header-align="center" align="center" label="其中：微站">
                    <el-table-column
                            fixed
                            prop="sumPlanAllWz"
                            header-align="center"
                            align="center"
                            label="累计">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="sumPlanCurMonWz"
                            header-align="center"
                            align="center"
                            label="本月">
                    </el-table-column>
                </el-table-column>
                <el-table-column header-align="center" align="center" label="微站利用社会资源">
                    <el-table-column
                            fixed
                            prop="sumWxWzdwShzy"
                            header-align="center"
                            align="center"
                            label="累计">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="sumWxWzdwShzyMon"
                            header-align="center"
                            align="center"
                            label="本月">
                    </el-table-column>
                </el-table-column>
            </el-table-column>
            <el-table-column header-align="center" align="center" label="运营商提交需求数量（按站型）">
                <el-table-column header-align="center" align="center" label="提交总需求">
                    <el-table-column
                            fixed
                            prop="sumPlanType"
                            header-align="center"
                            align="center"
                            label="累计">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="sumPlanTypeMon"
                            header-align="center"
                            align="center"
                            label="本月">
                    </el-table-column>
                </el-table-column>
                <el-table-column header-align="center" align="center" label="其中：宏站">
                    <el-table-column
                            fixed
                            prop="sumPlanTypeHz"
                            header-align="center"
                            align="center"
                            label="累计">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="sumPlanTypeHzMon"
                            header-align="center"
                            align="center"
                            label="本月">
                    </el-table-column>
                </el-table-column>
                <el-table-column header-align="center" align="center" label="其中：微站">
                    <el-table-column
                            fixed
                            prop="sumPlanTypeWz"
                            header-align="center"
                            align="center"
                            label="累计">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="sumPlanTypeWzMon"
                            header-align="center"
                            align="center"
                            label="本月">
                    </el-table-column>
                </el-table-column>
            </el-table-column>
            <el-table-column header-align="center" align="center" label="运营商提交需求数量（分运营商）">
                <el-table-column header-align="center" align="center" label="移动">
                    <el-table-column
                            fixed
                            prop="sumPlanOpeYd"
                            header-align="center"
                            align="center"
                            label="累计">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="sumPlanOpeYdMon"
                            header-align="center"
                            align="center"
                            label="本月">
                    </el-table-column>
                </el-table-column>
                <el-table-column header-align="center" align="center" label="联通">
                    <el-table-column
                            fixed
                            prop="sumPlanOpeLt"
                            header-align="center"
                            align="center"
                            label="累计">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="sumPlanOpeLtMon"
                            header-align="center"
                            align="center"
                            label="本月">
                    </el-table-column>
                </el-table-column>
                <el-table-column header-align="center" align="center" label="电信">
                    <el-table-column
                            fixed
                            prop="sumPlanOpeDx"
                            header-align="center"
                            align="center"
                            label="累计">
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="sumPlanOpeDxMon"
                            header-align="center"
                            align="center"
                            label="本月">
                    </el-table-column>
                </el-table-column>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>

    import {formatDate} from '@/utils/initDateFormat'

    export default {
        name: "tower_solution_statistic",
        data() {
            return {
                dataForm: {
                    smonth: formatDate(new Date(), 'yyyyMM'),
                },
                dataList: [],
                rowIndex: '-1',
                OrderIndexArr: [],
                hoverOrderArr: [],
                dataListLoading: false,
            }
        },
        mounted() {
            this.getOrderNumber();
        },
        created() {
            this.getDataList();
        },
        methods: {
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/queryTowerSolutionStatistic'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'queryParam': this.dataForm
                    })
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataList = data.amountList;
                        this.getOrderNumber();
                    } else {
                        this.dataList = [];
                    }
                    this.dataListLoading = false
                })
            },
            //相同内容的行合并
            getOrderNumber() {
                let OrderObj = {};
                this.dataList.forEach((element, index) => {
                    element.rowIndex = index;
                    if (OrderObj[element.branch]) {
                        OrderObj[element.branch].push(index)
                    } else {
                        OrderObj[element.branch] = [];
                        OrderObj[element.branch].push(index)
                    }
                });
                // 将数组长度大于1的值 存储到this.OrderIndexArr（也就是需要合并的项）
                for (let k in OrderObj) {
                    if (OrderObj[k].length > 1) {
                        this.OrderIndexArr.push(OrderObj[k])
                    }
                }
            },
            // 合并单元格
            objectSpanMethod({row, column, rowIndex, columnIndex}) {
                if (columnIndex === 0) {
                    for (let i = 0; i < this.OrderIndexArr.length; i++) {
                        let element = this.OrderIndexArr[i];
                        for (let j = 0; j < element.length; j++) {
                            let item = element[j];
                            if (rowIndex == item) {
                                if (j == 0) {
                                    return {
                                        rowspan: element.length,
                                        colspan: 1
                                    }
                                } else if (j != 0) {
                                    return {
                                        rowspan: 0,
                                        colspan: 0
                                    }
                                }
                            }
                        }
                    }
                }
            },

            tableRowClassName({row, rowIndex}) {
                let arr = this.hoverOrderArr;
                for (let i = 0; i < arr.length; i++) {
                    if (rowIndex == arr[i]) {
                        return 'hovered-row'
                    }
                }
            },
            cellMouseEnter(row, column, cell, event) {
                this.rowIndex = row.rowIndex;
                this.hoverOrderArr = [];
                this.OrderIndexArr.forEach(element => {
                    if (element.indexOf(this.rowIndex) >= 0) {
                        this.hoverOrderArr = element
                    }
                })
            },
            cellMouseLeave(row, column, cell, event) {
                this.rowIndex = '-1';
                this.hoverOrderArr = [];
            }
        }
    }
</script>

<style scoped>

</style>