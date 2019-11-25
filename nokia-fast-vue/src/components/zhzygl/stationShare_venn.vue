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
                            // colors:['#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
                            colors:['#0085d0', '#f08200', '#0527af','#50B432', '#ED561B', '#DDDF00' ],
                            series: [{
                                type: 'venn',
                                name: '',
                                dataLabels: {
                                    enabled: true,
                                    inside: false,
                                    align: 'center',
                                    format: '{point.name}',
                                    style: {
                                        color: '#effffc',
                                        fontFamily: 'Microsoft Yahei',
                                        fontSize: '13px'
                                    }
                                },
                                data: [
                                    {
                                        name: '移动 '+this.dataList[0],
                                        sets: ['移动'],
                                        value: 2
                                    }, {
                                        name: '联通 '+this.dataList[1],
                                        sets: ['联通'],
                                        value: 2
                                    }, {
                                        name: '电信 '+this.dataList[2],
                                        sets: ['电信'],
                                        value: 2
                                    }, {
                                        name: this.dataList[3],
                                        sets: ['移动', '联通'],
                                        value: 1
                                    }, {
                                        name: this.dataList[4],
                                        sets: ['移动', '电信'],
                                        value: 1
                                    }, {
                                        name: this.dataList[5],
                                        sets: ['联通', '电信'],
                                        value: 1
                                    }, {
                                        name: this.dataList[6],
                                        sets: ['移动', '联通', '电信'],
                                        value: 1
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