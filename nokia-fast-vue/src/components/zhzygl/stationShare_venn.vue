<template>

    <div class="chart-box">
        <!--<div :id="id" :option="option"></div>-->
        <div id="high"></div>
    </div>
</template>

<script>
    import HighCharts from 'highcharts'
    import vennInit from 'highcharts/modules/venn'

    vennInit(HighCharts);

    export default {
        name: "stationShare_venn",
        // props: {
        //     id: {
        //         type: String
        //     },
        //     option: {
        //         type: Object
        //     }
        // },
        data() {
            return {
                chartVenn: null,
                dataList:[]
            }
        },
        mounted() {
            // HighCharts.chart(this.id,this.option);
            this.initChartBar();
        },
        methods: {
            initChartBar() {
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getStationShareAmount'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataList = data.amountList;
                        // console.log(this.dataList);
                        var option = {
                            chart: {
                                backgroundColor: 'rgba(14, 151, 244, 0)',
                                type: 'scatter'
                            },
                            title: {
                                text: '基站共享情况',
                                style: {
                                    color: '#e1f3d8',
                                    fontFamily: 'Microsoft Yahei',
                                    fontSize: '18px'
                                }
                            },
                            plotOptions: {
                                series: {
                                    shadow: true,
                                    borderWidth: 1
                                }
                            },
                            series: [{
                                type: 'venn',
                                name: '',
                                dataLabels: {
                                    enabled: true,
                                    inside: true,
                                    align: 'center',
                                    format: '{point.sets}<br>{point.value}',
                                },
                                data: [
                                    {
                                        name: '移动',
                                        sets: ['移动'],
                                        value: this.dataList[0]
                                    }, {
                                        name: '联通',
                                        sets: ['联通'],
                                        value: this.dataList[1]
                                    }, {
                                        name: '电信',
                                        sets: ['电信'],
                                        value: this.dataList[2]
                                    }, {
                                        name: '移动&联通',
                                        sets: ['移动', '联通'],
                                        value: this.dataList[3]
                                    }, {
                                        name: '移动&电信',
                                        sets: ['移动', '电信'],
                                        value: this.dataList[4]
                                    }, {
                                        name: '联通&电信',
                                        sets: ['联通', '电信'],
                                        value: this.dataList[5]
                                    }, {
                                        name: '移动&联通&电信',
                                        sets: ['移动', '联通', '电信'],
                                        value: this.dataList[6]
                                    }
                                ]
                            }],
                            credits: {
                                enabled: false // 禁用版权信息
                            }
                        };
                        this.chartVenn = HighCharts.chart(document.getElementById('high'), option);
                    }
                });

                window.addEventListener('resize', () => {
                    this.chartVenn.resize();
                })
            }
        }
    }
</script>

<style scoped>
    .chart-box {
        height: 420px;
        /*width:500px;*/
        /*margin:40px auto;*/
        background-color: rgba(14, 151, 244, 0.54);
    }
</style>