<template>
    <div id="J_chartMapBox" class="chart-box">
    </div>
</template>

<script>
    import hainan_json from '@/utils/460000.json'

    export default {
        name: "hainan_map",
        data() {
            return {
                newProcessesVisible: false,
                dataListLoading: false,
                chartMap: null,
                dataLength: 0,
            }
        },
        props: ['h'],
        watch:{
            h(val){
                this.chartMap.resize();
            }
        },
        mounted() {
            this.initChartMap();

            let myChart = this.$echarts.init(document.getElementById('J_chartMapBox'), 'light');
            let hourIndex = 0;
            let fhourTime = null;

            fhourTime = setInterval(function () {
                myChart.dispatchAction({
                    type: "downplay",
                    seriesIndex: 0,

                });
                myChart.dispatchAction({
                    type: "highlight",
                    seriesIndex: 0,
                    dataIndex: hourIndex
                });
                myChart.dispatchAction({
                    type: "showTip",
                    seriesIndex: 0,
                    dataIndex: hourIndex
                });
                hourIndex++;
                if (hourIndex >= 19) {
                    hourIndex = 0;
                }
            }, 3000);

            //鼠标移入停止轮播
            myChart.on("mousemove", function (e) {
                clearInterval(fhourTime);
                myChart.dispatchAction({
                    type: "downplay",
                    seriesIndex: 0,
                });
                myChart.dispatchAction({
                    type: "highlight",
                    seriesIndex: 0,
                    dataIndex: e.dataIndex
                });
                myChart.dispatchAction({
                    type: "showTip",
                    seriesIndex: 0,
                    dataIndex: e.dataIndex
                });
            });

            //鼠标移出恢复轮播
            myChart.on("mouseout", function () {
                fhourTime = setInterval(function () {
                    myChart.dispatchAction({
                        type: "downplay",
                        seriesIndex: 0,

                    });
                    myChart.dispatchAction({
                        type: "highlight",
                        seriesIndex: 0,
                        dataIndex: hourIndex
                    });
                    myChart.dispatchAction({
                        type: "showTip",
                        seriesIndex: 0,
                        dataIndex: hourIndex
                    });
                    hourIndex++;
                    if (hourIndex >= 19) {
                        hourIndex = 0;
                    }
                }, 3000);
            });
        },
        methods: {
            initChartMap() {
                let option = {
                    title: {
                        text: '海南各市县基站数',
                        left: 'center',
                        textStyle:{
                            color:'#e1f3d8'
                        }
                    },
                    textStyle: {
                        color: "#1cdade",
                        fontSize: 11,
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{b}<br/>{c}',
                        showDelay: 0,
                        transitionDuration: 0.2,
                        formatter: function (params) {
                            let value = (params.value + '').split('.');
                            value = value[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,');
                            return params.seriesName + '<br/>' + params.name + ': ' + value;
                        }
                    },
                    toolbox: {
                        //工具栏
                        show: false,
                        orient: 'vertical',
                        left: 'right',
                        top: 'top',
                        feature: {
                            dataView: {readOnly: false},
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    visualMap: {
                        min: 10,
                        max: 5000,
                        text: ['High', 'Low'],
                        realtime: false,
                        calculable: true,
                        inRange: {
                            color: ['#85daef', '#66ff0e','#fff900']
                            // color: ['#fefac9', '#66ff0e']
                            // color: ['#5475f5', '#9feaa5', '#85daef','#74e2ca', '#e6ac53', '#90ea0e']
                        }
                    },
                    series: [
                        {
                            name: '基站数',
                            type: 'map',
                            map: 'hainan',    // 自定义地图名称
                            roam: false,     //设置缩放
                            aspectScale:0.95,    //设置地图的长宽比
                            itemStyle: {
                                normal: {label: {show: true}},
                                emphasis: {label: {show: true}}
                            },
                            textFixed: {
                                Alaska: [20, -20]
                            },
                            // regions: [
                            //     {
                            //         name: "南海诸岛",
                            //         itemStyle: {
                            //             normal: {
                            //                 opacity: 0,
                            //                 label: {
                            //                     show: true
                            //                 }
                            //             }
                            //         }
                            //     }
                            // ],
                        }
                    ]
                };

                this.$echarts.registerMap('hainan', hainan_json);     //自定义地图名称
                this.chartMap = this.$echarts.init(document.getElementById('J_chartMapBox'), 'light');
                this.chartMap.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getStationAmount'),
                    method: 'get'
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataLength = data.amountList.length;
                        this.chartMap.setOption({
                            series: [{
                                data: data.amountList
                            }]
                        })
                    }
                });

//                window.addEventListener('resize', () => {
//                    this.chartMap.resize()
//                })
            }
        }
    }
</script>

<style scoped>
    .chart-box {
        height: 98%;
        /*background-color: rgba(14, 151, 244, 0.54);*/
    }
</style>