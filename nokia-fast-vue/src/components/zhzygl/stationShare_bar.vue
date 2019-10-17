<template>
    <div id="stationShare_chartBar" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "stationShare_bar",
        data() {
            return {
                chartBar: null,
                dataList: [],
            }
        },
        mounted() {
            this.initChartBar();
        },
        methods: {
            initChartBar() {
                var option = {
                    title: {
                        text: '基站运营商',
                        textStyle:{
                            color:'#e1f3d8'
                        }
                    },
                    color: ['#0eccff'],
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
                            type: 'value',
                            boundaryGap: [0, 0.01],
                            axisLabel: {
                                interval: 0,
                                rotate: 0,
                                color:'#e1f3d8'
                            }
                        }
                    ],
                    yAxis: [

                        {
                            type: 'category',
                            data: ['移动','联通','电信','移动，联通','移动，电信','联通，电信','移动，联通，电信'],
                            axisLabel: {
                                interval:0,
                                rotate:0,
                                color:'#e1f3d8'
                            },
                            axisTick: {
                                alignWithLabel: true
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
                                    position: 'right',
                                    color:'#e1f3d8'
                                }
                            },
                            data: []
                        }
                    ]
                };
                this.chartBar = echarts.init(document.getElementById('stationShare_chartBar'), 'roma');
                this.chartBar.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getStationShareAmount'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.chartBar.setOption({
                            series: [{
                                data: data.amountList
                            }]
                        })
                    }
                });

                window.addEventListener('resize', () => {
                    this.chartBar.resize();
                })
            }
        }
    }
</script>

<style scoped>
    .chart-box {
        height: 420px;
        background-color: rgba(14, 151, 244, 0.54);
    }
</style>