<template>
    <div id="ttStationOwner_barLine" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "ttStationOwner_barLine",
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
                        text: '产权基站停电情况',
                        textStyle: {
                            color: '#460064'
                        }
                    },
                    color: ['#cc00ff', '#f08200'],
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
                        data: ['产权基站数', '停电率'],
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
                                color: '#460064'
                            },
                            splitLine: {
                                show: false
                            }
                        },
                        {
                            type: 'value',
                            min: 0,
                            max: 10,
                            interval: 2,
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
                            data: [3792,2036,23,1088,869,885,764,630,201,678,622,488,347,328,593,434,248,301,280]
                        },
                        {
                            name:'停电率',
                            type: 'line',
                            yAxisIndex: 1,
                            label: {
                                normal: {
                                    show: false,
                                    position: 'top',
                                    color: '#460064'
                                }
                            },
                            data: [1.5,8.3,0.0,3.3,0.7,0.8,3.4,1.9,7.5,8.0,5.9,1.6,0.6,0.9,9.1,1.6,8.1,8.6,4.6]
                        }
                    ]
                };
                this.chartBar = echarts.init(document.getElementById('ttStationOwner_barLine'), 'roma');
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