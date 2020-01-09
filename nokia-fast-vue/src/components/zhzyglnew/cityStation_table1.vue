<template>
    <div id="cityStation_table" class="chart-box">
        <table class="bg-row1">
            <thead>
            <th style="border-radius:10px 0px 0px 10px;">市县</th>
            <th style="border-radius:0px 10px 10px 0px;">基站数</th>
            </thead>

            <tr v-for="item in dataList">
                <td>{{item.name}}</td>
                <td>{{item.value}}</td>
            </tr>
        </table>
    </div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "cityStation_pie",
        data() {
            return {
                chartPie: null,
                dataList: [],
                dataListAll:[]
            }
        },
        mounted() {
            this.initTable();
        },
        props: ['h'],
        watch:{
            h(val){
                this.resizeTable(val);
            }
        },
        methods: {
            initTable(){
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getStationAmount6'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {
//                        this.dataList = data.amountList;
                        this.dataListAll = data.dataList;
                        this.resizeTable(this.h);
                    }else {
                        this.dataList = [];
                        this.dataListAll = [];
                    }

                });
            },
            resizeTable(val){
                //dataList
                let n = parseInt((val-35)/31)-1;
//                console.log(n,val);
                this.dataList = this.dataListAll.slice(0,n);
            }

        }
    }
</script>

<style scoped>
    .chart-box {
        height: 98%;
        /*background-color: rgba(14, 151, 244, 0.54);*/
    }
</style>
<style lang="scss" scoped>
    .bg-row1 {
        //background-color: #898181;
        width: 100%;
        font-size: 12px;
        margin-left: 0%;
        border-collapse:collapse;
    }
    .bg-row1 tr td{
        /*background-color: #63a2ff;*/
        color:white;
        width: 50%;
        padding: 2px;
        text-align: center;
        line-height: 31.5px;
        border-bottom:1px dashed #c2c2c2;
    }
    .bg-row1 thead{
        box-shadow:1px 2px 3px 4px #13242c;
        border-radius:10px;
        /*border: 1px solid #696;padding: 60px 0;*/

        /*text-align: center;*/

        /*-webkit-border-radius: 8px;*/

        /*-moz-border-radius: 8px;*/

        /*border-radius: 8px;*/

        /*-webkit-box-shadow: #666 0px 0px 10px;*/

        /*-moz-box-shadow: #666 0px 0px 10px;*/

        /*box-shadow: #666 0px 0px 10px;*/

        /*background: #EEFF99;*/
    }
    .bg-row1 thead th{
        /*background-color: #63a2ff;*/
        width: 50%;

        padding: 2px;
        text-align: center;
        line-height: 35px;
        border-bottom:0px dashed #c2c2c2;

        background-color: #345866;
        color:white;
        font-size: 24px;
        font-weight: bold;

    }

    .bg-row1 tr:nth-child(odd){
        /*background: #436473;*/
    }

</style>