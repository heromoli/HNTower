<template>
    <div>
        <x-chart id="high" :option="option"></x-chart>
    </div>
</template>
<style>
</style>
<script>
    // 导入chart组件
    import XChart from '@/components/zhzygl/stationShare_venn'

    export default {
        name: "high",
        components: {
            XChart
        },
        data() {
            return {
                chartVenn: null,
                option: {
                    chart: {
                        backgroundColor: 'rgba(14, 151, 244, 0)',
                        type: 'scatter'
                    },
                    title: {
                        text: '基站共享情况',
                        style: {
                            color: '#e1f3d8',
                            fontFamily: 'Microsoft Yahei',
                            fontSize: '18px'
                        }
                    },
                    plotOptions: {
                        series: {
                            shadow: true,
                            borderWidth: 1
                        }
                    },
                    series: [{
                        type: 'venn',
                        name: '',
                        dataLabels: {
                            defer: false,
                            enabled: true,
                            inside: false,
                            align: 'center',
                            format: '<b>{point.sets} :{point.value} </b>',
                        },
                        data: [{
                            name: '移动',
                            sets: ['移动'],
                            value: 6514
                        }, {
                            name: '联通',
                            sets: ['联通'],
                            value: 2831
                        }, {
                            name: '电信',
                            sets: ['电信'],
                            value: 1743
                        }, {
                            name: '移动&联通',
                            sets: ['移动', '联通'],
                            value: 1419
                        }, {
                            name: '移动&电信',
                            sets: ['移动', '电信'],
                            value: 1044
                        }, {
                            name: '联通&电信',
                            sets: ['联通', '电信'],
                            value: 1314
                        }, {
                            name: '移动&联通&电信',
                            sets: ['移动', '联通', '电信'],
                            value: 1638,
                            // name: 'They\'re dreaming'
                        }]
                    }],
                    credits: {
                        enabled: false // 禁用版权信息
                    }
                }
            }
        },
        mounted() {
            this.initChartVenn();
        },
        methods: {
            initChartVenn() {
                this.chartVenn = Highcharts.chart(document.getElementById('stationShare_chartBar'), 'roma');
                this.chartVenn.setOption(option);
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/getStationShareAmountVenn'),
                    method: 'get'
                }).then(({data}) => {
                    console.log(data);
                    if (data && data.code === 0) {

                        // this.chartVenn.setOption({
                        //     series: [{
                        //         data: data.amountList
                        //     }]
                        // })
                    }
                });

                window.addEventListener('resize', () => {
                    this.chartVenn.resize();
                })
            }
        }
    }
</script>
