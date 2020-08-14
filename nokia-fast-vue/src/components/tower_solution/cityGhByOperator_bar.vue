<template>
    <div id="cityGhByOperator_bar" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "cityGhByOperator_bar",
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
                        text: '规划点位运营商分类',
                        textStyle: {
                            color: '#460064'
                        }
                    },
                    color: ['#0527af', '#f08200', '#0085d0'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        data: ['移动', '联通', '电信']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis:
                        {
                            type: 'category',
                            data: ['琼海分公司', '海口分公司', '儋州分公司', '三亚分公司'],
                            axisLabel: {
                                interval: 0,
                                rotate: 0,
                                color: '#460064'
                            },
                            axisTick: {
                                alignWithLabel: true
                            }
                        },
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                interval: 0,
                                rotate: 0,
                                color: '#460064'
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
                            data: []
                        }
                    ]
                };
                this.chartBar = echarts.init(document.getElementById('cityGhByOperator_bar'), 'roma');
                this.chartBar.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getCityGuihuaByOperator'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.chartBar.setOption({
                            series: [
                                {
                                    name: '移动',
                                    type: 'bar',
                                    stack: '总量',
                                    label: {
                                        show: true,
                                        position: 'inside'
                                    },
                                    data: data.amountListYd
                                },
                                {
                                    name: '联通',
                                    type: 'bar',
                                    stack: '总量',
                                    label: {
                                        show: true,
                                        position: 'inside'
                                    },
                                    data: data.amountListLt
                                },
                                {
                                    name: '电信',
                                    type: 'bar',
                                    stack: '总量',
                                    label: {
                                        show: true,
                                        position: 'inside'
                                    },
                                    data: data.amountListDx
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
        height: 320px;
        background-color: rgb(255, 255, 255);
    }
</style>