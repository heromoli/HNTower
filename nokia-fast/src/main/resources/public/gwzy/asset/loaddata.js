/**
 加载数据
 */
var url = "/fast/api/zhzygl/";

function left_up(){
    // loadleft1('left1', getStationAmount6);
    // return

    // /api/zhzygl/getStationAmount6
    $.ajax({url:url+"getStationAmount6",success:function(result){
        // console.log(result)
        loadleft1('left1', result);
    }});
}

function left_center() {
    // loadleft2('left2', getOperatorStationAmount);
    // return
    // /api/zhzygl/getOperatorStationAmount
    $.ajax({url:url+"getOperatorStationAmount",success:function(result){
        // console.log(result)
        loadleft2('left2', result);
    }});
}

function left_down(){
    // loadleft3('left3', getYearlyStationAmount, getYearlyStationIncrease);
    // return

    // /api/zhzygl/getYearlyStationAmount
    // /api/zhzygl/getYearlyStationIncrease
    $.ajax({url:url+"getYearlyStationAmount",success:function(result1){
        // console.log(result)
        $.ajax({url:url+"getYearlyStationIncrease",success:function(result2){
            // console.log(result)
            loadleft3('left3', result1, result2);
        }});
    }});
}

function center_up() {
    // loadcenter11('center11', getStationAmount);
    
    // /api/zhzygl/getStationAmount
    $.ajax({url: url + "getStationAmount",success: function (result) {
        loadcenter11('center11', result);
    }});

    loadcenter12('center12', datacenter12); // 静态数据
    return

    // 静态数据
    $.ajax({url: url + "centre_up2",success: function (result) {
        loadcenter12('center12', result);
    }});
}

function center_down() {
    // loadcenter21('center21', getStation5GAmount);
    
    // /api/zhzygl/getStation5GAmount
    $.ajax({
        url: url + "getStation5GAmount",
        success: function (result) {
            loadcenter21('center21', result);
        }
    });

    loadtable('center22', dataCenter22); // 静态数据
    return

    // 静态数据
    $.ajax({url: url + "centre_down2",success: function (result) {
        loadcenter22('center22', result);
    }});
}

function righ_up(){
    // loadright11('right11', getStationShareAmount);
    // loadright12('right12', getStationAloneAmount);
    // return

    // /api/zhzygl/getStationShareAmount
    $.ajax({url:url+"getStationShareAmount",success:function(result){
        // console.log(result);
        loadright11('right11', result);
    }});

    //  /api/zhzygl/getStationAloneAmount
    $.ajax({url:url+"getStationAloneAmount",success:function(result){
        // console.log(result);
        loadright12('right12', result);
    }});
}

function righ_down(){
    // loadright2('right2', getMonthlyStation5GAmount);
    // return

    //  /api/zhzygl/getMonthlyStation5GAmount'
    $.ajax({url:url+"getMonthlyStation5GAmount",success:function(result){
        // console.log(result);
        loadright2('right2', result);
    }});
}