<template>
    <div class="mod-home">
        <el-container style="height: 720px; border: #eee">
            <el-main>
                <el-row :gutter="20">
                    <el-col :span="6">
                        <el-card shadow="always">
                            <!--<span>光网在线</span>-->
                            <el-image :src="analytics"></el-image>
                            <div style="padding: 5px;" >
                                <div class="bottom clearfix">
                                    <el-button type="primary" @click="gwzx()">光网在线</el-button>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="6">
                        <el-card shadow="always">
                            <!--<span>资源管理</span>-->
                            <el-image :src="virtualbox"></el-image>
                            <div style="padding: 5px;">
                                <div class="bottom clearfix">
                                    <el-button type="primary" @click="zygl()">光网资源</el-button>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <!--<el-col :span="6">-->
                        <!--<el-card shadow="always">-->
                            <!--<span>工程管理</span>-->
                            <!--<el-image :src="travelplanner"></el-image>-->
                            <!--<div style="padding: 5px;">-->
                                <!--<div class="bottom clearfix">-->
                                    <!--<el-button type="primary" @click="">工程管理</el-button>-->
                                <!--</div>-->
                            <!--</div>-->
                        <!--</el-card>-->
                    <!--</el-col>-->
                </el-row>
            </el-main>

        </el-container>
    </div>
</template>

<script>

    import analytics from '@/assets/img/analytics.png'
    import virtualbox from '@/assets/img/virtualbox.png'
    import travelplanner from '@/assets/img/travelplanner.png'

    export default {
        data() {
            return {
                analytics: analytics,
                virtualbox: virtualbox,
                travelplanner:travelplanner
            }
        },
        methods: {
            gwzx() {
                // const {href} = this.$router.resolve({
                //     name: 'larg_screen'
                // });
                var href = this.$http.adornUrl("/gwzx/index.html");
                window.open(href, '_blank')

            },
            zygl() {
                const {href} = this.$router.resolve({
                    name: 'zhzygl'
                });
                window.open(href, '_blank')
            }
        }
    }
</script>

<style>
    .time {
        font-size: 13px;
        color: #999;
    }

    .bottom {
        margin-top: 5px;
        margin-bottom: 15px;
        line-height: 12px;
    }

    .button {
        padding: 0;
        float: right;
    }

    .image {
        width: 100%;
        display: block;
    }

    /*.clearfix:before,*/
    /*.clearfix:after {*/
        /*display: table;*/
        /*content: "";*/
    /*}*/

    /*.clearfix:after {*/
        /*clear: both*/
    /*}*/
</style>

