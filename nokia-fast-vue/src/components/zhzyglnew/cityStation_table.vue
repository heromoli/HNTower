<template>
    <div id="cityStation_table" class="chart-box">
        <el-table
                :data="dataList"
                border
                :row-style="tableRowClassName"
                size="mini"
                style="background-color: #fffe8d;">
            <el-table-column
                    fixed
                    prop="name"
                    header-align="center"
                    align="center"
                    label="市县">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="value"
                    align="center"
                    header-align="center"
                    label="基站数">
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "cityStation_pie",
        data() {
            return {
                chartPie: null,
                dataList: [],
            }
        },
        mounted() {
            this.initTable();
        },
        methods: {
            initTable() {
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getStationAmount6'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataList = data.dataList;
                    }
                });

                window.addEventListener('resize', () => {
                    this.chartPie.resize();
                })
            },
            tableRowClassName({row, rowIndex}) {
                if (rowIndex === 1) {
                    return 'warning-row';
                } else if (rowIndex === 3) {
                    return 'success-row';
                }
                return '';
            },
            tableHeaderColor({row, rowIndex}) {
                if (rowIndex === 0) {
                    return 'background-color: lightblue;color: #fff;font-weight: 500;'
                }
            }
        }
    }
</script>

<style scoped>
    .chart-box {
        height: 210px;
        background-color: rgba(14, 151, 244, 0.54);
    }

    .el-table__header th, .el-table__header tr {
        background-color: #17B3A3;
        color: black;
    }
    .el-table td{
        background-color: rgba(204, 204, 204, .3);
    }
</style>