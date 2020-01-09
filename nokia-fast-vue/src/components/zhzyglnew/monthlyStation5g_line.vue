<template>
    <div id="monthlyStation5g_chartLine" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "yearlyStation_line",
        data() {
            return {
                chartBar: null,
                dataList: [],
            }
        },
        props: ['h'],
        watch:{
            h(val){
                this.chartBar.resize();
            }
        },
        mounted() {
            this.initChartLine();
        },
        methods: {
            initChartLine() {
                var option = {
                    title: {
                        text: '每月新增5G站址',
                        textStyle: {
                            color: '#e1f3d8'
                        }
                    },
                    color: ['#1cdade'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: [],
                            axisLabel: {
                                interval: 0,
                                rotate: 45,
                                color: '#e1f3d8'
                            },
                            axisTick: {
                                alignWithLabel: false
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                interval: 0,
                                rotate: 0,
                                color: '#e1f3d8'
                            },
                            splitLine: {
                                show: false
                            }
                        }
                    ],
                    series: [
                        {
                            type: 'line',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    color: '#e1f3d8'
                                }
                            },
                            data: []
                        }
                    ]
                };
                this.chartBar = echarts.init(document.getElementById('monthlyStation5g_chartLine'), 'roma');
                this.chartBar.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getMonthlyStation5GAmount'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        // console.log(data);
                        this.chartBar.setOption({
                            xAxis:[{
                                data: data.nameList
                            }],
                            series: [{
                                data: data.amountList
                            }]
                        })
                    }
                });

//                window.addEventListener('resize', () => {
//                    this.chartBar.resize();
//                })
            }
        }
    }
</script>

<style scoped>
    .chart-box {
        height: 95%;
        /*background-color: rgba(14, 151, 244, 0.54);*/
    }
</style>