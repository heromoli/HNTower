<template>
    <div id="operatorStation_chartPie" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "operatorStation_pie",
        data() {
            return {
                newProcessesVisible: false,
                dataListLoading: false,
                chartPie: null,
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
                        text: '产权归属',
                        left: 'center',
                        textStyle: {
                            color: '#e1f3d8'
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },

                    legend: {
                        orient: 'vertical',
                        x: 'left',
                        data: ['铁塔', '电信', '移动', '联通'],
                        textStyle: {
                            color: '#e1f3d8'
                        }
                    },
                    series: [
                        {
                            name: '产权归属',
                            type: 'pie',
                            radius: ['30%', '75%'],
                            avoidLabelOverlap: false,
                            color: ['#e60000', '#0527af', '#f08200', '#0085d0'],
                            //#e60000
                            label: {
                                normal: {
                                    show: true,
                                    position: 'inner',
                                    textStyle: {
                                        fontWeight: 'normal',
                                        fontSize: 13    //文字的字体大小
                                    },
                                    formatter: '{c}'
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
                            data: []
                        }
                    ]
                };
                this.chartPie = echarts.init(document.getElementById('operatorStation_chartPie'));
                this.chartPie.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getOperatorStationAmount'),
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

                window.addEventListener('resize', () => {
                    this.chartPie.resize();
                })
            }
        }
    }
</script>

<style scoped>
    .chart-box {
        height: 210px;
        background-color: rgba(14, 151, 244, 0.54);
    }
</style>