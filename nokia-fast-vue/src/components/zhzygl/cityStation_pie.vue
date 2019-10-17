<template>
    <div id="cityStation_chartPie" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "cityStation_pie",
        data() {
            return {
                chartPie: null,
                dataList: [],
            }
        },
        mounted() {
            this.initChartPie();
        },
        methods: {
            initChartPie() {
                var option = {
                    title: {
                        text: '主要行政区县基站',
                        left: 'center',
                        textStyle:{
                            color:'#e1f3d8'
                        }

                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'left',
                        data:[],
                        textStyle:{
                            color:'#e1f3d8'
                        }
                    },
                    series: [
                        {
                            name:'区县',
                            type:'pie',
                            radius: ['30%', '75%'],
                            avoidLabelOverlap: false,
                            label: {
                                normal: {
                                    show: true,
                                    position: 'inner',
                                    textStyle : {
                                        fontWeight : 'normal' ,
                                        fontSize : 13   //文字的字体大小
                                    },
                                    formatter:'{c}'
                                },
                                emphasis: {
                                    show: true,
                                    textStyle: {
                                        fontSize: '20',
                                        fontWeight: 'bold'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            data:[]
                        }
                    ]
                };
                this.chartPie = echarts.init(document.getElementById('cityStation_chartPie'));
                this.chartPie.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getStationAmount6'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.chartPie.setOption({
                            series: [{
                                data: data.amountList
                            }],
                            legend:{
                                data:data.regionNameList
                            }
                        })
                    }
                });

                window.addEventListener('resize', () => {
                    this.chartPie.resize();
                })
            }
        }
    }
</script>

<style scoped>
    .chart-box {
        height: 210px;
        background-color: rgba(14, 151, 244, 0.54);
    }
</style>