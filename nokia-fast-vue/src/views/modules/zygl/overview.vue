<template>
    <div  :style="note" v-loading.fullscreen.lock="loading">
        <!--<el-container >-->
            <!--<el-header>-->
                <el-row :gutter="3">
                    <el-col :span="6" style="float: left">
                        <template>
                            <!--<img :src="logo"  :width="200" :height="50" >-->
                            <h1 class="logo_style3">光网在线综合资源管理</h1>
                        </template>
                    </el-col>
                    <el-col :span="6">

                    </el-col>
                    <el-col :span="6">

                    </el-col>
                    <el-col :span="6" style="float: right;margin-top: 10px">
                        <template>
                            <iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=48" width="auto" height="50px" frameborder="0" marginwidth="10" marginheight="0" scrolling="no"></iframe>
                        </template>
                    </el-col>
                </el-row>
            <!--</el-header>-->
            <!--<el-main>-->
            <el-row>
                <el-col :span="8">
                    <template v-if="!loading">
                        <cityStation_pie/>
                    </template>
                    <template v-if="!loading">
                        <operatorStation_pie/>
                    </template>
                </el-col>
                <!--<el-col :span="4">-->
                    <!--<template v-if="!loading">-->
                        <!--<chanquan_pie/>-->
                    <!--</template>-->
                    <!--<template v-if="!loading">-->
                        <!--<shareRate_radar/>-->
                    <!--</template>-->

                <!--</el-col>-->
                <el-col :span="8">
                    <template v-if="!loading">
                        <hainan_map/>
                    </template>
                </el-col>
                <el-col :span="8">
                    <template v-if="!loading">
                        <stationShare_venn/>
                    </template>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">
                    <template v-if="!loading">
                        <yearlyStation_line/>
                    </template>
                </el-col>
                <el-col :span="12">
                    <template v-if="!loading">
                        <station5gBuild_bar/>
                    </template>
                </el-col>
                <el-col :span="6">
                    <template v-if="!loading">
                        <monthlyStation5g_line/>
                    </template>
                </el-col>
            </el-row>
            <!--</el-main>-->
            <!--<el-footer></el-footer>-->
        <!--</el-container>-->
    </div>
</template>

<script>

    import bg_png from '@/assets/img/01p79w.jpg'
    import logo_png from '@/assets/img/logo.png'
    import hainan_map from '@/components/zhzygl/hainan_map'
    import chanquan_pie from '@/components/zhzygl/chanquan_pie'
    import cityStation_pie from '@/components/zhzygl/cityStation_pie'
    import stationShare_bar from '@/components/zhzygl/stationShare_bar'
    import bizScene_bar from '@/components/zhzygl/bizScene_bar'
    import yearlyStation_line from '@/components/zhzygl/yearlyStation_line'
    import stationSouorce_pie from '@/components/zhzygl/stationSource_pie'
    import shareRate_radar from '@/components/zhzygl/shareRate_radar'
    import operatorStation_pie from '@/components/zhzygl/operatorStation_pie'
    import highCharts_stationShare from '@/components/zhzygl/highCharts_stationShare'
    import stationShare_venn from '@/components/zhzygl/stationShare_venn'
    import station5gBuild_bar from '@/components/zhzygl/station5gBuild_bar'
    import monthlyStation5g_line from '@/components/zhzygl/monthlyStation5g_line'

    export default {
        name: "overview",

        data() {
            return {
                logo: logo_png,
                loading: false,
                note: {
                    backgroundImage: "url(" + bg_png + ") ",
                    backgroundPosition: "center center",
                    backgroundRepeat: "no-repeat",
                    backgroundSize: "cover",
                    // marginTop: "5px",
                    // backgroundColor:  "#33a4f4"
                }
            }
        },
        mounted() {
        },
        components: {
            hainan_map,
            chanquan_pie,
            cityStation_pie,
            stationShare_bar,
            bizScene_bar,
            yearlyStation_line,
            stationSouorce_pie,
            shareRate_radar,
            operatorStation_pie,
            // highCharts_stationShare,
            station5gBuild_bar,
            monthlyStation5g_line,
            stationShare_venn
        },
        methods:{
            get_weather: function(){
                this.$axios.get("http://wthrcdn.etouch.cn/weather_mini?city=海口").then(function(response){
                    console.log(response.data);
                }).catch(function(error){
                    console.log(error);
                })
            }
        }

    }
</script>

<style lang="scss" scoped>
    .logo_style {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 2.5em;
        /*vertical-align: middle;*/
        font-weight: bolder;
        color: #e1f3d8;
    }

    .logo_style1 {
        width: auto;
        margin-top: 12px;
        float: right;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        font-size: 11px;
        color: #999999;
        line-height: 25px;
        letter-spacing: 1px

    }

    .logo_style2 {
        margin: 0px;
        padding: 0px;
        background-color: #E7EAEB;
        font-family: "微软雅黑", "黑体", "宋体";
        font-size: 12px;
        height: 36px;
    }

    .logo_style3 {
        width: auto;
        height: 30px;
        margin-left: 50px;
        font-family: Arial, Helvetica, sans-serif;
        font-size: 2em;
        color: #e1f3d8;
        line-height: 35px;
        text-transform: uppercase
    }

    .logo_style4 {
        background: #e1e5e8;
        font-family: "Georgia", Tahoma, Sans-Serif;
        font-size: 12px;
        line-height: 18px;
        color: #888;
    }

    .logo_style5 {
        width: 130px;
        height: 20px;
        /*background: url(images/servicesbg.gif) 00 repeat-x #68EF00;*/
        color: #317400;
        font: 18px/14px Georgia, "Times New Roman", Times, serif;
        margin: 34px 37px;
    }

    .logo_style6 {
        margin: 0;
        padding: 0;
        line-height: 1.5em;
        font-family: Verdana, Geneva, sans-serif;
        font-size: 11px;
        color: #ffffff;
        background: #005b7f;
    }
</style>
