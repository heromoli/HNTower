<template>
    <div id="dxStationOwnerCutOutage_barLine" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "dxStationOwnerCutOutage_barLine",
        data() {
            return {
                chartBar: null,
                dataList: [],
            }
        },
        props: ['h'],
        watch: {
            h(val) {
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
                        text: '产权基站停电退服情况',
                        textStyle: {
                            color: '#460064'
                        }
                    },
                    color: ['#7030a0', '#f08200'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        textStyle:{
                            fontSize: 13,//字体大小
                            color: '#460064'//字体颜色
                        },
                        data: ['产权基站数', '停电退服率'],
                        bottom:'0px'
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: ['海口', '三亚', '三沙', '儋州', '琼海', '文昌', '万宁', '东方', '五指山', '乐东', '澄迈', '临高', '定安', '屯昌', '陵水', '昌江', '保亭', '琼中', '白沙'],
                            axisLabel: {
                                interval: 0,
                                rotate: 45,
                                color: '#460064'
                            },
                            axisTick: {
                                alignWithLabel: false
                            },
                            axisPointer: {
                                type: 'shadow'
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                interval: 0,
                                rotate: 0,
                                color: '#460064',

                            },
                            splitLine: {
                                show: false
                            }
                        },
                        {
                            type: 'value',
                            min: 0,
                            max: 15,
                            interval: 3,
                            axisLabel: {
                                interval: 0,
                                rotate: 0,
                                color: '#460064',
                                formatter:'{value} %'
                            },
                            splitLine: {
                                show: false
                            }
                        }
                    ],
                    series: [
                        {
                            name:'产权基站数',
                            type: 'bar',
                            barWidth: '50%',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    color: '#460064'
                                }
                            },
                            data: [4240,2230,42,1296,1002,1031,874,745,236,815,719,580,416,386,691,514,298,371,343]
                        },
                        {
                            name:'停电退服率',
                            type: 'line',
                            yAxisIndex: 1,
                            label: {
                                normal: {
                                    show: false,
                                    position: 'top',
                                    color: '#460064'
                                }
                            },
                            data: [1.0,6.1,0.0,1.2,0.6,0.6,1.8,1.3,6.8,5.3,1.4,1.2,0.2,0.8,6.1,1.4,5.0,5.4,2.0]
                        }
                    ]
                };
                this.chartBar = echarts.init(document.getElementById('dxStationOwnerCutOutage_barLine'), 'roma');
                this.chartBar.setOption(option);
                // this.$http({
                //     url: this.$http.adornUrl('/api/zhzygl/getMonthlyStation5GAmount'),
                //     method: 'get'
                // }).then(({data}) => {
                //     if (data && data.code === 0) {
                //         // console.log(data);
                //         this.chartBar.setOption({
                //             xAxis:[{
                //                 data: data.nameList
                //             }],
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
        background-color: rgb(255, 255, 255);
    }
</style>