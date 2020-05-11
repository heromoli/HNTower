<template>
    <div class="mod-demo-echarts">
        <el-row>
            <el-col :span="20">
                <div class="block">
                    <el-carousel trigger="click" height="120px">
                        <el-carousel-item v-for="item in 4" :key="item">
                            <h3>{{ item }}</h3>
                        </el-carousel-item>
                    </el-carousel>
                </div>
            </el-col>
            <el-col :span="4">
                <el-button style="float: right; padding: 50px 50px" type="success" @click="startWF">发起新需求</el-button>
            </el-col>
        </el-row>

        <el-row :gutter="20">
            <el-col :span="12">
                <el-card>
                    <div id="J_chartPieBox" class="chart-box"></div>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card>
                    <div id="J_chartScatterBox" class="chart-box"></div>
                </el-card>
            </el-col>
            <el-col :span="24">
                <el-card>
                    <div id="J_chartBarBox" class="chart-box"></div>
                </el-card>
            </el-col>
            <el-col :span="24">
                <el-card>
                    <div id="J_chartLineBox" class="chart-box"></div>
                </el-card>
            </el-col>


        </el-row>

        <new-processes v-if="newProcessesVisible" ref="newProcesses"></new-processes>
    </div>
</template>

<script>
    import NewProcesses from './new_station_demand'
    import echarts from 'echarts'
    import shine from 'echarts/theme/shine'

    export default {
        data() {
            return {
                newProcessesVisible: false,
                dataListLoading: false,
                chartLine: null,
                chartBar: null,
                chartPie: null,
                chartPieStatus: null
            }
        },
        mounted() {
            this.initChartLine();
            this.initChartBar();
            this.initChartPie();
            this.initChartPieStatus();
        },
        activated() {
            // 由于给echart添加了resize事件, 在组件激活时需要重新resize绘画一次, 否则出现空白bug
            if (this.chartLine) {
                this.chartLine.resize()
            }
            if (this.chartBar) {
                this.chartBar.resize()
            }
            if (this.chartPie) {
                this.chartPie.resize()
            }
            if (this.chartPieStatus) {
                this.chartPieStatus.resize()
            }
        },
        components: {
            NewProcesses
        },
        methods: {
            // 折线图
            initChartLine() {
                var option = {
                    'title': {
                        'text': '分公司月建站统计'
                    },
                    'tooltip': {
                        'trigger': 'axis'
                    },
                    'legend': {
                        'data': ['儋州', '三亚', '琼海', '海口']
                    },
                    'grid': {
                        'left': '3%',
                        'right': '4%',
                        'bottom': '3%',
                        'containLabel': true
                    },
                    'toolbox': {
                        'feature': {
                            'saveAsImage': {}
                        }
                    },
                    'xAxis': {
                        'type': 'category',
                        'boundaryGap': false,
                        'data': ['2019-01', '2019-02', '2019-03', '2019-04', '2019-05', '2019-06', '2019-07']
                    },
                    'yAxis': {
                        'type': 'value'
                    },
                    'series': [
                        {
                            'name': '海口',
                            'type': 'line',
                            'stack': '总量',
                            'data': [120, 132, 101, 134, 90, 230, 210]
                        },
                        {
                            'name': '三亚',
                            'type': 'line',
                            'stack': '总量',
                            'data': [220, 182, 191, 234, 290, 330, 310]
                        },
                        {
                            'name': '琼海',
                            'type': 'line',
                            'stack': '总量',
                            'data': [150, 232, 201, 154, 190, 330, 410]
                        },
                        {
                            'name': '儋州',
                            'type': 'line',
                            'stack': '总量',
                            'data': [320, 332, 301, 334, 390, 330, 320]
                        }
                    ]
                };
                this.chartLine = echarts.init(document.getElementById('J_chartLineBox'), 'shine');
                this.chartLine.setOption(option);
                window.addEventListener('resize', () => {
                    this.chartLine.resize()
                })
            },
            // 柱状图
            initChartBar() {
                var option = {
                    title: {
                        text: '铁塔种类'
                    },
                    tooltip: {

                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {
                        data: ['地面塔', '楼面塔', '角钢塔', '楼面抱杆', '简易塔', '景观塔', '美化天线', '仿生树', '广告牌']
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
                            data: ['海口', '三亚', '琼海', '儋州']
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '地面塔',
                            type: 'bar',
                            data: [320, 332, 301, 334, 390, 330, 320]
                        },
                        {
                            name: '简易塔',
                            type: 'bar',
                            stack: '楼面塔',
                            data: [120, 132, 101, 134, 90, 230, 210]
                        },
                        {
                            name: '角钢塔',
                            type: 'bar',
                            stack: '楼面塔',
                            data: [220, 182, 191, 234, 290, 330, 310]
                        },
                        {
                            name: '楼面抱杆',
                            type: 'bar',
                            stack: '楼面塔',
                            data: [150, 232, 201, 154, 190, 330, 410]
                        },
                        {
                            name: '楼面塔',
                            type: 'bar',
                            data: [862, 1018, 964, 1026, 1000, 1000, 1070],
                            markLine: {
                                lineStyle: {
                                    normal: {
                                        type: 'dashed'
                                    }
                                },
                                data: [
                                    [{type: 'min'}, {type: 'max'}]
                                ]
                            }
                        },
                        {
                            name: '景观塔',
                            type: 'bar',
                            barWidth: 5,
                            stack: '景观塔',
                            data: [620, 732, 701, 734, 1090, 1130, 1120]
                        },
                        {
                            name: '美化天线',
                            type: 'bar',
                            stack: '景观塔',
                            data: [120, 132, 101, 134, 290, 230, 220]
                        },
                        {
                            name: '仿生树',
                            type: 'bar',
                            stack: '景观塔',
                            data: [60, 72, 71, 74, 190, 130, 110]
                        },
                        {
                            name: '广告牌',
                            type: 'bar',
                            stack: '景观塔',
                            data: [62, 82, 91, 84, 109, 110, 120]
                        }
                    ]
                };
                this.chartBar = echarts.init(document.getElementById('J_chartBarBox'), 'shine');
                this.chartBar.setOption(option);
                window.addEventListener('resize', () => {
                    this.chartBar.resize()
                })
            },
            // 饼状图
            initChartPie() {
                var option = {
                    backgroundColor: '#fbfff4',
                    title: {
                        text: '各地市工单',
                        left: 'center',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['海口', '三亚', '琼海', '儋州']
                    },
                    series: [
                        {
                            name: '需求来源',
                            type: 'pie',
                            radius: '75%',
                            center: ['50%', '60%'],
                            data: [],
                            //     .sort(function (a, b) {
                            //     return a.value - b.value
                            // })
                            // roseType: 'radius',
                            label: {
                                normal: {
                                    textStyle: {
                                        color: 'rgba(255, 255, 255, 0.3)'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    lineStyle: {
                                        color: 'rgba(255, 255, 255, 0.3)'
                                    },
                                    smooth: 0.2,
                                    length: 10,
                                    length2: 20
                                }
                            },
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                this.chartPie = echarts.init(document.getElementById('J_chartPieBox'), 'shine');
                this.chartPie.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/wf/chartBranchCompanyCount'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.chartPie.setOption({
                            series: [{
                                data: data.resultData
                            }]
                        })
                    }
                });

                window.addEventListener('resize', () => {
                    this.chartPie.resize();
                })
            },
            // 散点图
            initChartPieStatus() {
                var option = {
                    backgroundColor: '#fbfff4',
                    title: {
                        text: '工单状态',
                        left: 'center',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['需求收集', '站址筛查', '建站确认', '需求变更']
                    },
                    series: [
                        {
                            name: '工单状态',
                            type: 'pie',
                            radius: '75%',
                            center: ['50%', '60%'],
                            data: [],
                            //     .sort(function (a, b) {
                            //     return a.value - b.value
                            // })
                            // roseType: 'radius',
                            label: {
                                normal: {
                                    textStyle: {
                                        color: 'rgba(255, 255, 255, 0.3)'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    lineStyle: {
                                        color: 'rgba(255, 255, 255, 0.3)'
                                    },
                                    smooth: 0.2,
                                    length: 10,
                                    length2: 20
                                }
                            },
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                this.chartPieStatus = echarts.init(document.getElementById('J_chartScatterBox'), 'shine');
                this.chartPieStatus.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/wf/chartTaskStatusCount'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.chartPieStatus.setOption({
                            series: [{
                                data: data.resultData
                            }]
                        })
                    }
                });
            },
            startWF() {
                this.newProcessesVisible = true;
                this.$nextTick(() => {
                    this.$refs.newProcesses.init()
                })
            }
        }

    }
</script>
<style lang="scss" scoped>
    .site-wrapper.site-page--lcgl {
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        width: 100%;
        height: 100%;
        z-index: 0;
        background-image: url('~@/assets/img/login_bg_bak.jpg');
        overflow: hidden;
    }

    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 150px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }

    .mod-demo-echarts {
        > .el-alert {
            margin-bottom: 10px;
        }
        > .el-row {
            margin-top: -10px;
            margin-bottom: -10px;
            .el-col {
                padding-top: 10px;
                padding-bottom: 10px;
            }
        }
        .chart-box {
            min-height: 400px;
        }
    }
</style>
