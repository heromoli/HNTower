document.write("<script type='text/javascript' src='asset/data.js'></script>");

$(function () {
    pagelogin();
    startClock();

    loadleft1('pie1', dataleft1.产权,'产权退服率', (dataleft1.产权.退服/dataleft1.产权.合计).toFixed(4));
    loadleft1('pie2', dataleft1.光缆,'光缆修复率', (dataleft1.光缆.修复/dataleft1.光缆.中断).toFixed(4));
    loadleft1('pie3', dataleft1.逻辑,'逻辑退服率', (dataleft1.逻辑.退服/dataleft1.逻辑.合计).toFixed(4));
    loadleft2('left2', dataleft2);
    loadleft3('left3', dataleft3);

    loadcenter1(datacenter1);
    loadcenter2('center2', datacenter23);
    loadcenter3('center3', dataFormate(datacenter23), datacenter23);

    loadDynamicTable('right2',dataright2);
})

// 刷新界面元素高度
function pagelogin() {
    var h = document.body.clientHeight;
    var h1 = $(".header").height();
    $(".contain").height((h - 16 - h1 +20) + 'px');

    var ch = $(".contain").height() - 6 * 7;
    $(".row-1").height(ch + 'px');
    $(".row-2").height(ch / 2 + 'px');
    $(".row-3").height(ch / 3 + 'px');
    $(".row-4").height(ch / 4 + 'px');
    $(".main").height('100%');
    $(".piestl").height($(".row-3 .main").height() + 'px');
    $(".datadiv").height($(".row-3 .main").height() + 'px');
    $(".card").width(($(".col-6").width() / 4+1) + 'px');

    $(".card2").height('96%');
    $(".card1").height('46%');
    $(".card-3").height();
    $("#center2").height($(".row-2 .main").height() + 'px');
    $("#center3").height($(".row-4 .main").height() + 'px');
    $("#right2").height($(".row-3 .main").height() + 'px');
}

window.onresize = function () {
    pagelogin()
}

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
    var hours = displayClock(time.getHours()) + ":";
    var minutes = displayClock(time.getMinutes()) + ":";
    var seconds = displayClock(time.getSeconds());
    //显示时间
    stime.innerHTML = time.getFullYear() + "-" + time.getMonth() + "-" + time.getDate() + " " + hours + minutes + seconds;//在id为show的块区域显示
    timer = setTimeout("startClock()", 1000);//延时器
}
// 时钟《《《《《《《《《《《《《《《《《《《《《《

