document.write("<script type='text/javascript' src='asset/data.js'></script>");
document.write("<script type='text/javascript' src='asset/loaddata.js'></script>");

var time_len = 5 * 60 * 1000;

$(function () {

    setInterval(init, time_len);
    init();
});

function init() {
    $(".loading").fadeOut();
    pagelogin();
    startClock();
    // loss();
    left_up();
    left_center();
    left_down();
    center_up();
    center_down();
    righ_up();
    righ_down();
    document.getElementById('zhezhao').style.display = "none";

}

// 刷新界面元素高度
function pagelogin() {
    var h = document.body.scrollHeight;
    var h1 = $(".header").height();
    $(".contain").height((h - 16 - h1 - 10) + 'px');
    $(".tianqi").width($('body').width() + 'px');

    var ch = $(".contain").height() - 6 * 7;
    $(".row-3").height(ch / 3 + 'px');
    $(".row-2").height(ch * 2 / 3 + 14 + 'px');
    $(".main").height('100%');
    $(".datadiv").height('100%');
    var chart = $(".main").height() - $(".divtitle").height();
    $("#left1").height(chart - 5 + 'px');
    $("#left3").height(chart - 5 + 'px');
    $("#center11").height($(".row-3").height() + 50 + 'px');
    $("#center12").height($(".row-3").height() -50 + 'px');
    $("#center21").height('100%');
    $("#center22").height(chart - 55 + 'px');
    $("#right11").height($(".row-2").height() * 2 / 3 + 'px');
    $("#right2").height(chart - 5 + 'px');
}

window.onresize = function () {
    pagelogin()
};

// 时钟》》》》》》》》》》》》》》》》》》》》
var timer = null;

function displayClock(num) { //num是传入的startClock中的动态值
    if (num < 10) {
        return "0" + num;
    } else {
        return num;
    }
}

function startClock() {
    var time = new Date();
    var month = time.getMonth() + 1;
    var hours = displayClock(time.getHours()) + ":";
    var minutes = displayClock(time.getMinutes()) + ":";
    var seconds = displayClock(time.getSeconds());
    //显示时间
    stime.innerHTML = time.getFullYear() + "-" + month + "-" + time.getDate() + " " + hours + minutes + seconds; //在id为show的块区域显示
    timer = setTimeout("startClock()", 1000); //延时器
}

// 时钟《《《《《《《《《《《《《《《《《《《《《《


// 加载柱状图
function loadleft1(divid, data) {
    var myChartbarline = echarts.init(document.getElementById(divid));
    var option = {
        title: {
            text: ''
        },
        grid: {
            top: '10%',
            left: '5%',
            width: '90%',
            height: '60%',

        },
        tooltip: {
            show: true
        },
        animation: false,
        "xAxis": [{
            "type": "category",
            "data": data.regionNameList,
            "axisTick": {
                "alignWithLabel": true
            },
            "nameTextStyle": {
                "color": "#82b0ec"
            },
            "axisLine": {
                show: false,
                "lineStyle": {
                    "color": "#82b0ec"
                }
            },
            "axisLabel": {
                "textStyle": {
                    "color": "#fff",
                    fontSize: 10
                },
                margin: 30
            }
        }],
        "yAxis": [{
            show: false,
            "type": "value",
            "axisLabel": {
                "textStyle": {
                    "color": "#fff"
                },
            },
            "splitLine": {
                "lineStyle": {
                    "color": "#0c2c5a"
                }
            },
            "axisLine": {
                "show": false
            }
        }],
        "series": [{
                "name": "",
                type: 'pictorialBar',
                symbolSize: [20, 10],
                symbolOffset: [0, -6],
                symbolPosition: 'end',
                z: 6,
                // "barWidth": "0",
                "label": {
                    "normal": {
                        "show": true,
                        "position": "top",
                        // "formatter": "{c}%"
                        fontSize: 10,
                        fontWeight: 'bold',
                        color: '#34DCFF'
                    }
                },
                color: "#2DB1EF",
                data: data.amountList
            },
            {
                name: '',
                type: 'pictorialBar',
                symbolSize: [20, 10],
                symbolOffset: [0, 7],
                // "barWidth": "20",
                z: 6,
                "color": "#2DB1EF",
                "data": data.amountList
            },
            {
                name: '',
                type: 'pictorialBar',
                symbolSize: [30, 15],
                symbolOffset: [0, 12],
                z: 5,
                itemStyle: {
                    normal: {
                        color: 'transparent',
                        borderColor: '#2EA9E5',
                        borderType: 'solid',
                        borderWidth: 1
                    }
                },
                data: data.amountList
            },
            {
                name: '',
                type: 'pictorialBar',
                symbolSize: [40, 20],
                symbolOffset: [0, 18],
                z: 5,
                itemStyle: {
                    normal: {
                        color: 'transparent',
                        borderColor: '#19465D',
                        borderType: 'solid',
                        borderWidth: 2
                    }
                },
                data: data.amountList
            },
            {
                type: 'bar',
                //silent: true,
                "barWidth": "20",
                barGap: '10%', // Make series be overlap
                barCateGoryGap: '10%',
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 0.7, [{
                                offset: 0,
                                color: "#38B2E6"
                            },
                            {
                                offset: 1,
                                color: "#07d2f1"
                            }
                        ]),
                        opacity: .8
                    },
                },
                data: data.amountList
            }
        ]
    };

    myChartbarline.setOption(option);

    window.addEventListener("resize", () => {
        myChartbarline.resize();
    });
}


