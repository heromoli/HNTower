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
    loss();
    left_up();

    // loadleft1('pie1', dataleft1.产权,'产权退服率', (dataleft1.产权.退服/dataleft1.产权.合计).toFixed(4));
    // loadleft1('pie2', dataleft1.光缆,'光缆修复率', (dataleft1.光缆.修复/dataleft1.光缆.中断).toFixed(4));
    // loadleft1('pie3', dataleft1.逻辑,'逻辑退服率', (dataleft1.逻辑.退服/dataleft1.逻辑.合计).toFixed(4));

    left_centre();
    left_down();
    centre_down();
    // loadleft2('left2', dataleft2);
    // loadtable('left3', dataleft3);
    // loadcenter1(datacenter1);
    // loadcenter2('center2', datacenter23);
    // loadcenter3('center3', dataFormate(datacenter23), datacenter23);
    // loadtable('right1',dataright1);
    loadDynamicTable('right2', dataright2);
    // loadright3('right3', dataright3);
    righ_down();
    document.getElementById('zhezhao').style.display = "none";

}

// 刷新界面元素高度
function pagelogin() {
    var h = document.body.clientHeight;
    var h1 = $(".header").height();
    $(".contain").height((h - 16 - h1 + 10) + 'px');

    var ch = $(".contain").height() - 6 * 7;
    $(".row-1").height(ch + 'px');
    $(".row-2").height(ch * 2 / 3 + 12 + 'px');
    $(".row-3").height(ch / 3 + 'px');
    $(".row-4").height(ch / 4 + 27 + 'px');
    $(".main").height('100%');
    $(".piestl").height($(".row-3 .main").height() + 'px');
    // $(".datadiv").height($(".row-3 .main").height() + 'px');
    $(".card").width(($(".col-6").width() / 4) + 'px');

    $(".card2").height('96%');
    $(".card1").height('46%');
    // $(".card-3").height();
    $("#center1").height($(".row-3 .main").height() - 30 + 'px');
    $("#center2").height($(".row-2 .main").height() + 'px');
    // $("#center3").height($(".row-4 .main").height() + 'px');
    $("#right2").height($(".row-3 .main").height() + 'px');
    $("#right3").height($(".row-3 .main").height() - $(".row-3 .divtitle").height() - 10 + 'px');
}

window.onresize = function () {
    pagelogin()
};

// 时钟》》》》》》》》》》》》》》》》》》》》
var timer = null;

function displayClock(num) {//num是传入的startClock中的动态值
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
    stime.innerHTML = time.getFullYear() + "-" + month + "-" + time.getDate() + " " + hours + minutes + seconds;//在id为show的块区域显示
    timer = setTimeout("startClock()", 1000);//延时器
}

// 时钟《《《《《《《《《《《《《《《《《《《《《《

function loadleft1(divid, dd, title, value) {
    var myChartpie = echarts.init(document.getElementById(divid));
    var data = [value, value, value, value, value,];
    var arr = [];
    for (let i in dd) {
        if (i != 'RATE')
            arr.push(i + ':' + dd[i]);
    }

    var option1 = {
        title: {
            text: title,
            left: 'center',
            top: 5,
            textStyle: {
                color: '#fff',
                fontSize: '16px Microsoft YaHei'
            }
        },
        backgroundColor: '#f0f0f000',
        graphic: [{
            type: 'group',
            left: 'center',
            bottom: 20,
            children: [{
                type: 'text',
                z: 100,
                left: '50',
                top: 'middle',
                style: {
                    fill: '#88d9e5',
                    text: arr.join('\n'),
                    font: '16px Microsoft YaHei'
                }
            }, {
                type: 'text',
                z: 100,
                left: '120',
                top: 'middle',
                style: {
                    fill: '#000',
                    text: '',
                    font: '24px Microsoft YaHei'
                }
            }]
        }],
        series: [{
            type: 'liquidFill',
            radius: '70%',
            center: ['50%', '45%'],
            data: data,
            backgroundStyle: {
                borderWidth: 5,
                borderColor: '#2a92b4',
                color: '#bfeff9'
            },
            label: {
                normal: {
                    formatter: (value * 100).toFixed(2) + '%',
                }
            }
        }]
    };

    myChartpie.setOption(option1);

    window.addEventListener("resize", () => {
        myChartpie.resize();
    });
}

