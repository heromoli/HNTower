<template>
    <div id="stationShare_chartPie" class="chart-box"></div>
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
            this.initChartPie();
        },
        methods: {
            initChartPie() {
                var option = {
                    title: {
                        text: '基站运营商',
                        left: 'center',
                        textStyle:{
                            color: '#e1f3d8',
                            fontFamily: 'Microsoft Yahei',
                            fontSize: 18
                        }

                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
//                    legend: {
//                        orient: 'vertical',
//                        x: 'right',
//                        y:0,
//                        data:['移动', '联通', '电信'],
//                        textStyle:{
//                            color:'#e1f3d8'
//                        }
//                    },
                    series: [
                        {
                            name:'分公司',
                            type:'pie',
                            // radius: ['40%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                normal: {
                                    show: true,
                                    position: 'inner',
                                    textStyle : {
                                        fontWeight : 'normal' ,
                                        lineHeight:20,
                                        fontSize : 18   //文字的字体大小
                                    },
                                    formatter:'{b}\n{c}'
                                },
//                                emphasis: {
//                                    show: true,
//                                    textStyle: {
//                                        fontSize: '20',
//                                        fontWeight: 'bold'
//                                    }
//                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            itemStyle: {
                                normal: {
                                    color: function (params) {
                                        var colorList = [
                                            '#0085d0', '#f08200', '#0527af'
                                        ];
                                        return colorList[params.dataIndex]
                                    },
                                    fontSize:18
                                }
                            },
                            data:[
                            ]
                        }
                    ]
                };
                //console.log(option);
                this.chartPie = echarts.init(document.getElementById('stationShare_chartPie'));
                this.chartPie.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getStationAloneAmount'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.chartPie.setOption({
                            series: [{
                                data: data.amountList
                            }]
                        })
                    }
                });

//                window.addEventListener('resize', () => {
//                    this.chartPie.resize();
//                })
            },

            initChartBar() {
                var option = {
                    title: {
                        // text: '基站运营商',
                        textStyle: {
                            color: '#e1f3d8'
                        }
                    },
                    // color: ['#0eccff'],
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
                                color: '#e1f3d8'
                            },
                            splitLine: {
                                show: false
                            },
                            show: false
                        }
                    ],
                    yAxis: [

                        {
                            type: 'category',
                            // data: ['移动','联通','电信','移动，联通','移动，电信','联通，电信','移动，联通，电信'],
                            data: ['移动', '联通', '电信'],
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
                    series: [
                        {
                            type: 'bar',
                            barWidth: '50%',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'inside',
                                    color: '#e1f3d8'
                                }
                            },
                            itemStyle: {
                                normal: {
                                    color: function (params) {
                                        var colorList = [
                                            '#0085d0', '#f08200', '#0527af'
                                        ];
                                        return colorList[params.dataIndex]
                                    },
                                    fontSize:18,
                                }
                            },
                            data: []
                        }
                    ]
                };
                this.chartBar = echarts.init(document.getElementById('stationShare_chartBar'), 'roma');
                this.chartBar.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getStationAloneAmount'),
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
        height: 210px;
        /*background-color: rgba(14, 151, 244, 0.54);*/
    }
</style>