function loadleft2(divid, dataList) {
    var data = dataList.amountList
    var str = '';
    if (data.length > 0) {
        str = str + '<table class="jingtai_top">';
        str = str + '	<thead>';
        str = str + '		<tr>';
        for (let i in data[0]) {
            if (i == 'name') {
                str = str + '			<th >运营商</th>';
            } else {
                str = str + '			<th >基站数量</th>';
            }
        }
        str = str + '		</tr>';
        str = str + '	</thead>';
        str = str + '</table>';
        str = str + '<div class="jingtai_box">';
        str = str + '	<table class="jingtai_tab">';
        str = str + '		<tbody>';
        for (let i = 0; i < data.length; i++) {
            str = str + '			<tr>';
            for (let j in data[i]) {
                if (j == 'name') {
                    if (data[i][j] == '联通') {
                        str = str + '			<td ><img src="asset/icon/liantong.png"></img></td>';
                    } else if (data[i][j] == '电信') {
                        str = str + '			<td ><img src="asset/icon/dianxin.png"></img></td>';
                    } else if (data[i][j] == '移动') {
                        str = str + '			<td ><img src="asset/icon/yidong.png"></img></td>';
                    } else if (data[i][j] == '铁塔') {
                        str = str + '			<td ><img src="asset/icon/tieta.png"></img></td>';
                    }
                } else if (j == 'value') {
                    str = str + '			<td >' + data[i][j] + '</td>';
                } else {

                }

            }
            str = str + '			</tr>';
        }
        str = str + '		</tbody>';
        str = str + '	</table>';
        str = str + '</div>';

        $("#" + divid).html(str);


    } else {
        $('#' + divid).html("无数据");
    }
}

