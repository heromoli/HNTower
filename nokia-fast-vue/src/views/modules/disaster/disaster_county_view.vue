<template>
    <div :style="note" v-loading.fullscreen.lock="loading">
        <el-tabs type="border-card" v-model="activeName">
            <el-tab-pane label="合计" name="hj">
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <!--<span class="span1"></span>-->
                            <!--<span class="span2"></span>-->
                            <!--<span class="span3"></span>-->
                            <!--<span class="span4"></span>-->
                            <hjStationAmount_bar v-if="'hj' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <hjStationBreakOpt_barLine v-if="'hj' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <hjStationOutage_barLine v-if="'hj' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <hjStationRepair_barLine v-if="'hj' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <hjStationOwner_barLine v-if="'hj' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <hjStationOwnerCutOutage_barLine v-if="'hj' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
            </el-tab-pane>
            <el-tab-pane label="电信"  name="dx">
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <dxStationAmount_bar v-if="'dx' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <dxStationBreakOpt_barLine v-if="'dx' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <dxStationOutage_barLine v-if="'dx' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <dxStationRepair_barLine v-if="'dx' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <dxStationOwner_barLine v-if="'dx' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <dxStationOwnerCutOutage_barLine v-if="'dx' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
            </el-tab-pane>
            <el-tab-pane label="移动" name="yd">
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <ydStationAmount_bar v-if="'yd' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <ydStationBreakOpt_barLine v-if="'yd' === activeName"/>

                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <ydStationOutage_barLine v-if="'yd' === activeName"/>

                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <ydStationRepair_barLine v-if="'yd' === activeName"/>

                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <ydStationOwner_barLine v-if="'yd' === activeName"/>

                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <ydStationOwnerCutOutage_barLine v-if="'yd' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
            </el-tab-pane>
            <el-tab-pane label="联通" name="lt">
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <ltStationAmount_bar v-if="'lt' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <ltStationBreakOpt_barLine v-if="'lt' === activeName"/>

                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <ltStationOutage_barLine v-if="'lt' === activeName"/>

                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <ltStationRepair_barLine v-if="'lt' === activeName"/>

                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <ltStationOwner_barLine v-if="'lt' === activeName"/>

                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <ltStationOwnerCutOutage_barLine v-if="'lt' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
            </el-tab-pane>
            <el-tab-pane label="铁塔" name="tt">
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <ttStationAmount_bar v-if="'tt' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <ttStationOutage_barLine v-if="'tt' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <ttStationRepair_barLine v-if="'tt' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <ttStationOwner_barLine v-if="'tt' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <div class="main">
                            <ttStationOwnerCutOutage_barLine v-if="'tt' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>

    import bg_png from '@/assets/img/beijing.png'


    import hjStationAmount_bar from '@/components/disaster/hjStationAmount_bar'
    import hjStationOutage_barLine from '@/components/disaster/hjStationOutage_barLine'
    import hjStationRepair_barLine from '@/components/disaster/hjStationRepair_barLine'
    import hjStationOwner_barLine from '@/components/disaster/hjStationOwner_barLine'
    import hjStationOwnerCutOutage_barLine from '@/components/disaster/hjStationOwnerCutOutage_barLine'
    import hjStationBreakOpt_barLine from '@/components/disaster/hjStationBreakOpt_barLine'

    import dxStationAmount_bar from '@/components/disaster/dxStationAmount_bar'
    import dxStationOutage_barLine from '@/components/disaster/dxStationOutage_barLine'
    import dxStationRepair_barLine from '@/components/disaster/dxStationRepair_barLine'
    import dxStationOwner_barLine from '@/components/disaster/dxStationOwner_barLine'
    import dxStationOwnerCutOutage_barLine from '@/components/disaster/dxStationOwnerCutOutage_barLine'
    import dxStationBreakOpt_barLine from '@/components/disaster/dxStationBreakOpt_barLine'

    import ydStationAmount_bar from '@/components/disaster/ydStationAmount_bar'
    import ydStationOutage_barLine from '@/components/disaster/ydStationOutage_barLine'
    import ydStationRepair_barLine from '@/components/disaster/ydStationRepair_barLine'
    import ydStationOwner_barLine from '@/components/disaster/ydStationOwner_barLine'
    import ydStationOwnerCutOutage_barLine from '@/components/disaster/ydStationOwnerCutOutage_barLine'
    import ydStationBreakOpt_barLine from '@/components/disaster/ydStationBreakOpt_barLine'

    import ltStationAmount_bar from '@/components/disaster/ltStationAmount_bar'
    import ltStationOutage_barLine from '@/components/disaster/ltStationOutage_barLine'
    import ltStationRepair_barLine from '@/components/disaster/ltStationRepair_barLine'
    import ltStationOwner_barLine from '@/components/disaster/ltStationOwner_barLine'
    import ltStationOwnerCutOutage_barLine from '@/components/disaster/ltStationOwnerCutOutage_barLine'
    import ltStationBreakOpt_barLine from '@/components/disaster/ltStationBreakOpt_barLine'

    import ttStationAmount_bar from '@/components/disaster/ttStationAmount_bar'
    import ttStationOutage_barLine from '@/components/disaster/ttStationOutage_barLine'
    import ttStationRepair_barLine from '@/components/disaster/ttStationRepair_barLine'
    import ttStationOwner_barLine from '@/components/disaster/ttStationOwner_barLine'
    import ttStationOwnerCutOutage_barLine from '@/components/disaster/ttStationOwnerCutOutage_barLine'
    import ttStationBreakOpt_barLine from '@/components/disaster/ttStationBreakOpt_barLine'


    export default {
        name: "disaster_county_view",

        data() {
            return {
                activeName: "hj",
                loading: false,
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
                        h1: 350
                    },
                    r: {
                        h1: 417
                    }
                }
            }
        },
        // watch: {
        //     activeName (v) {
        //         this.$nextTick(_ => {
        //             this.$refs[`chart${v}`].echarts.resize()
        //         })
        //     }
        // },
        mounted() {
            this.initHeight();
            setInterval(this.getTimes, 1000)
        },
        components: {
            hjStationAmount_bar,
            hjStationOutage_barLine,
            hjStationRepair_barLine,
            hjStationOwner_barLine,
            hjStationOwnerCutOutage_barLine,
            hjStationBreakOpt_barLine,

            dxStationAmount_bar,
            dxStationOutage_barLine,
            dxStationRepair_barLine,
            dxStationOwner_barLine,
            dxStationOwnerCutOutage_barLine,
            dxStationBreakOpt_barLine,

            ydStationAmount_bar,
            ydStationOutage_barLine,
            ydStationRepair_barLine,
            ydStationOwner_barLine,
            ydStationOwnerCutOutage_barLine,
            ydStationBreakOpt_barLine,

            ltStationAmount_bar,
            ltStationOutage_barLine,
            ltStationRepair_barLine,
            ltStationOwner_barLine,
            ltStationOwnerCutOutage_barLine,
            ltStationBreakOpt_barLine,

            ttStationAmount_bar,
            ttStationOutage_barLine,
            ttStationRepair_barLine,
            ttStationOwner_barLine,
            ttStationOwnerCutOutage_barLine,
            ttStationBreakOpt_barLine
        },
        methods: {
            initHeight() {
                var h = document.body.clientHeight;
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
        margin-top: -20px;
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

    .div title {
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
