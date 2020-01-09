<template>
    <div id="cityStation_table" class="chart-box">
        <table class="bg-row1">
            <thead>
            <th style="border-radius:10px 0px 0px 10px;">产权归属</th>
            <th style="border-radius:0px 10px 10px 0px;">基站数</th>
            </thead>

            <tr v-for="item in dataList">
                <td>
                    <img v-show="item.name=='铁塔'" :src="tieta"/>
                    <img v-show="item.name=='电信'" :src="dianxin"/>
                    <img v-show="item.name=='移动'" :src="yidong"/>
                    <img v-show="item.name=='联通'" :src="liantong"/>
                   </td>
                <td>{{item.value}}</td>
            </tr>
        </table>
    </div>
</template>

<script>
    import echarts from 'echarts'
    import tieta from '@/assets/img/tieta.png'
    import dianxin from '@/assets/img/dianxin.png'
    import yidong from '@/assets/img/yidong.png'
    import liantong from '@/assets/img/liantong.png'
    export default {
        name: "cityStation_pie",
        data() {
            return {
                chartPie: null,
                dataList: [],
                dianxin:dianxin,
                yidong:yidong,
                liantong:liantong,
                tieta:tieta,
            }
        },

        mounted() {
            this.initTable();
        },
        components:{
            dianxin,
            yidong,
            liantong,
            tieta,
        },
        methods: {
            initTable(){
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getOperatorStationAmount'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataList = data.amountList;
                    }else {
                        this.dataList = [];
                    }
                });
            },

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
        line-height: 40px;
        border-bottom:1px dashed #c2c2c2;
    }
    .bg-row1 tr td img{
        height: 30px;
    }
    .bg-row1 thead {
        box-shadow: 1px 2px 3px 4px #13242c;
        border-radius:10px 10px 10px 10px;
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
    .bg-row1 thead th span{

    }
    .bg-row1 tr:nth-child(odd){
        /*background: #436473;*/
    }

</style>