function loadleft3(divid, data1, data2) {
    var myChartbarline = echarts.init(document.getElementById(divid));

    var option = {
        "tooltip": {
            "trigger": "axis",
            "axisPointer": {
                "type": "shadow",
                textStyle: {
                    color: "#fff"
                }

            },
        },
        grid: {
            top: '10%',
            left: '5%',
            width: '90%',
            height: '65%',

        },
        "calculable": true,
        "xAxis": [{
            "type": "category",
            "axisLine": {
                lineStyle: {
                    color: '#fff'
                }
            },
            "splitLine": {
                "show": false
            },
            "axisTick": {
                "show": false
            },
            "splitArea": {
                "show": false
            },
            "axisLabel": {
                "interval": 0,
                color: '#fff',
                fontSize: 10
            },
            "data": data1.nameList,
        }],
        "yAxis": [{
            "type": "value",
            "splitLine": {
                "show": false
            },
            // "axisTick": {
            //     "show": false
            // },
            // "axisLine": {
            //     "show": false
            // },
            // "splitArea": {
            //     "show": false
            // },
            "axisLabel": {
                "show": false,
                // "interval": 0,
                // color: '#fff',
                // fontSize: 10
            },
        }],
        series: [{
                name: "总量",
                type: "bar",
                stack: "个",
                barMaxWidth: 25,
                barGap: "10%",

                itemStyle: {
                    normal: {
                        color: {
                            type: 'linear',
                            x: 0,
                            y: 0,
                            x2: 0,
                            y2: 1,
                            colorStops: [{
                                offset: 0,
                                color: 'rgba(35, 157, 250, 1)' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: 'rgba(35, 157, 250, 0)' // 100% 处的颜色
                            }],
                            global: false // 缺省为 false
                        }
                    }
                },
                data: data1.amountList
            },

            {
                name: "新增",
                type: "bar",
                stack: "个",
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        color: '#fff'
                    }
                },
                itemStyle: {
                    normal: {
                        color: {
                            type: 'linear',
                            x: 0,
                            y: 0,
                            x2: 0,
                            y2: 1,
                            colorStops: [{
                                offset: 0,
                                color: 'rgba(35, 250, 187, 1)' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: 'rgba(35, 250, 187, 0)' // 100% 处的颜色
                            }],
                            global: false // 缺省为 false
                        },
                        "barBorderRadius": 0
                    }
                },
                "data": data2.amountList
            }
        ]
    }
    myChartbarline.setOption(option);

    window.addEventListener("resize", () => {
        myChartbarline.resize();
    });
}

// 加载地图
function loadcenter11(divid, data) {
    var dataNew = [];
    for (var i = 0; i < data.amountList.length; i++) {
        dataNew.push({
            name: data.amountList[i].name.replace("市", "").replace("黎族自治县", "").replace("黎族苗族自治县", "").replace("县", ""),
            value: data.amountList[i].value
        })
    }
    echarts.registerMap('hainan', hainanJson);
    var myMapChart = echarts.init(document.getElementById(divid));
    var option = {
        title: {
            text: '海南各市县基站数',
            left: 'center',
            top: 5,
            textStyle: {
                color: '#fff',
            }
        },
        textStyle: {
            color: "#1cdade",
            fontSize: 12,
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
                dataView: {
                    readOnly: false
                },
                restore: {},
                saveAsImage: {}
            }
        },
        visualMap: {
            min: 100,
            max: 4000,
            // text: ['High', 'Low'],
            realtime: false,
            calculable: true,
            textStyle: {
                color: '#fff',
            },
            inRange: {
                // color: ['#85daef', '#5475f5', '#06c']
                color: ['#92cbf5', '#1F3FAE']
                // color: ['#5475f5', '#9feaa5', '#85daef','#74e2ca', '#e6ac53', '#90ea0e']
            },

        },
        series: [{
            name: '基站数',
            type: 'map',
            map: 'hainan', // 自定义地图名称
            roam: false, //设置缩放
            aspectScale: 0.9, //设置地图的长宽比
            left: 50,
            data: dataNew,
            itemStyle: {
                normal: {
                    label: {
                        show: true,
                        color: "#fff",
                        fontSize: 10
                    },
                    areaColor: '#01215c',
                    borderWidth: 1, //设置外层边框
                    borderColor: '#fff',
                    shadowColor: 'rgba(0,54,255, 1)',
                    shadowBlur: 150
                },
                emphasis: {
                    label: {
                        show: true
                    }
                }
            },
            textFixed: {
                // Alaska: [20, -20]
            },
        }]
    };

    myMapChart.setOption(option);
    window.addEventListener("resize", () => {
        myMapChart.resize();
    });
}