function loadleft2(divid, data) {
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
                if (j == '运营商') {
                    if (data[i][j] == '联通') {
                        str = str + '			<td ><img src="asset/icon/liantong.png"></img></td>';
                    } else if (data[i][j] == '电信') {
                        str = str + '			<td ><img src="asset/icon/dianxin.png"></img></td>';
                    } else if (data[i][j] == '移动') {
                        str = str + '			<td ><img src="asset/icon/yidong.png"></img></td>';
                    }
                } else if (j == '修复率') {
                    str = str + '			<td ><div id="scheduleX"><div class="xList" style="width:' + data[i][j] + ';"><span class="xNum">' + data[i][j] + '%</span></span></div></div></td>';
                } else if (j == '退服率') {
                    str = str + '			<td >' + data[i][j] + '%</td>';
                } else {
                    str = str + '			<td >' + data[i][j] + '</td>';
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
                if (j.indexOf('率') == j.length - 1) {
                    str = str + '			<td >' + data[i][j] + '%</td>';
                } else {
                    str = str + '			<td >' + data[i][j] + '</td>';
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

function loadright3(divid, data) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(divid));
    var legendarr = [];
    var c = ['#fa4a4d','#ff9a07', '#f3ee0e', '#92d050', '#33b565', '#06ffa7'];
    var seriesdata = [];
    for (let i = 0; i < data.length; i++) {
        legendarr[data.length - i] = data[i].告警名称;
        seriesdata[i] = {
            value: data[i].告警基站数量,
            name: data[i].告警名称,
            itemStyle: {
                normal: {
                    color: c[i]
                }
            },
            label: {
                show: true,
                fontSize: 16,
                lineHeight: 18
            },
            labelLine: {
                ength: 30,
                show: true
            }
        }
    }

    for (let l = 0; l < 4; l++) {
        seriesdata[data.length + l] = {
            value: 0,
            name: "",
            label: {
                show: false
            },
            labelLine: {
                show: false
            }
        }
    }
    var option2 = {
        backgroundColor: 'rgba(0,0,0,0)',
        tooltip: {
            trigger: 'item',
            formatter: "{b}  <br/>{c}"
        },
        legend: {
            x: 'center',
            y: '2%',
            data: legendarr,
            icon: 'circle',
            textStyle: {
                color: '#fff',
            }
        },
        calculable: true,
        series: [{
            name: '应急信息',
            type: 'pie',
            //起始角度，支持范围[0, 360]
            startAngle: 0,
            //饼图的半径，数组的第一项是内半径，第二项是外半径
            radius: [51, 80],
            //支持设置成百分比，设置成百分比时第一项是相对于容器宽度，第二项是相对于容器高度
            center: ['50%', '30%'],
            //是否展示成南丁格尔图，通过半径区分数据大小。可选择两种模式：
            // 'radius' 面积展现数据的百分比，半径展现数据的大小。
            //  'area' 所有扇区面积相同，仅通过半径展现数据大小
            roseType: 'area',
            //是否启用防止标签重叠策略，默认开启，圆环图这个例子中需要强制所有标签放在中心位置，可以将该值设为 false。
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: true,
                    formatter: '{c}'
                },
                emphasis: {
                    show: true
                }
            },
            labelLine: {
                normal: {
                    show: true,
                    length2: 1,
                },
                emphasis: {
                    show: true
                }
            },
            data: seriesdata
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option2);
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

function loadcenter1(data) {
    for (var i = 1; i < 8; i++) {
        var t = $('#d' + i + ' p.title').html();
        $('#d' + i + ' p.value').html(data[t]);
    }
}

// 加载柱状图
function loadcenter3(divid, dataList, dd) {
    var myChartbarline = echarts.init(document.getElementById(divid));
    var option3 = {
        title: {text: ''},
        grid: {
            top: '15%',
            left: '5%',
            width: '90%',
            height: '70%',

        },
        tooltip: {
            trigger: 'axis',
            formatter(params) {
                var str = '';
                for (let i = 0; i < dd.length; i++) {
                    if (dd[i].name == params[0].name) {
                        str = dd[i].name + '<br />'
                            + ' 退服率：' + dd[i].value + '%<br />'
                            + ' 退服数：' + dd[i].退服数 + '<br />'
                            + ' 总站数：' + dd[i].总站数;

                    }
                }

                return str;
            },
        },
        legend: {
            data: ['退服数', '退服率'],
            textStyle: {
                color: '#ffffff'
            },
            top: '2%',
        },
        xAxis: {
            data: dataList["name"],
            axisLine: {
                lineStyle: {
                    color: '#b6f2f7'
                }
            },
            axisTick: {
                show: false,
            },
        },
        yAxis: [{
            type: 'value',
            // min: 0,
            // max: 100,
            // interval: 5,
            axisLabel: {
                formatter: '{value} %'
            },
            axisLine: {
                lineStyle: {
                    color: '#b6f2f7',
                }
            },
            splitLine: {
                lineStyle: {
                    type: 'dotted', //虚线
                    color: '#b6f2f7',
                }
            }
        },
            {
                type: 'value',
                // min: 0,
                // max: 125,
                // interval: 25,
                axisLine: {
                    lineStyle: {
                        color: '#b6f2f7',
                    }
                },
                splitLine: {
                    show: false
                }
            }],
        series: [{
            name: '退服数',
            type: 'line',
            yAxisIndex: 1,
            data: dataList["退服数"],
            showAllSymbol: true,
            symbol: 'emptyCircle',
            symbolSize: 8,
            barWidth: 10,
            lineStyle: {
                normal: {
                    width: 4,
                    color: {
                        type: 'linear',

                        colorStops: [{
                            offset: 0,
                            color: '#3cf7f7' // 0% 处的颜色
                        }, {
                            offset: 1,
                            color: '#48D8BF' // 100% 处的颜色
                        }],
                        globalCoord: false // 缺省为 false
                    },
                    shadowColor: 'rgba(72,216,191, 0.3)',
                    shadowBlur: 5,
                    shadowOffsetY: 10
                }
            },
            itemStyle: {
                normal: {
                    color: '#0ebad5'
                },
            },

        }, {
            name: '退服率',
            type: 'bar',
            barWidth: 10,
            yAxisIndex: 0,
            data: dataList["value"],
            itemStyle: {
                normal: {
                    barBorderRadius: 5,
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            {offset: 0, color: '#90b7d4'},
                            {offset: 1, color: '#3EACE5'}
                        ]
                    )
                }
            },
        }]
    };

    myChartbarline.setOption(option3);

    window.addEventListener("resize", () => {
        myChartbarline.resize();
    });
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

// 加载地图
function loadcenter2(divid, dataList) {
    echarts.registerMap('hainan', hainanJson);
    var myMapChart = echarts.init(document.getElementById(divid));
    var option4 = {
        title: {
            text: '退服率',
            textStyle: {
                color: '#006be4',
                fontSize: 18
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: function (params) {
                var num = 0;
                var all = 0;
                dataList.forEach(e => {
                    if (e.name == params.name) {
                        num = e.退服数;
                        all = e.总站数
                    }
                });
                return params.name + "<br>" +
                    "退服率: " + params.value.toString().split(",")[2] + "%" + "<br>" +
                    "退服数: " + num + "<br>" +
                    "总站数: " + all;
            },
        },
        // dataRange: {
        //     x: 'right',
        //     y: 'bottom',
        //     splitList: [
        //         {start: 10, end: 100, label: '10 到 100（严重）', color: 'red'},
        //         {start: 5, end: 10, color:'#e8e051'},
        //         {start: 1, end: 5, color:'#9ed35f'},
        //         {end: 1, color: '#6ad367'}
        //     ],
        //     textStyle: {
        //         fontSize: '14',
        //         color: '#e6e2e2d9'
        //     },
        // },
        geo: {
            map: 'hainan',
            roam: false,     //设置缩放
            zoom: 1.2,
            aspectScale: 0.95,    //设置地图的长宽比
            label: {
                emphasis: {
                    show: false
                }
            },
            itemStyle: {
                normal: {
                    borderWidth: 1,//设置外层边框
                    areaColor: '#3eabff',
                    borderColor: '#fff',
                    shadowBlur: 5
                },
                emphasis: {
                    areaColor: '#006be4'
                }
            }
        },
        series: [
            {
                name: '退服率',
                type: 'scatter',
                coordinateSystem: 'geo',
                data: convertData(dataList),
                symbolSize: function (val) {
                    return val[2] * 10 / 2;
                },
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#ffffff'
                    }
                }
            },
            {
                name: 'Top 5',
                type: 'effectScatter',
                coordinateSystem: 'geo',
                data: convertData(dataList.sort(function (a, b) {
                    return b.value - a.value;
                }).slice(0, 6)),
                // data: convertData(dataList),
                symbolSize: function (val) {
                    return val[2] * 10 / 5;
                },
                encode: {
                    value: 2
                },
                showEffectOn: 'render',
                rippleEffect: {
                    brushType: 'stroke'
                },
                hoverAnimation: true,
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#0041d2',
                        shadowBlur: 10,
                        shadowColor: 'rgba(0,0,0,.3)'
                    }
                },
                zlevel: 1
            }
        ]
    };

    myMapChart.setOption(option4);
    window.addEventListener("resize", () => {
        myMapChart.resize();
    });
}

