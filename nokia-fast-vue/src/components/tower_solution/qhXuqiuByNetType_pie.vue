<template>
    <div id="qhXuqiuByNetType_pie" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "qhXuqiuByNetType_pie",
        data() {
            return {
                newProcessesVisible: false,
                dataListLoading: false,
                chartPie: null,
                dataList: []
            }
        },
        mounted() {
            this.initChartPie();
        },
        methods: {
            initChartPie() {
                var option = {
                    title: {
                        x: 'center',
                        y: 'center',
                        textStyle: {
                            rich: {
                                value: {
                                    color: '#303133',
                                    fontSize: 25,
                                    fontWeight: 'bold',
                                    lineHeight: 30,
                                },
                                name: {
                                    color: '#909399',
                                    fontSize: 20,
                                    lineHeight: 20
                                },
                            },
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'right',
                        textStyle: {
                            color: '#460064'
                        },
                        formatter: function (name) {
                            return name;
                        }
                    },
                    series:
                        {
                            name: '规划点位',
                            type: 'pie',
                            radius: ['50%', '80%'],
                            stillShowZeroSum: false,
                            avoidLabelOverlap: false,
                            zlevel: 1,
                            label: {
                                normal: {
                                    padding: [10, 10, 10, 10],
                                    backgroundColor: '#fff',
                                    show: false,
                                    position: 'center',
                                    formatter: [
                                        '{value|{c}}',
                                        '{name|{b}}'
                                    ].join('\n'),
                                    rich: {
                                        value: {
                                            color: '#303133',
                                            fontSize: 25,
                                            fontWeight: 'bold',
                                            lineHeight: 30,
                                        },
                                        name: {
                                            color: '#909399',
                                            fontSize: 20,
                                            lineHeight: 20
                                        }
                                    }
                                },
                                emphasis: {
                                    show: true,
                                    textStyle: {
                                        fontSize: '20',
                                        fontWeight: 'bold'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                },
                                normal: {
                                    color: function (params) {
                                        //自定义颜色
                                        var colorList = [
                                            '#07edf3', '#fa0000'
                                        ];
                                        return colorList[params.dataIndex]
                                    }
                                }
                            },
                            data: []
                        }

                };
                this.chartPie = echarts.init(document.getElementById('qhXuqiuByNetType_pie'), 'macarons');
                this.chartPie.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getXuqiuByNetType'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'cityBranchCompany': '琼海'
                    })
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        // this.totalData = data.totalData;
                        this.chartPie.setOption({
                            title: {
                                text: [
                                    '{value|' + data.totalData + '}',
                                    '{name|总计}',
                                ].join('\n')
                            },
                            legend: {
                                data: data.nameList
                            },
                            series: [{
                                data: data.amountList
                            }]
                        });
                    }
                });

                window.addEventListener('resize', () => {
                    this.chartPie.resize();
                })
            }
        }
    }
</script>

<style scoped>
    .chart-box {
        height: 240px;
        background-color: rgb(255, 255, 255);
    }
</style>