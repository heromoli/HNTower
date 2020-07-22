<template>
    <div :style="note" v-loading.fullscreen.lock="loading" class="header">

        <el-row :gutter="3" :style="'height:'+heights.t+'px'">
            <el-col :span="24" align="center">
                <img :src="logo" alt="" align="center" style="margin-top: 15px"/>
                <div class="currenttime">
                    <span>{{times}}</span>
                </div>
                <div class="tianqi">
                    <iframe width="365" scrolling="no" height="24" frameborder="0" allowtransparency="true"
                            src="//i.tianqi.com/index.php?c=code&id=1&color=%23FFFFFF&icon=1&py=haikou&wind=1&num=1&site=12"></iframe>
                </div>
            </el-col>
        </el-row>

        <el-row :gutter="7" style="padding: 5px">
            <el-col :span="7">
                <div class="main" :style="'height:'+heights.l.h1+'px'">
                    <span class="span1"></span>
                    <span class="span2"></span>
                    <span class="span3"></span>
                    <span class="span4"></span>
                    <cityStation_table1 :h="heights.l.h1"/>
                </div>

                <div class="main" :style="'height:'+heights.l.h2+'px'">
                    <span class="span1"></span>
                    <span class="span2"></span>
                    <span class="span3"></span>
                    <span class="span4"></span>
                    <cityStation_table2 :h="heights.l.h2"/>
                </div>

            </el-col>
            <el-col :span="10">
                <div class="main" :style="'height:'+heights.m.h1+'px'">
                    <span class="span1"></span>
                    <span class="span2"></span>
                    <span class="span3"></span>
                    <span class="span4"></span>
                    <hainan_map :h="heights.m.h1"/>
                    <div class="m_bottom_bar">
                        <station5gBuild_pie :h="heights.l.h1"/>
                    </div>

                </div>
            </el-col>
            <el-col :span="7">
                <div class="main" :style="'height:'+heights.r.h1+'px'">
                    <span class="span1"></span>
                    <span class="span2"></span>
                    <span class="span3"></span>
                    <span class="span4"></span>
                    <stationShare_venn/>
                    <div class="right_bottom_bar">
                        <stationShare_bar/>
                    </div>
                </div>
            </el-col>
        </el-row>
        <el-row :gutter="7" style="padding: 0px 5px 5px 5px;margin-top:-3.5px;margin-left:-1.4px;margin-right:3px">
            <el-col :span="7">
                <div class="main" :style="'height:'+heights.l.h1+'px'">
                    <span class="span1"></span>
                    <span class="span2"></span>
                    <span class="span3"></span>
                    <span class="span4"></span>
                    <yearlyStation_bar :h="heights.l.h1"/>
                </div>
            </el-col>
            <el-col :span="10">
                <div class="main" :style="'height:'+heights.l.h1+'px'">
                    <span class="span1"></span>
                    <span class="span2"></span>
                    <span class="span3"></span>
                    <span class="span4"></span>
                    <station5gBuild_bar :h="heights.l.h1"/>
                    <div class="m_bottom_table">
                        <cityStation_table3 :h="heights.l.h1"/>
                    </div>
                </div>
            </el-col>
            <el-col :span="7">
                <div class="main" :style="'height:'+heights.l.h1+'px'">
                    <span class="span1"></span>
                    <span class="span2"></span>
                    <span class="span3"></span>
                    <span class="span4"></span>
                    <template v-if="!loading">
                        <monthlyStation5g_line :h="heights.l.h1"/>
                    </template>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>

    import bg_png from '@/assets/img/beijing.png'
    import title from '@/assets/img/title.png'
    // import logo_png from '@/assets/img/logo.png'
    import hainan_map from '@/components/zhzyglnew/hainan_map'
    // import chanquan_pie from '@/components/zhzyglnew/chanquan_pie'
    // import cityStation_pie from '@/components/zhzyglnew/cityStation_pie'
    import cityStation_table1 from '@/components/zhzyglnew/cityStation_table1'
    import cityStation_table2 from '@/components/zhzyglnew/cityStation_table2'
    import cityStation_table3 from '@/components/zhzyglnew/cityStation_table3'
    // import cityStation_table from '@/components/zhzyglnew/cityStation_table'
    import stationShare_bar from '@/components/zhzyglnew/stationShare_bar'
    // import bizScene_bar from '@/components/zhzyglnew/bizScene_bar'
    import yearlyStation_bar from '@/components/zhzyglnew/yearlyStation_bar'
    // import stationSource_pie from '@/components/zhzyglnew/stationSource_pie'
    // import shareRate_radar from '@/components/zhzyglnew/shareRate_radar'
    import operatorStation_pie from '@/components/zhzyglnew/operatorStation_pie'
    import stationShare_venn from '@/components/zhzyglnew/stationShare_venn'
    import station5gBuild_bar from '@/components/zhzyglnew/station5gBuild_bar'
    import station5gBuild_pie from '@/components/zhzyglnew/station5gBuild_pie'
    import monthlyStation5g_line from '@/components/zhzyglnew/monthlyStation5g_line'

    export default {
        name: "overview",

        data() {
            return {
                logo: title,
                loading: false,
                times: '',
                note: {
                    backgroundImage: "url(" + bg_png + ") ",
                    backgroundPosition: "center center",
                    backgroundRepeat: "no-repeat",
                    backgroundSize: "cover",
                    // marginTop: "5px",
                    // backgroundColor:  "#33a4f4"
                },
                heights: {
                    height: 0,
                    t: 90,
                    l: {
                        h1: 230,
                        h2: 180,
                        h3: 230,
                    },
                    m: {
                        h1: 417
                    },
                    r: {
                        h1: 417
                    }
                }
            }
        },
        mounted() {
            this.initHeight();
            setInterval(this.getTimes, 1000)
        },
        components: {
            hainan_map,
            // chanquan_pie,
            // cityStation_pie,
            // cityStation_table,
            stationShare_bar,
            // bizScene_bar,
            yearlyStation_bar,
            // stationSource_pie,
            // shareRate_radar,
            operatorStation_pie,
            station5gBuild_bar,
            monthlyStation5g_line,
            stationShare_venn,
            cityStation_table1,
            cityStation_table2,
            cityStation_table3,
            // title,
            station5gBuild_pie
        },
        methods: {
            initHeight() {
                //var h = document.body.clientHeight;
                let _self = this;
                this.resize();
                window.addEventListener('resize', () => {
                    _self.resize();
                })

            },
            resize: function () {
                var height = window.innerHeight - this.heights.t;
                var h = (height - 31) / 11;
                var h3 = h * 3;
                var h4 = h * 4;
                this.heights.l.h1 = h4;
                this.heights.l.h2 = h3;
                this.heights.l.h3 = h4;
                this.heights.m.h1 = h4 + h3 + 6.5;
                this.heights.r.h1 = h4 + h3 + 6.5;
            },
            getTimes() {
                var time = new Date();
                var month = time.getMonth() + 1;
                var hours = this.displayClock(time.getHours()) + ":";
                var minutes = this.displayClock(time.getMinutes()) + ":";
                var seconds = this.displayClock(time.getSeconds());
                //显示时间
                this.times = time.getFullYear() + "-" + month + "-" + time.getDate() + " " + hours + minutes + seconds;//在id为show的块区域显示

            },
            displayClock(num) {//num是传入的startClock中的动态值
                if (num < 10) {
                    return "0" + num;
                } else {
                    return num;
                }
            }


        }
    }
