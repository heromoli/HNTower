<template>
    <div id="ydStationRepair_barLine" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "ydStationBreakOpt_barLine",
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
                            data: [46,420,6,132,72,69,159,110,68,100,67,92,6,57,147,74,75,70,66]
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
                            data: [39,341,6,121,61,59,139,96,59,84,58,87,6,49,124,66,64,62,56]
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
                            data: [84.8,81.2,100.0,91.7,84.7,85.5,87.4,87.3,86.8,84.0,86.6,94.6,100.0,86.0,84.4,89.2,85.3,88.6,84.8]
                        }
                    ]
                };
                this.chartBar = echarts.init(document.getElementById('ydStationRepair_barLine'), 'roma');
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