var gundong;

// 加载table
function loadDynamicTable(divid, data) {
    var str = '';
    if (data.length > 0) {
        str = str + '<table class="top">';
        str = str + '	<thead>';
        str = str + '		<tr>';
        str = str + '			<th style="width: 15%">站点名称</th>';
        str = str + '			<th style="width: 15%">加油站电话</th>';
        str = str + '			<th>加油站地址</th>';
        str = str + '		</tr>';
        str = str + '	</thead>';
        str = str + '</table>';
        str = str + '<div class="scroll-box">';
        str = str + '	<table class="tab-scroll">';
        str = str + '		<tbody>';
        for (let i = 0; i < data.length; i++) {
            str = str + '			<tr>';
            str = str + '				<td style="width: 15%">' + data[i].站点名称 + '</td>';
            str = str + '				<td style="width: 15%">' + data[i].加油站电话 + '</td>';
            str = str + '				<td>' + data[i].加油站地址 + '</td>';
            str = str + '			</tr>';
        }
        str = str + '		</tbody>';
        str = str + '	</table>';
        str = str + '</div>';

        $("#" + divid).html(str);

        gundong = setInterval(scrollTop, 1000);

    } else {
        $('#' + divid).html("无数据");
    }
}

function notgundong() {
    clearInterval(gundong);
}

function isgundong() {
    gundong = setInterval(scrollTop, 3000);
}

function scrollTop() {
    var sTab = document.getElementsByClassName('tab-scroll')[0];//要滚动的表
    var tbody = sTab.getElementsByTagName('tbody')[0];//要滚动表格的内容
    sTab.appendChild(tbody.cloneNode(true));//追加一次滚动内容,以防滚动后可视区域无内容
    var speed = sTab.getElementsByTagName('td')[0].offsetHeight + 1;//每次滚动的距离
    var tbdh = tbody.offsetHeight + 1;//整个表的高度,是因为上边的边不算滚动

    var t = sTab.offsetTop;//获取要滚动表的位置
    if (-tbdh == t) {//比较  滚动的距离等于整个表的高度   即第一个表完全看不见
        sTab.style.transition = '0s';//过渡动画设为0秒
        sTab.style.top = 0;//位置设为初始位置
        scrollTop();//因为偷梁换柱消耗了一次过程,所以把这一次过程补回来
    } else {
        sTab.style.transition = '1s';
        sTab.style.top = t - speed + 'px';
    }
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