function loadleft1(divid, dd, title, value) {
    var myChartpie = echarts.init(document.getElementById(divid));
    var data = [value, value, value, value, value, ];
    var arr = [];
    for (let i in dd) {
        arr.push(i + ':' + dd[i]);
    }

    var option = {
        title: {
            text: title,
            left: 'center',
            top: 5,
            textStyle: {
                color: '#e6e2e2d9',
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

    myChartpie.setOption(option);

    window.addEventListener("resize",()=>{
        myChartpie.resize();
    });
}

function loadleft2(divid, data){
    var str = '';
    if(data.length >0 ){
        str = str + '<table class="jingtai_top">';
        str = str + '	<thead>';
        str = str + '		<tr>';
        for(let i in data[0]){
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
            for(let j in data[i]){
                if(j == '运营商'){
                    if(data[i][j] == '联通'){
                        str = str + '			<td ><img src="asset/icon/liantong.png"></img></td>';
                    }else if(data[i][j] == '电信'){
                        str = str + '			<td ><img src="asset/icon/dianxin.png"></img></td>';
                    }else if(data[i][j] == '移动'){
                        str = str + '			<td ><img src="asset/icon/yidong.png"></img></td>';
                    }
                }else if(j == '修复率'){
                    str = str + '			<td ><div id="scheduleX"><div class="xList" style="width:' + data[i][j] + ';"><span class="xNum">' + data[i][j] + '</span></span></div></div></td>';
                }else{
                    str = str + '			<td >' + data[i][j] + '</td>';
                }

            }
            str = str + '			</tr>';
        }
        str = str + '		</tbody>';
        str = str + '	</table>';
        str = str + '</div>';

        $("#" + divid).html(str);


    }else{
        $('#' + divid).html("无数据");
    }
}

function loadleft3(divid, data){
    var str = '';
    if(data.length >0 ){
        str = str + '<table class="jingtai_top">';
        str = str + '	<thead>';
        str = str + '		<tr>';
        for(let i in data[0]){
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
            for(let j in data[i]){
                str = str + '			<td >' + data[i][j] + '</td>';
            }
            str = str + '			</tr>';
        }
        str = str + '		</tbody>';
        str = str + '	</table>';
        str = str + '</div>';

        $("#" + divid).html(str);


    }else{
        $('#' + divid).html("无数据");
    }
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

function loadcenter1(data){
    for(var i =1;i <8 ;i++){
        var t = $('#d' + i + ' p.title').html();
        $('#d' + i + ' p.value').html(data[t]);
    }
}

// 加载柱状图
function loadcenter3(divid, dataList, dd) {
    var myChartbarline = echarts.init(document.getElementById(divid));
    var option = {
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

    myChartbarline.setOption(option);

    window.addEventListener("resize",()=>{
        myChartbarline.resize();
    });
}

// 加载地图
function loadcenter2(divid, dataList) {

    echarts.registerMap('海南', geoJson);
    var myMapChart = echarts.init(document.getElementById(divid));
    option = {
        title: {
            text: '退服率',
            textStyle: {
                color: '#e6e2e2d9',
                fontSize: 18
            }
        },
        tooltip: {
            formatter: function (params, ticket, callback) {
                return params.name + '<br />'
                    + params.seriesName + '：' + params.value + '%<br />'
                    + ' 退服数：' + params.data['退服数'] + '<br />'
                    + ' 总站数：' + params.data['总站数']
            },
            // extraCssText:'width:100px;height:85px;'
        },
        // visualMap: {
        //     min: 0,
        //     max: 10,
        //     left: 'left',
        //     top: 'bottom',
        //     text: ['高', '低'],
        //     textStyle: {
        //         color: '#fff',
        //         fontSize: 12
        //     },
        //     inRange: {
        //         color: ['#2c7ba8', '#8cceff']
        //     },
        //     show: true
        // },
        dataRange: {
            x: 'left',
            y: 'bottom',
            splitList: [
                {start: 10, end: 100, label: '10 到 100（严重）', color: 'red'},
                {start: 5, end: 10, color:'#e8e051'},
                {start: 2, end: 5, color:'#9ed35f'},
                {end: 2, color: '#6ad367'}
            ],
            textStyle: {
                fontSize: '14',
                color: '#e6e2e2d9'
            },
        },
        geo: {
            map: '海南',
            roam: false,
            zoom: 1.23,
            label: {
                normal: {
                    show: true,
                    fontSize: '14',
                    color: '#e6e2e2d9'
                }
            },
            itemStyle: {
                normal: {
                    // areaColor: '#98FB98',
                    borderWidth: 1,//设置外层边框
                    borderColor: '#5cc0f7',
                    // shadowColor: '#5cd5e4',
                    shadowBlur: 100
                },
                emphasis: {
                    areaColor: '#75ddc8',
                    shadowOffsetX: 0,
                    shadowOffsetY: 0,
                    shadowBlur: 20,
                    borderWidth: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            },
            // left: '6%',
            // top: 40,
            // bottom: '54%',
            // right: '14%',
            // roam: true,
            // scaleLimit:{                       //所属组件的z分层，z值小的图形会被z值大的图形覆盖
            //     min: 1,                          //最小的缩放值
            //     max:10,                            //最大的缩放值
            // }
        },
        series: [
            {
                name: '退服率',
                type: 'map',
                geoIndex: 0,
                data: dataList,
            }
        ]
    };
    myMapChart.setOption(option);

    window.addEventListener("resize",()=>{
        myMapChart.resize();
    });
}

var gundong;

// 加载table
function loadDynamicTable(divid,data){
    var str = '';
    if(data.length >0 ){
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

    }else{
       $('#' + divid).html("无数据");
    }
}

function notgundong(){
    clearInterval(gundong);
}

function isgundong(){
    gundong = setInterval(scrollTop, 1000);
}

function scrollTop() {
    var sTab = document.getElementsByClassName('tab-scroll')[0];//要滚动的表
    var tbody = sTab.getElementsByTagName('tbody')[0];//要滚动表格的内容
    sTab.appendChild(tbody.cloneNode(true));//追加一次滚动内容,以防滚动后可视区域无内容
    var speed = sTab.getElementsByTagName('td')[0].offsetHeight+1;//每次滚动的距离
    var tbdh = tbody.offsetHeight+1;//整个表的高度,是因为上边的边不算滚动

    var t = sTab.offsetTop;//获取要滚动表的位置
    if (-tbdh == t) {//比较  滚动的距离等于整个表的高度   即第一个表完全看不见
        sTab.style.transition = '0s';//过渡动画设为0秒
        sTab.style.top = 0;//位置设为初始位置
        scrollTop();//因为偷梁换柱消耗了一次过程,所以把这一次过程补回来
    }else{
        sTab.style.transition = '1s';
        sTab.style.top = t - speed + 'px';
    }
}