function loadcenter12(divid, data) {
    var myMapChart = echarts.init(document.getElementById(divid));
    var d = [];
    var color = ['rgb(255, 153, 153)', 'rgb(255, 176, 63)', 'rgb(61, 186, 45)', 'rgb(43, 166, 254)'];
    var colorBorder = ['rgba(255, 153, 153, 0.4)', 'rgba(255, 176, 63, 0.4)', 'rgba(61, 186, 45, 0.4)', 'rgba(43, 166, 254, 0.4)'];
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
    for (var i = 0; i < data.length; i++) {
        d.push({
            data: data[i].value,
            value: 20,
            name: data[i].name,
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
        series: [{
            name: '',
            type: 'pie',
            clockWise: false,
            radius: [35, 90],
            hoverAnimation: false,
            startAngle: -30,
            itemStyle: {
                normal: {
                    label: {
                        show: true,
                        position: 'inner',
                        fontSize: 18,
                        lineHeight: 18,
                        formatter: function (params) {
                            var percent = 0;
                            var total = 0;
                            for (var i = 0; i < data.length; i++) {
                                total += data[i].value;
                            }
                            percent = ((params.value / total) * 100).toFixed(0);
                            if (params.name !== '') {
                                // return params.name + '\n' + params.data.data;
                                if (params.name.length > 4) {
                                    return params.name.slice(0, 3) + '\n' + params.name.slice(3) + ":" + params.data.data;
                                } else {
                                    return params.name + "\n" + params.data.data;
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
            data: d
        }],
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
                    fontFamily: 'Microsoft Yahei',
                    fontSize: 18
                }
            }, ]
        }]
    };

    myMapChart.setOption(option);
    window.addEventListener("resize", () => {
        myMapChart.resize();
    });
}

function loadcenter21(divid, data) {
    var myChartbarline = echarts.init(document.getElementById(divid));

    var option = {
        title: {
            text: '新增5G站址',
            textStyle: {
                color: '#fff',
                fontSize: 16
            }
        },
        color: ['#1cdade'],
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: data.countyList,
            axisLabel: {
                interval: 0,
                rotate: 90,
                color: '#fff'
            },
            axisTick: {
                alignWithLabel: true
            }
        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                show: false,
                interval: 0,
                rotate: 0,
                color: '#fff'
            },
            splitLine: {
                show: false
            }
        }],
        series: [{
            type: 'bar',
            barWidth: '50%',
            label: {
                normal: {
                    show: true,
                    position: 'top',
                    color: '#fff'
                }
            },
            itemStyle: {
                normal: {
                    color: {
                        type: 'linear',
                        x: 0,
                        y: 0,
                        x2: 0,
                        y2: 1,
                        colorStops: [{
                            offset: 0,
                            color: 'rgba(35, 157, 250, 1)' // 0% 处的颜色
                        }, {
                            offset: 1,
                            color: 'rgba(35, 157, 250, 0)' // 100% 处的颜色
                        }],
                        global: false // 缺省为 false
                    }
                }
            },
            data: data.amountList
        }]
    }
    myChartbarline.setOption(option);

    window.addEventListener("resize", () => {
        myChartbarline.resize();
    });
}

function loadtable(divid, data) {
    var str = '';
    if (data.length > 0) {
        str = str + '<table class="jingtai_top">';
        str = str + '	<thead>';
        str = str + '		<tr>';
        for (let i in data[0]) {
            str = str + '			<th >' + i + '</th>';
        }
        str = str + '		</tr>';
        str = str + '	</thead>';
        str = str + '</table>';
        str = str + '<div class="jingtai_box">';
        str = str + '	<table class="jingtai_tab">';
        str = str + '		<tbody>';
        for (let i = 0; i < data.length; i++) {
            str = str + '			<tr>';
            for (let j in data[i]) {
                str = str + '			<td >' + data[i][j] + '</td>';

            }
            str = str + '			</tr>';
        }
        str = str + '		</tbody>';
        str = str + '	</table>';
        str = str + '</div>';

        $("#" + divid).html(str);


    } else {
        $('#' + divid).html("无数据");
    }
}

