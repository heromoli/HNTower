<template>
    <div id="shareRate_chartRadar" class="chart-box"></div>
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
                        text: '站址共享率',
                        left: 'left',
                        textStyle:{
                            color:'#e1f3d8'
                        }
                    },
                    tooltip: {
                        trigger: 'axis'
                        // formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'center',
                        data: ['站址共享率']
                    },
                    radar: {
                        name: {
                            textStyle: {
                                color:'#e1f3d8'
                                // backgroundColor: '#999',
                                // borderRadius: 10,
                                // padding: [2, 2]
                            }
                        },
                        shape: 'circle',
                        splitNumber: 4,
                        startAngle: 45,
                        indicator: [
                            {text: '新建', max: 0.5},
                            {text: '联通', max: 0.5},
                            {text: '电信', max: 0.5},
                            {text: '移动', max: 0.5},
                        ],
                        center: ['50%', '55%'],
                        radius: '80%'

                    },
                    series: [
                        {
                            type: 'radar',
                            tooltip: {
                                trigger: 'item'
                            },
                            itemStyle: {normal: {areaStyle: {type: 'default'}}},
                            data: [

                            ]
                        }
                    ]
                };
                this.chartPie = echarts.init(document.getElementById('shareRate_chartRadar'), 'macarons');
                this.chartPie.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getCalculateShareRate'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        console.log(data);
                        this.chartPie.setOption({
                            series: [{
                                data: [{
                                    name: '共享率',
                                    value: data.amountList
                                }]
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