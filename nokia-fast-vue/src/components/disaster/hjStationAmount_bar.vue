<template>
    <div id="dxStationAmount_bar" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "dxStationAmount_bar",
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
                        text: '基站总数',
                        textStyle: {
                            color: '#460064'
                        }
                    },
                    color: ['#92d050'],
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
                            data: ['海口','三亚','三沙','儋州','琼海','文昌','万宁','东方','五指山','乐东','澄迈','临高','定安','屯昌','陵水','昌江','保亭','琼中','白沙'],
                            axisLabel: {
                                interval: 0,
                                rotate: 45,
                                color: '#460064'
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
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    color: '#460064'
                                }
                            },
                            data: [7448,3864,46,1996,1645,1614,1383,948,381,980,1157,830,639,525,1092,635,459,507,467]
                        }
                    ]
                };
                this.chartBar = echarts.init(document.getElementById('dxStationAmount_bar'), 'roma');
                this.chartBar.setOption(option);
                // this.$http({
                //     url: this.$http.adornUrl('/api/zhzygl/getBizSceneAmount'),
                //     method: 'get'
                // }).then(({data}) => {
                //     if (data && data.code === 0) {
                //         this.chartBar.setOption({
                //             series: [{
                //                 data: data.amountList
                //             }]
                //         })
                //     }
                // });

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
        /*background-color: rgba(5, 39, 175, 0.75);*/
        background-color: rgb(255, 255, 255);
    }
</style>