function loadright11(divid, data) {
    var dataList = data.amountList

    var option = {
        chart: {
            backgroundColor: 'rgba(14, 151, 244, 0)',
            type: 'scatter'
        },
        title: false,
        // tooltip: {
        //     pointFormat: '{series.name}'
        // },
        plotOptions: {
            series: {
                shadow: true,
                borderWidth: 1
            },
        },
        // colors:['#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
        colors: ['#24CBE5', '#2089cf', '#205bcf', '#211fdd'],
        series: [{
            type: 'venn',
            name: '',
            dataLabels: {
                enabled: true,
                inside: false,
                align: 'center',
                format: '{point.name}',
                style: {
                    color: '#effffc',
                    fontFamily: 'Microsoft Yahei',
                    fontSize: '18px'
                }
            },
            data: [{
                name: '移动 ' + dataList[0],
                sets: ['移动'],
                value: 2
            }, {
                name: '联通 ' + dataList[1],
                sets: ['联通'],
                value: 2
            }, {
                name: '电信 ' + dataList[2],
                sets: ['电信'],
                value: 2
            }, {
                name: dataList[3],
                sets: ['移动', '联通'],
                value: 1
            }, {
                name: dataList[4],
                sets: ['移动', '电信'],
                value: 1
            }, {
                name: dataList[5],
                sets: ['联通', '电信'],
                value: 1
            }, {
                name: dataList[6],
                sets: ['移动', '联通', '电信'],
                value: 1
            }]
        }],
        credits: {
            enabled: false // 禁用版权信息
        }
    };

    Highcharts.chart(divid, option);
}

function loadright12(divid, data) {
    var myMapChart = echarts.init(document.getElementById(divid));

    var option = {
        title: {
            text: '基站运营商',
            left: 'center',
            textStyle: {
                color: '#fff',
                fontFamily: 'Microsoft Yahei',
                fontSize: 16
            }

        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        series: [{
            name: '分公司',
            type: 'pie',
            // radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: true,
                    position: 'inner',
                    textStyle: {
                        fontWeight: 'normal',
                        lineHeight: 20,
                        fontSize: 14 //文字的字体大小
                    },
                    formatter: '{b}\n{c}'
                },
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            itemStyle: {
                normal: {
                    color: function (params) {
                        var colorList = ['#24CBE5', '#2089cf', '#205bcf', '#211fdd'];
                        return colorList[params.dataIndex]
                    },
                    fontSize: 14
                }
            },
            data: data.amountList
        }]
    };

    myMapChart.setOption(option);
    window.addEventListener("resize", () => {
        myMapChart.resize();
    });
}

