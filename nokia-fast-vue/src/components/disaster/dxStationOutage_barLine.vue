<template>
    <div id="dxStationOutage_barLine" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "dxStationOutage_barLine",
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
                        text: '基站退服情况',
                        textStyle: {
                            color: '#460064'
                        }
                    },
                    color: ['#00b0f0','#f08200'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        data: ['退服基站数', '退服率'],
                        textStyle:{
                            fontSize: 13,//字体大小
                            color: '#460064'//字体颜色
                        },
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
                            name:'退服基站数',
                            type: 'bar',
                            barWidth: '50%',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    color: '#460064'
                                }
                            },
                            data: [3,36,0,3,2,1,4,1,8,7,3,2,0,2,14,1,8,9,3]
                        },
                        {
                            name:'退服率',
                            type: 'line',
                            yAxisIndex: 1,
                            // label: {
                            //     normal: {
                            //         show: true,
                            //         position: 'top',
                            //         color: '#f08200'
                            //     }
                            // },
                            data: [0.3,5.0,0.0,0.7,0.6,0.3,1.3,0.4,8.2,3.5,1.2,1.0,0.0,1.6,5.6,0.6,7.6,6.8,2.2]
                        }
                    ]
                };
                this.chartBar = echarts.init(document.getElementById('dxStationOutage_barLine'), 'roma');
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