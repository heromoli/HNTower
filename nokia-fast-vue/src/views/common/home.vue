<template>
    <el-container style="height: 760px;">
        <el-main>
            <el-row :gutter="20">
                <el-col :span="4">
                    <el-card :body-style="{ padding: '0px 20px' }" shadow="always">
                        <!--<span>光网在线</span>-->
                        <el-image :src="duibifenxi"></el-image>
                        <div class="bottom">
                            <el-button type="primary" @click="gwzx()">光网在线</el-button>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="4">
                    <el-card :body-style="{ padding: '0px 20px' }" shadow="always">
                        <!--<span>资源管理</span>-->
                        <el-image :src="fenxi"></el-image>
                        <div class="bottom">
                            <el-button type="primary" @click="zygl()">光网资源</el-button>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="4">
                    <el-card :body-style="{ padding: '0px 20px' }" shadow="always">
                        <!--<span>光网在线</span>-->
                        <el-image :src="liucheng"></el-image>
                        <div class="bottom">
                            <el-button type="primary">工作流程</el-button>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="4">
                    <el-card :body-style="{ padding: '0px 20px' }" shadow="always">
                        <!--<span>光网在线</span>-->
                        <el-image :src="baobiao"></el-image>
                        <div class="bottom">
                            <el-button type="primary">报表管理</el-button>
                        </div>
                    </el-card>
                </el-col>

                <el-col :span="4">
                    <el-card :body-style="{ padding: '0px 20px' }" shadow="always">
                        <!--<span>工程管理</span>-->
                        <el-image :src="jiankong"></el-image>
                        <div class="bottom">
                            <el-button type="primary">智慧运维</el-button>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="4">
                    <el-card :body-style="{ padding: '0px 20px' }" shadow="always">
                        <!--<span>工程管理</span>-->
                        <el-image :src="keshihua"></el-image>
                        <div class="bottom">
                            <el-button type="primary">AI识别</el-button>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </el-main>
    </el-container>
</template>

<script>

    import baobiao from '@/assets/icon/baobiao.png'
    import bendihua from '@/assets/icon/bendihua.png'
    import duibifenxi from '@/assets/icon/duibifenxi.png'
    import fenxi from '@/assets/icon/fenxi.png'
    import gaojing from '@/assets/icon/gaojing.png'
    import jiankong from '@/assets/icon/jiankong.png'
    import keshihua from '@/assets/icon/keshihua.png'
    import liucheng from '@/assets/icon/liucheng.png'

    export default {
        data() {
            return {
                baobiao: baobiao,
                bendihua: bendihua,
                duibifenxi: duibifenxi,
                fenxi: fenxi,
                gaojing: gaojing,
                jiankong: jiankong,
                keshihua: keshihua,
                liucheng: liucheng

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
                // const {href} = this.$router.resolve({
                //     name: 'zhzygl'
                // });
                var href = this.$http.adornUrl("/gwzy/index.html");
                window.open(href, '_blank')
            },
            gzlc() {
                const {href} = this.$router.resolve({
                    name: 'larg_screen'
                });
                // var href = this.$http.adornUrl("/gwzx/index.html");
                window.open(href, '_blank')

            },
            bbgl() {
                const {href} = this.$router.resolve({
                    name: 'larg_screen'
                });
                // var href = this.$http.adornUrl("/gwzx/index.html");
                window.open(href, '_blank')

            },
        }
    }
</script>

<style scoped>

    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

    .bottom {
        margin-top: 5px;
        margin-bottom: 15px;
        line-height: 12px;
    }
</style>

