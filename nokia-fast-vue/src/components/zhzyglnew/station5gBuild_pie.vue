<template>
    <div id="station5g_chartPie" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "bizScene_bar",
        data() {
            return {
                chartBar: null,
                dataList: [],
                rData : [
                    {
                        'name': '总任务',
                        'value': 2234
                     },
                    {
                        'name': '征址',
                        'value': 1016
                    },
                    {
                        'name': '开工',
                        'value': 961
                    },
                    {
                        'name': '完工',
                        'value': 967
                    }

                ],
                rDataRate:{
                    '征址率':'45.48%',
                    '完工率':'43.29',
                }
            }
        },
        props: ['h'],
        watch:{
            h(val){
                this.chartBar.resize();
            }
        },
        mounted() {
            this.initCharPie();
        },
        methods: {
            initChartBar() {
                var option = {
                    title: {
                        text: '新增5G站址',
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
                            data: [],
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
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                interval: 0,
                                rotate: 0,
                                color: '#e1f3d8'
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
                                    color: '#e1f3d8'
                                }
                            },
                            data: []
                        }
                    ]
                };
                this.chartBar = echarts.init(document.getElementById('station5g_chartBar'), 'roma');
                this.chartBar.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getStation5GAmount'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {

                        this.chartBar.setOption({
                            xAxis: [{
                                data: data.countyList
                            }],
                            series: [{
                                data: data.amountList
                            }]
                        })
                    }
                });

//                window.addEventListener('resize', () => {
//                    this.chartBar.resize();
//                })
            },
            initCharPie(){
                var _self = this;
                var placeHolderStyle = {
                    normal: {
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        },
                        color: 'rgba(0, 0, 0, 0)',
                        borderColor: 'rgba(0, 0, 0, 0)',
                        borderWidth: 0
                    }
                };
                var data = [];
                var color = ['rgb(255, 153, 153)', 'rgb(255, 176, 63)', 'rgb(61, 186, 45)', 'rgb(43, 166, 254)'];
                var colorBorder = ['rgba(255, 153, 153, 0.4)', 'rgba(255, 176, 63, 0.4)', 'rgba(61, 186, 45, 0.4)', 'rgba(43, 166, 254, 0.4)'];
                for (var i = 0; i < _self.rData.length; i++) {
                    data.push({
                        data: _self.rData[i].value,
                        value: 20,
                        name: _self.rData[i].name,
                        itemStyle: {
                            normal: {
                                color: color[i],
                                borderWidth: 5,
                                borderColor: colorBorder[i]
                            }
                        }
                    }, {
                        value: 8,
                        name: '',
                        itemStyle: placeHolderStyle
                    });
                }
//                data.push({
//                    value: 40,
//                    name: '',
//                    itemStyle: placeHolderStyle
//                });
                var seriesObj = [{
                    name: '',
                    type: 'pie',
                    clockWise: false,
                    radius: [35, 100],
                    hoverAnimation: false,
                    startAngle: -30,
                    itemStyle: {
                        normal: {
                            label: {
                                show: true,
                                position: 'inner',
                                fontSize: 18,
                                lineHeight: 18,
                                formatter: function(params) {
                                    var percent = 0;
                                    var total = 0;
                                    for (var i = 0; i < _self.rData.length; i++) {
                                        total += _self.rData[i].value;
                                    }
                                    percent = ((params.value / total) * 100).toFixed(0);
                                    if (params.name !== '') {
                                        // return params.name + '\n' + params.data.data;
                                        if (params.name.length > 4) {
                                            return params.name.slice(0, 3) + '\n' + params.name.slice(3)+":"+params.data.data;
                                        } else {
                                            return params.name+"\n"+params.data.data;
                                        }

                                    } else {
                                        return '';
                                    }
                                },


                            },
                            labelLine: {
                                length: 30,
                                length2: 60,
                                show: true
                            }
                        }
                    },
                    data: data
                }];
               var option = {
                    //backgroundColor: '#fff',
                    tooltip: {
                        show: false
                    },
                    legend: {
                        show: false
                    },
                    toolbox: {
                        show: false
                    },
                    series: seriesObj,
                    graphic: [{
                        type: 'group',
                        left: 'center',
                        top: 'middle',
                        children: [{
                            type: 'text',
                            z: 100,
                            left: 'center',
                            top: '0',
                            style: {
                                fill: '#effffc',
                                text: [
                                    '全省'
                                ],
//                                color: '#effffc',
                                fontFamily: 'Microsoft Yahei',
                                fontSize: 18
                            }
                        },
                        ]
                    }]
                };
                this.chartBar = echarts.init(document.getElementById('station5g_chartPie'), 'roma');
                this.chartBar.setOption(option);


            },
            getBranchData() {
                this.$http({
                    url: this.$http.adornUrl('/api/report/queryBranchDailyReport'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': 1,
                        'limit': 10,
                        'queryParam': {}
                    })
                }).then(({data}) => {

                })
            },
        }
    }
</script>

<style scoped>
    .chart-box {
        height: 100%;
        /*background-color: rgba(14, 151, 244, 0.54);*/
    }
</style>