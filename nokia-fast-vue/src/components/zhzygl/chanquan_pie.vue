<template>
    <div id="chanquan_chartPie" class="chart-box"></div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "canquan_pie",
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
                        textStyle:{
                            color:'#e1f3d8'
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },legend: {
                        orient: 'vertical',
                        x: 'left',
                        data:['社会资源','自建','注入','其他'],
                        textStyle:{
                            color:'#e1f3d8'
                        }
                    },
                    series: [
                        {
                            name:'访问来源',
                            type:'pie',
                            radius: ['30%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                normal: {
                                    show: false,
                                    position: 'center',
                                    formatter:'{c}'
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
                            data:[
                            ]
                        }
                    ]
                };
                this.chartPie = echarts.init(document.getElementById('chanquan_chartPie'), 'macarons');
                this.chartPie.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getChanquanAmount'),
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