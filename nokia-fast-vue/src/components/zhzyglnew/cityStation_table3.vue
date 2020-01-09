<template>
    <div id="cityStation_table" class="chart-box">
        <table class="bg-row1">
            <thead>
            <th><span type="success">分公司</span></th>
            <th><span type="success">征址率</span></th>
            <th><span type="success">完工率</span></th>
            </thead>

            <tr v-for="item in dataList">
                <td>{{item.name}}</td>
                <td>{{item.v1}}</td>
                <td>{{item.v2}}</td>
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
                dataList: [
                    {'name':'海口','v1':'60.47%','v2':'53.1%'},
                    {'name':'琼海','v1':'39.01%','v2':'45.81%'},
                    {'name':'三亚','v1':'31.83%','v2':'31.83%'},
                    {'name':'儋州','v1':'24.61%','v2':'24.61%'},
                    {'name':'全省','v1':'45.48%','v2':'43.29%'},
                ],
                dianxin:dianxin,
                yidong:yidong,
                liantong:liantong,
                tieta:tieta,
            }
        },

        mounted() {
//            this.initTable();
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
        width: 30%;
        padding: 2px;
        text-align: center;
        line-height: 35px;
        border-bottom:1px dashed #c2c2c2;
    }
    .bg-row1 tr td img{
        height: 30px;
    }
    .bg-row1 thead th{
        /*background-color: #63a2ff;*/
        width: 30%;
        padding: 2px;
        text-align: center;
        line-height: 35px;
        border-bottom:1px dashed #c2c2c2;
        background-color: #345866;

    }
    .bg-row1 thead th span{
        color:white;
        font-size: 18px;
        font-weight: bold;
    }
    .bg-row1 tr:nth-child(odd){
        /*background: #436473;*/
    }

</style>