</script>

<style lang="scss" scoped>
    .logo_style3 {
        /*font-size: 18px;*/
        color: white;
    }

    .header .biaoti img {
        width: 99%;
        height: 80px;
        margin: 0 auto;
    }

    .header {
        width: 100%;
    }

    .header .el-row {
        width: 99.9%;
    }

    .contain {
        //  margin-top: -20px;
    }

    .currenttime {
        position: absolute;
        top: 20px;
        left: 30px;
        color: white;
        font-size: 24px;
        font-weight: bold;
    }

    .tianqi {
        position: absolute;
        top: 20px;
        right: 50px;
    }

    .col-3 {
        width: 28.34%;
        float: left;
        margin: 0 0.5%;
    }

    .col-6 {
        width: 40%;
        float: left;
        margin: 0 0.3%;
    }

    .rowc {
        padding: 7px 0px;
    }

    .piestl {
        width: 33.3%;
        float: left;
    }

    .cards {
        background-color: #42758a80;
        margin: 5px;
        position: relative;
    }

    .card-3 {
        float: left;
    }

    .card2 p.title {
        color: #e6e2e2d9;
        font-size: 22px;
    }

    .card2 p.value {
        font-size: 28px;
        color: white;
        text-align: right;
        position: absolute;
        bottom: 20px;
        width: 100%;
        right: 5px;
    }

    .card2 .title {
        padding: 30px 0px 0px 10px;
    }

    .card1 p.title {
        font-size: 14px;
        color: #e6e2e2d9;
        padding: 10px;
    }

    .card1 p.value {
        font-size: 24px;
        color: white;
        text-align: right;
        position: absolute;
        bottom: 0px;
        width: 100%;
        right: 5px;
        padding: 5px 0;
    }

    .cards p {
        margin: 10px 0px;
    }

    .card img {
        position: absolute;
    }

    .card1 img {
        margin: 5px 15px;
        width: 40px;
        height: 40px;
        bottom: 5px;
    }

    .card2 img {
        bottom: 20px;
    }

    .divtitle {
        margin: 0px;
        padding: 10px;
        background-color: #f0f0f00f;
        color: #e6e2e2d9;
        text-align: center;
        font-family: Microsoft YaHei;
        font-weight: bold;
    }

    td img {
        height: 30px;
    }

    td {
        margin-left: 0px;
        margin-top: 0px;
        margin-right: 0px;
        margin-bottom: 0px;
    }

    #left2 table {
        width: 100%;
        height: 70%;
        border-collapse: collapse;
        color: #88d9e5;
    }

    #left2 th {
        width: 100px;
        line-height: 30px;
        border-bottom: 1px solid #b5b5b5;
        text-align: center;
        font-size: 14px;
    }

    #left2 td {
        width: 100px;
        line-height: 30px;
        border-bottom: 1px dashed #b5b5b5;
        text-align: center;
        font-size: 14px;
    }

    #left3 table {
        width: 100%;
        height: 70%;
        border-collapse: collapse;
        color: #88d9e5;
    }

    #left3 th {
        width: 100px;
        line-height: 30px;
        border-bottom: 1px solid #b5b5b5;
        text-align: center;
        font-size: 14px;
    }

    #left3 td {
        width: 100px;
        line-height: 30px;
        border-bottom: 1px dashed #b5b5b5;
        text-align: center;
        font-size: 14px;
    }

    #right2 table {
        width: 100%;
        border-collapse: collapse;
        color: #88d9e5;
    }

    #right2 th {
        width: 100px;
        line-height: 30px;
        border-bottom: 1px solid #b5b5b5;
        text-align: center;
        font-size: 12px;
    }

    #right2 td {
        width: 100px;
        line-height: 30px;
        border-bottom: 1px dashed #b5b5b5;
        text-align: center;
        font-size: 10px;
    }

    .scroll-box {
        height: 88%;
        width: 100%;
        overflow: hidden;
        position: relative;
    }

    .tab-scroll {
        position: absolute;
        left: 0;
        top: 0;
        border-top: none;
    }

    .tab-scroll td {
        border-top: none;
    }

    /*进度条>>>>>>>>>>>>>>>>>>>>>>>*/
    #scheduleX {
        width: 90%;
        height: 25px;
        border-radius: 20px;
        background-color: #7d8e91;
    }

    #scheduleX .xList {
        background-color: #0ebad5;
        border-radius: 20px;
        width: 0%;
        height: 100%;
        position: relative;
    }

    #scheduleX .xList .xNum {
        font-size: 10px;
        position: absolute;
        top: 50%;
        right: 5%;
        color: #fff;
        -webkit-transform: translate(0%, -50%);
        -moz-transform: translate(0%, -50%);
        -ms-transform: translate(0%, -50%);
        -o-transform: translate(0%, -50%);
        transform: translate(0%, -50%);
        z-index: 10;
    }

    /*进度条<<<<<<<<<<<<<<<<<<<<<<<*/

    /*.main .center{*/
    /*width: 100%;*/
    /*}*/

    /*body{*/
    /*display: flex;*/
    /*justify-content: center;*/
    /*flex-direction: column;*/
    /*align-items: center;*/
    /*}*/
    .main {
        position: relative;
        width: 100%;
        /*border: 1px solid #0097d694;*/
        background-color: #42758a80;
        padding: 5px;
        margin-bottom: 6.5px;
    }

    .main .span1 {
        position: absolute;
        left: -5px;
        top: -5px;
        padding: 5px;
        border-style: solid;
        border-color: #f0f0f075;
        border-width: 2px 0 0 2px;
    }

    .main span:nth-child(2) {
        position: absolute;
        right: -5px;
        top: -5px;
        padding: 5px;
        border-style: solid;
        border-color: #f0f0f075;
        border-width: 2px 2px 0 0;
    }

    .main span:nth-child(3) {
        position: absolute;
        right: -5px;
        bottom: -5px;
        padding: 5px;
        border-style: solid;
        border-color: #f0f0f075;
        border-width: 0 2px 2px 0;
    }

    .main .span4 {
        position: absolute;
        left: -5px;
        bottom: -5px;
        padding: 5px;
        border-style: solid;
        border-color: #f0f0f075;
        border-width: 0 0 2px 2px;
    }

    .main .span2 {
        display: none;
    }

    .main .span4 {
        display: none;
    }

    .right_bottom_bar {
        width: 40%;
        position: absolute; /*固定位置*/
        right: 30%;
        bottom: 5px;

    }

    .m_bottom_bar {
        height: 40%;
        width: 40%;
        position: absolute; /*固定位置*/
        right: 0;
        bottom: 0;

    }

    .m_bottom_table {
        height: 40%;
        width: 40%;
        position: absolute; /*固定位置*/
        right: 5px;
        top: 1px;

    }
</style>
