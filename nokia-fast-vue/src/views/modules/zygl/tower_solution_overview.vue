<template>
    <div :style="note" v-loading.fullscreen.lock="loading">
        <el-tabs type="border-card" v-model="activeName">
            <el-tab-pane label="全省" name="province">
                <el-row :gutter="2">
                    <el-col :span="12">
                        <div class="main">
                            <provincePlanStatus_bar v-if="'province' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <provinceprojectType_bar v-if="'province' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="2">
                    <el-col :span="6">
                        <div class="main">
                            <guihuaByBuildType_pie v-if="'province' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <div class="main">
                            <guihuaByStationType_pie v-if="'province' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <div class="main">
                            <guihuaByOperator_pie v-if="'province' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <div class="main">
                            <guihuaByNetType_pie v-if="'province' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="2">
                    <el-col :span="6">
                        <div class="main">
                            <xuqiuByBuildType_pie v-if="'province' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <div class="main">
                            <xuqiuByStationType_pie v-if="'province' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <div class="main">
                            <xuqiuByOperator_pie v-if="'province' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <div class="main">
                            <xuqiuByNetType_pie v-if="'province' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
            </el-tab-pane>
            <el-tab-pane label="分公司" name="city">
                <el-row :gutter="2">
                    <el-col :span="12">
                        <div class="main">
                            <cityPlanStatus_bar v-if="'city' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <cityProjectType_bar v-if="'city' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="2">
                    <el-col :span="12">
                        <div class="main">
                            <city-gh-by-build-type_bar v-if="'city' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <city-gh-by-station-type_bar v-if="'city' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="2">
                    <el-col :span="12">
                        <div class="main">
                            <city-gh-by-operator_bar v-if="'city' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <city-gh-by-net-type_bar v-if="'city' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="2">
                    <el-col :span="12">
                        <div class="main">
                            <city-xq-by-build-type_bar v-if="'city' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <city-xq-by-station-type_bar v-if="'city' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="2">
                    <el-col :span="12">
                        <div class="main">
                            <city-xq-by-operator_bar v-if="'city' === activeName"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="main">
                            <city-xq-by-net-type_bar v-if="'city' === activeName"/>
                        </div>
                    </el-col>
                </el-row>
            </el-tab-pane>

            <!--<el-tab-pane label="海口" name="hk">-->
                <!--<el-row :gutter="2">-->
                    <!--<el-col :span="12">-->
                        <!--<div class="main">-->
                            <!--<hkPlanStatus_bar v-if="'hk' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="12">-->
                        <!--<div class="main">-->
                            <!--<hkProjectType_bar v-if="'hk' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                <!--</el-row>-->
                <!--<el-row :gutter="2">-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<hkGuihuaByBuildType_pie v-if="'hk' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<hkGuihuaByStationType_pie v-if="'hk' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<hkGuihuaByOperator_pie v-if="'hk' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<hkGuihuaByNetType_pie v-if="'hk' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                <!--</el-row>-->
                <!--<el-row :gutter="2">-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<hkXuqiuByBuildType_pie v-if="'hk' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<hkXuqiuByStationType_pie v-if="'hk' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<hkXuqiuByOperator_pie v-if="'hk' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<hkXuqiuByNetType_pie v-if="'hk' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                <!--</el-row>-->
            <!--</el-tab-pane>-->
            <!--<el-tab-pane label="三亚" name="sy">-->
                <!--<el-row :gutter="2">-->
                    <!--<el-col :span="12">-->
                        <!--<div class="main">-->
                            <!--<syPlanStatus_bar v-if="'sy' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="12">-->
                        <!--<div class="main">-->
                            <!--<syProjectType_bar v-if="'sy' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                <!--</el-row>-->
                <!--<el-row :gutter="2">-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<syGuihuaByBuildType_pie v-if="'sy' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<syGuihuaByStationType_pie v-if="'sy' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<syGuihuaByOperator_pie v-if="'sy' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<syGuihuaByNetType_pie v-if="'sy' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                <!--</el-row>-->
                <!--<el-row :gutter="2">-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<syXuqiuByBuildType_pie v-if="'sy' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<syXuqiuByStationType_pie v-if="'sy' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<syXuqiuByOperator_pie v-if="'sy' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<syXuqiuByNetType_pie v-if="'sy' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                <!--</el-row>-->
            <!--</el-tab-pane>-->
            <!--<el-tab-pane label="琼海" name="qh">-->
                <!--<el-row :gutter="2">-->
                    <!--<el-col :span="12">-->
                        <!--<div class="main">-->
                            <!--<qhPlanStatus_bar v-if="'qh' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="12">-->
                        <!--<div class="main">-->
                            <!--<qhProjectType_bar v-if="'qh' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                <!--</el-row>-->
                <!--<el-row :gutter="2">-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<qhGuihuaByBuildType_pie v-if="'qh' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<qhGuihuaByStationType_pie v-if="'qh' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<qhGuihuaByOperator_pie v-if="'qh' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<qhGuihuaByNetType_pie v-if="'qh' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                <!--</el-row>-->
                <!--<el-row :gutter="2">-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<qhXuqiuByBuildType_pie v-if="'qh' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<qhXuqiuByStationType_pie v-if="'qh' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<qhXuqiuByOperator_pie v-if="'qh' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<qhXuqiuByNetType_pie v-if="'qh' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                <!--</el-row>-->
            <!--</el-tab-pane>-->
            <!--<el-tab-pane label="儋州" name="dz">-->
                <!--<el-row :gutter="2">-->
                    <!--<el-col :span="12">-->
                        <!--<div class="main">-->
                            <!--<dzPlanStatus_bar v-if="'dz' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="12">-->
                        <!--<div class="main">-->
                            <!--<dzProjectType_bar v-if="'dz' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                <!--</el-row>-->
                <!--<el-row :gutter="2">-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<dzGuihuaByBuildType_pie v-if="'dz' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<dzGuihuaByStationType_pie v-if="'dz' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<dzGuihuaByOperator_pie v-if="'dz' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<dzGuihuaByNetType_pie v-if="'dz' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                <!--</el-row>-->
                <!--<el-row :gutter="2">-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<dzXuqiuByBuildType_pie v-if="'dz' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<dzXuqiuByStationType_pie v-if="'dz' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<dzXuqiuByOperator_pie v-if="'dz' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                    <!--<el-col :span="6">-->
                        <!--<div class="main">-->
                            <!--<dzXuqiuByNetType_pie v-if="'dz' === activeName"/>-->
                        <!--</div>-->
                    <!--</el-col>-->
                <!--</el-row>-->
            <!--</el-tab-pane>-->
        </el-tabs>
    </div>
