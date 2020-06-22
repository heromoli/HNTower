<template>
    <div id="dxStationRepair_barLine" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "dxStationRepair_barLine",
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
                        text: '退服基站修复情况',
                        textStyle: {
                            color: '#460064'
                        }
                    },
                    color: ['#007ac0','#f08200','#00b0f0'],
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
                        data: ['累计退服基站', '修复数', '修复率'],
                        bottom:'0px'
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
                                color: '#460064'
                            },
                            splitLine: {
                                show: false
                            }
                        },
                        {
                            type: 'value',
                            min: 50,
                            max: 100,
                            interval: 10,
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
                            name:'累计退服基站',
                            type: 'bar',
                            barWidth: '50%',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    color: '#460064'
                                }
                            },
                            data: [143,1102,12,306,208,117,353,191,133,224,156,148,22,102,393,132,173,222,157]
                        },
                        {
                            name:'修复数',
                            type: 'line',
                            label: {
                                normal: {
                                    show: false,
                                    position: 'top',
                                    color: '#f08200'
                                }
                            },
                            data: [124,885,12,287,194,104,326,176,110,181,140,140,22,84,333,122,152,192,144]
                        },
                        {
                            name:'修复率',
                            type: 'line',
                            yAxisIndex:1,
                            label: {
                                normal: {
                                    show: false,
                                    position: 'top',
                                    color: '#460064'
                                }
                            },
                            data: [86.7,80.3,100.0,93.8,93.3,88.9,92.4,92.1,82.7,80.8,89.7,94.6,100.0,82.4,84.7,92.4,87.9,86.5,91.7]
                        }
                    ]
                };
                this.chartBar = echarts.init(document.getElementById('dxStationRepair_barLine'), 'roma');
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