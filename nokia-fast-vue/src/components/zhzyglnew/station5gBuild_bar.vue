<template>
    <div id="station5g_chartBar" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "bizScene_bar",
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
            this.initChartBar();
        },
        methods: {
            initChartBar() {
                var option = {
                    title: {
                        text: '新增5G站址',
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
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: [],
                            axisLabel: {
                                interval: 0,
                                rotate: 0,
                                color: '#e1f3d8'
                            },
                            axisTick: {
                                alignWithLabel: true
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
                            type: 'bar',
                            barWidth: '50%',
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
                this.chartBar = echarts.init(document.getElementById('station5g_chartBar'), 'roma');
                this.chartBar.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getStation5GAmount'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {

                        this.chartBar.setOption({
                            xAxis: [{
                                data: data.countyList
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