/**
 加载数据
 */
var url = "/fast/api/gwzx/";

function loss(){
    $.ajax({url:url+"loss",success:function(result){
        // console.log(result)
        var pageData = result.page[0];
        var datacenter1 ={
            '影响用户数':pageData.BAD_USERS,
            '经济损失(万元)':pageData.LOSS_MONEY,
            '出动抢修人员':pageData.REPAIR_PEOPLE,
            '出动抢修车辆':pageData.REPAIR_CAR,
            '预警短信(万户)':pageData.MESSAGE_NUMER_FOR_ALARM,
            '卫星保障电话':pageData.XSTAR_CELLPHONE,
            '应急油机发电':pageData.OIL_POWER_PRODUCT_NUMBER
        };
        loadcenter1(datacenter1);
    }});
}

function centre_down(){

    $.ajax({url:url+"centre_down",success:function(result){
        var page = result.page;
        var datacenter23 = [];
        for(var i = 0 ;i< page.length;  i ++){
            var o = {};
            for(var key in page[i]){
                o[key.toLowerCase()]=page[i][key];
            }
            datacenter23.push(o);
        }
        loadcenter2('center2', datacenter23);
    }});
}

function left_up(){
    $.ajax({url:url+"left_up",success:function(result){
        var pageData = result.page[0];
        var dataleft1 =
            {
                '产权':{
                    '停电':pageData.OWNER_CUT,
                    '退服':pageData.OWNER_CUT_OUTAGE_CURRENT,
                    '合计':pageData.OWNER,
                    // 'RATE':pageData.OWNER_CUT_OUTAGE_CURRENT_RATE,
                },
                '光缆':{
                    '中断':pageData.BREAK_OPT_TOTAL,
                    '修复':pageData.REPAIR_OPT_TOTAL,
                    // 'RATE':pageData.REPAIR_OPT_RATE,
                },
                '逻辑':{
                    '退服':pageData.OUTAGE_CURRENT,
                    '合计':pageData.SITE_NUMBER,
                    // 'RATE':pageData.OUTAGE_CURRENT_RATE,
                }
            };


        // loadleft1('pie1', dataleft1.产权,'产权退服率', dataleft1.产权.RATE);
        // loadleft1('pie2', dataleft1.光缆,'光缆修复率', dataleft1.光缆.RATE);
        // loadleft1('pie3', dataleft1.逻辑,'逻辑退服率', dataleft1.逻辑.RATE);
        loadleft1('pie1', dataleft1.产权,'产权退服率', (dataleft1.产权.退服/dataleft1.产权.合计).toFixed(4));
        loadleft1('pie2', dataleft1.光缆,'光缆修复率', (dataleft1.光缆.修复/dataleft1.光缆.中断).toFixed(4));
        loadleft1('pie3', dataleft1.逻辑,'逻辑退服率', (dataleft1.逻辑.退服/dataleft1.逻辑.合计).toFixed(4));
    }});
}

function left_centre(){
    $.ajax({url:url+"left_centre",success:function(result){
        // console.log(result)
        loadleft2('left2', result.page);
    }});
}

function left_down(){

    $.ajax({url:url+"left_down",success:function(result){
        // console.log(result)
        loadtable('left3', result.page);
    }});
}

function righ_down(){

    $.ajax({url:url+"righ_down",success:function(result){
        // console.log(result);
        loadright3('right3', result.page);
    }});
}