</template>

<script>

    import bg_png from '@/assets/img/beijing.png'

    import provincePlanStatus_bar from '@/components/tower_solution/planStatus_bar'
    import provinceprojectType_bar from '@/components/tower_solution/projectType_bar'
    import guihuaByBuildType_pie from '@/components/tower_solution/guihuaByBuildType_pie'
    import guihuaByStationType_pie from '@/components/tower_solution/guihuaByStationType_pie'
    import guihuaByOperator_pie from '@/components/tower_solution/guihuaByOperator_pie'
    import guihuaByNetType_pie from '@/components/tower_solution/guihuaByNetType_pie'
    import xuqiuByBuildType_pie from '@/components/tower_solution/xuqiuByBuildType_pie'
    import xuqiuByStationType_pie from '@/components/tower_solution/xuqiuByStationType_pie'
    import xuqiuByOperator_pie from '@/components/tower_solution/xuqiuByOperator_pie'
    import xuqiuByNetType_pie from '@/components/tower_solution/xuqiuByNetType_pie'

    import cityPlanStatus_bar from '@/components/tower_solution/cityPlanStatus_bar'
    import cityProjectType_bar from '@/components/tower_solution/cityProjectType_bar'
    import cityGhByBuildType_bar from '@/components/tower_solution/cityGhByBuildType_bar'
    import cityGhByNetType_bar from '@/components/tower_solution/cityGhByNetType_bar'
    import cityGhByOperator_bar from '@/components/tower_solution/cityGhByOperator_bar'
    import cityGhByStationType_bar from '@/components/tower_solution/cityGhByStationType_bar'
    import cityXqByBuildType_bar from '@/components/tower_solution/cityXqByBuildType_bar'
    import cityXqByNetType_bar from '@/components/tower_solution/cityXqByNetType_bar'
    import cityXqByOperator_bar from '@/components/tower_solution/cityXqByOperator_bar'
    import cityXqByStationType_bar from '@/components/tower_solution/cityXqByStationType_bar'

    // import hkPlanStatus_bar from '@/components/tower_solution/hkPlanStatus_bar'
    // import hkProjectType_bar from '@/components/tower_solution/hkProjectType_bar'
    // import hkGuihuaByBuildType_pie from '@/components/tower_solution/hkGuihuaByBuildType_pie'
    // import hkGuihuaByStationType_pie from '@/components/tower_solution/hkGuihuaByStationType_pie'
    // import hkGuihuaByOperator_pie from '@/components/tower_solution/hkGuihuaByOperator_pie'
    // import hkGuihuaByNetType_pie from '@/components/tower_solution/hkGuihuaByNetType_pie'
    // import hkXuqiuByBuildType_pie from '@/components/tower_solution/hkXuqiuByBuildType_pie'
    // import hkXuqiuByStationType_pie from '@/components/tower_solution/hkXuqiuByStationType_pie'
    // import hkXuqiuByOperator_pie from '@/components/tower_solution/hkXuqiuByOperator_pie'
    // import hkXuqiuByNetType_pie from '@/components/tower_solution/hkXuqiuByNetType_pie'
    //
    // import syPlanStatus_bar from '@/components/tower_solution/syPlanStatus_bar'
    // import syProjectType_bar from '@/components/tower_solution/syProjectType_bar'
    // import syGuihuaByBuildType_pie from '@/components/tower_solution/syGuihuaByBuildType_pie'
    // import syGuihuaByStationType_pie from '@/components/tower_solution/syGuihuaByStationType_pie'
    // import syGuihuaByOperator_pie from '@/components/tower_solution/syGuihuaByOperator_pie'
    // import syGuihuaByNetType_pie from '@/components/tower_solution/syGuihuaByNetType_pie'
    // import syXuqiuByBuildType_pie from '@/components/tower_solution/syXuqiuByBuildType_pie'
    // import syXuqiuByStationType_pie from '@/components/tower_solution/syXuqiuByStationType_pie'
    // import syXuqiuByOperator_pie from '@/components/tower_solution/syXuqiuByOperator_pie'
    // import syXuqiuByNetType_pie from '@/components/tower_solution/syXuqiuByNetType_pie'
    //
    // import qhPlanStatus_bar from '@/components/tower_solution/qhPlanStatus_bar'
    // import qhProjectType_bar from '@/components/tower_solution/qhProjectType_bar'
    // import qhGuihuaByBuildType_pie from '@/components/tower_solution/qhGuihuaByBuildType_pie'
    // import qhGuihuaByStationType_pie from '@/components/tower_solution/qhGuihuaByStationType_pie'
    // import qhGuihuaByOperator_pie from '@/components/tower_solution/qhGuihuaByOperator_pie'
    // import qhGuihuaByNetType_pie from '@/components/tower_solution/qhGuihuaByNetType_pie'
    // import qhXuqiuByBuildType_pie from '@/components/tower_solution/qhXuqiuByBuildType_pie'
    // import qhXuqiuByStationType_pie from '@/components/tower_solution/qhXuqiuByStationType_pie'
    // import qhXuqiuByOperator_pie from '@/components/tower_solution/qhXuqiuByOperator_pie'
    // import qhXuqiuByNetType_pie from '@/components/tower_solution/qhXuqiuByNetType_pie'
    //
    // import dzPlanStatus_bar from '@/components/tower_solution/dzPlanStatus_bar'
    // import dzProjectType_bar from '@/components/tower_solution/dzProjectType_bar'
    // import dzGuihuaByBuildType_pie from '@/components/tower_solution/dzGuihuaByBuildType_pie'
    // import dzGuihuaByStationType_pie from '@/components/tower_solution/dzGuihuaByStationType_pie'
    // import dzGuihuaByOperator_pie from '@/components/tower_solution/dzGuihuaByOperator_pie'
    // import dzGuihuaByNetType_pie from '@/components/tower_solution/dzGuihuaByNetType_pie'
    // import dzXuqiuByBuildType_pie from '@/components/tower_solution/dzXuqiuByBuildType_pie'
    // import dzXuqiuByStationType_pie from '@/components/tower_solution/dzXuqiuByStationType_pie'
    // import dzXuqiuByOperator_pie from '@/components/tower_solution/dzXuqiuByOperator_pie'
    // import dzXuqiuByNetType_pie from '@/components/tower_solution/dzXuqiuByNetType_pie'

    export default {
        name: "disaster_county_view",

        data() {
            return {
                activeName: "province",
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
            provincePlanStatus_bar,
            provinceprojectType_bar,
            guihuaByBuildType_pie,
            guihuaByStationType_pie,
            guihuaByOperator_pie,
            guihuaByNetType_pie,
            xuqiuByBuildType_pie,
            xuqiuByStationType_pie,
            xuqiuByOperator_pie,
            xuqiuByNetType_pie,

            cityPlanStatus_bar,
            cityProjectType_bar,
            cityGhByBuildType_bar,
            cityGhByNetType_bar,
            cityGhByOperator_bar,
            cityGhByStationType_bar,
            cityXqByBuildType_bar,
            cityXqByNetType_bar,
            cityXqByOperator_bar,
            cityXqByStationType_bar

            // hkPlanStatus_bar,
            // hkProjectType_bar,
            // hkGuihuaByBuildType_pie,
            // hkGuihuaByStationType_pie,
            // hkGuihuaByOperator_pie,
            // hkGuihuaByNetType_pie,
            // hkXuqiuByBuildType_pie,
            // hkXuqiuByStationType_pie,
            // hkXuqiuByOperator_pie,
            // hkXuqiuByNetType_pie,
            //
            // syPlanStatus_bar,
            // syProjectType_bar,
            // syGuihuaByBuildType_pie,
            // syGuihuaByStationType_pie,
            // syGuihuaByOperator_pie,
            // syGuihuaByNetType_pie,
            // syXuqiuByBuildType_pie,
            // syXuqiuByStationType_pie,
            // syXuqiuByOperator_pie,
            // syXuqiuByNetType_pie,
            //
            // qhPlanStatus_bar,
            // qhProjectType_bar,
            // qhGuihuaByBuildType_pie,
            // qhGuihuaByStationType_pie,
            // qhGuihuaByOperator_pie,
            // qhGuihuaByNetType_pie,
            // qhXuqiuByBuildType_pie,
            // qhXuqiuByStationType_pie,
            // qhXuqiuByOperator_pie,
            // qhXuqiuByNetType_pie,
            //
            // dzPlanStatus_bar,
            // dzProjectType_bar,
            // dzGuihuaByBuildType_pie,
            // dzGuihuaByStationType_pie,
            // dzGuihuaByOperator_pie,
            // dzGuihuaByNetType_pie,
            // dzXuqiuByBuildType_pie,
            // dzXuqiuByStationType_pie,
            // dzXuqiuByOperator_pie,
            // dzXuqiuByNetType_pie

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
