<template>
    <div id="bizScene_chartBar" class="chart-box"></div>
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
        mounted() {
            this.initChartBar();
        },
        methods: {
            initChartBar() {
                var option = {
                    title: {
                        text: '站址场景分布',
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
                            data: ['一般市区', '密集市区', '县城', '乡镇', '农村', '住宅小区', '3A以上景区', '其他景区', '校园', '工业园', '室分', '商业市场', '高速', '高铁线', '国道', '其他交通干线'],
                            axisLabel: {
                                interval: 0,
                                rotate: 0,
                                color:'#e1f3d8'
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
                                color:'#e1f3d8'
                            },
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
                this.chartBar = echarts.init(document.getElementById('bizScene_chartBar'), 'roma');
                this.chartBar.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getBizSceneAmount'),
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
        height: 300px;
        background-color: rgba(14, 151, 244, 0.54);
    }
</style>