function loadright2(divid, data) {
    var myChart = echarts.init(document.getElementById(divid));
    var color = [
        "#0090FF",
        "#36CE9E",
        "#FFC005",
        "#FF515A",
        "#8B5CFF",
        "#00CA69"
    ]
    var hexToRgba = (hex, opacity) => {
        let rgbaColor = "";
        let reg = /^#[\da-f]{6}$/i;
        if (reg.test(hex)) {
            rgbaColor = `rgba(${parseInt("0x" + hex.slice(1, 3))},${parseInt(
      "0x" + hex.slice(3, 5)
    )},${parseInt("0x" + hex.slice(5, 7))},${opacity})`;
        }
        return rgbaColor;
    }
    var option = {
        // color: ['#1cdade'],
        grid: {
            top: '10%',
            left: '5%',
            width: '90%',
            height: '65%',

        },
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                let html = '';
                params.forEach(v => {
                    console.log(v)
                    html += `<div style="color: #666;font-size: 14px;line-height: 24px">
                <span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:${color[v.componentIndex]};"></span>
                ${v.name} </br> ${v.value}
                `;
                })

                return html
            },
            extraCssText: 'background: #fff; border-radius: 0;box-shadow: 0 0 3px rgba(0, 0, 0, 0.2);color: #333;',
            axisPointer: {
                type: 'shadow',
                shadowStyle: {
                    color: '#ffffff',
                    shadowColor: 'rgba(225,225,225,1)',
                    shadowBlur: 5
                }
            }
        },
        xAxis: [{
            type: 'category',
            data: data.nameList,
            axisLabel: {
                interval: 0,
                rotate: 45,
                color: '#fff'
            },
            axisTick: {
                alignWithLabel: false
            }
        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                "show": false,
                interval: 0,
                rotate: 0,
                color: '#fff'
            },
            "axisLine": {
                "show": false
            },
            splitLine: {
                show: false
            }
        }],
        series: [{
            type: "line",
            smooth: true,
            // showSymbol: false,/
            symbolSize: 8,
            zlevel: 3,
            label: {
                normal: {
                    show: true,
                    position: 'top',
                    color: '#fff'
                }
            },
            lineStyle: {
                normal: {
                    color: color[0],
                    shadowBlur: 3,
                    shadowColor: hexToRgba(color[0], 0.5),
                    shadowOffsetY: 8
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(
                        0,
                        0,
                        0,
                        1,
                        [{
                                offset: 0,
                                color: hexToRgba(color[0], 0.3)
                            },
                            {
                                offset: 1,
                                color: hexToRgba(color[0], 0.1)
                            }
                        ],
                        false
                    ),
                    shadowColor: hexToRgba(color[0], 0.1),
                    shadowBlur: 10
                }
            },
            data: data.amountList
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}

function dataFormate(data) {
    var newdata = {};
    if (data != null && data.length > 0) {
        var col = [];
        for (var key in data[0]) {
            col[col.length] = key;
        }
        for (var i = 0; i < col.length; i++) {
            var vals = [];
            for (var j = 0; j < data.length; j++) {
                vals[vals.length] = data[j][col[i]];
            }
            newdata[col[i]] = vals;
        }
    }
    return newdata;
}

var geoCoordMap = {
    '海口': [110.3893, 19.8516],
    '三亚': [109.3716, 18.3698],
    '临高': [109.6957, 19.8063],
    '琼海': [110.4208, 19.224],
    '文昌': [110.8905, 19.7823],
    '儋州': [109.3291, 19.5653],
    '东方': [108.8498, 19.0414],
    '昌江': [109.0407, 19.2837],
    '白沙': [109.3703, 19.211],
    '万宁': [110.3137, 18.8388],
    '定安': [110.3384, 19.4698],
    '保亭': [109.6284, 18.6108],
    '乐东': [109.0283, 18.6301],
    '澄迈': [109.9937, 19.7314],
    '琼中': [109.8413, 19.0736],
    '屯昌': [110.0377, 19.362],
    '陵水': [109.9924, 18.5415],
    '五指山': [109.5282, 18.8299],
};

// 转换地图数据
var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if (geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value)
            });
        }
    }
    return res;
};


var gundong;


function notgundong() {
    clearInterval(gundong);
}

function isgundong() {
    gundong = setInterval(scrollTop, 3000);
}


function ditubigScreen() {
    document.getElementById('zhezhao').style.display = "";
    document.getElementById('oframe').style.display = "none";
    loadcenter2('bigditu', datacenter23);
}

function bigScreen() {
    document.getElementById('oframe').style.display = "";
    document.getElementById('zhezhao').style.display = "";
    // document.getElementById('bigtaifeng').className = "fullScreen";
    // $("#tankuang").html('<iframe id="bigtaifeng" width="100%" height="100%" src="http://typhoon.weather.com.cn/gis/typhoon_full.shtml" frameborder="0"></iframe>')
    // var qrdiv = $("<div></div>").qrcode("http://typhoon.weather.com.cn/gis/typhoon_full.shtml");
    // $("#qrcode-iframe").contents().find("body").append(qrdiv);
    document.getElementById("oframe").src = "http://typhoon.weather.com.cn/gis/typhoon_full.shtml";
}

function hidder() {
    document.getElementById('zhezhao').style.display = "none";
}