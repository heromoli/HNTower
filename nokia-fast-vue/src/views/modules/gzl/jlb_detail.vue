<template>
    <el-dialog
            title="工作流程详情"
            :visible.sync="visible"
            :fullscreen="true"
            :append-to-body="true">

        <el-container>
            <el-header>监理表详情</el-header>
            <el-main>

                <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
                         label-width="105px">

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="需求编号" prop="demandNum">
                                <el-input type="text" v-model="dataForm.demandNum" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="需求来源" prop="demandSource">
                                <el-input type="text" v-model="dataForm.demandSource" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="分公司" prop="branchCompany">
                                <el-input type="text" v-model="dataForm.branchCompany"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="区县" prop="region">
                                <el-input type="text" v-model="dataForm.region"></el-input>
                            </el-form-item>
                        </el-col>

                    </el-row>
                    <el-row>

                        <el-col :span="6">
                            <el-form-item label="站点名称" prop="stationName">
                                <el-input type="text" v-model="dataForm.stationName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="运营商站点名称" prop="operatorStationName">
                                <el-input type="text" v-model="dataForm.operatorStationName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="场景划分" prop="scene">
                                <el-input type="text" v-model="dataForm.scene"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="经度" prop="longitude">
                                <el-input type="text" v-model="dataForm.longitude"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="纬度" prop="latitude">
                                <el-input type="text" v-model="dataForm.latitude"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="详细地址" prop="address">
                                <el-input type="text" v-model="dataForm.address"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="建设方式" prop="buildType">
                                <el-input type="text" v-model="dataForm.buildType"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="是否共享" prop="ifShare">
                                <el-input type="text" v-model="dataForm.ifShare"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="征址完成时间" prop="locationFinishTime">
                                <el-input type="text" v-model="dataForm.locationFinishTime"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="进场时间" prop="intoTime">
                                <el-input type="text" v-model="dataForm.intoTime"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="土建完成时间" prop="buildFinishTime">
                                <el-input type="text" v-model="dataForm.buildFinishTime"></el-input>
                            </el-form-item>
                        </el-col>

                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="铁塔完成时间" prop="towerFinishTime">
                                <el-input type="text" v-model="dataForm.towerFinishTime"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="引电完成时间" prop="electricFinishTime">
                                <el-input type="text" v-model="dataForm.electricFinishTime"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="配套完成时间" prop="matchingFinishTime">
                                <el-input type="text" v-model="dataForm.matchingFinishTime"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="总体完工时间" prop="totalFinishTime">
                                <el-input type="text" v-model="dataForm.totalFinishTime"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="工程状态分类" prop="projectState">
                                <el-input type="text" v-model="dataForm.projectState"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="工程状态描述" prop="projectStateDescribe">
                                <el-input type="text" v-model="dataForm.projectStateDescribe"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="建设中倒排时间" prop="contact">
                                <el-input type="text" v-model="dataForm.contact"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="站址编号" prop="ifShare">
                                <el-select v-model="dataForm.ifShare" placeholder="请选择" style="width: 100%">
                                    <el-option
                                            v-for="item in shareOptions"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>


                    <el-row>
                        <el-col :span="24">
                            <el-form-item></el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="共享存量站址产权归属方" prop="shareStationBelong">
                                <el-input type="text" v-model="dataForm.shareStationBelong"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="原有运营商数" prop="oldOperatorNum">
                                <el-input type="text" v-model="dataForm.oldOperatorNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="原有运营商" prop="oldOperator">
                                <el-input type="text" v-model="dataForm.oldOperator"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="新增运营商数" prop="newOperatorNum">
                                <el-input type="text" v-model="dataForm.newOperatorNum"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>

                        <el-col :span="6">
                            <el-form-item label="新增运营商" prop="newOperator">
                                <el-input type="text" v-model="dataForm.newOperator"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="铁塔种类" prop="towerType">
                                <el-input type="text" v-model="dataForm.towerType"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="铁塔细分种类" prop="towerTypeDetail">
                                <el-input type="text" v-model="dataForm.towerTypeDetail"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="建设方式" prop="buildType">
                                <el-input type="text" v-model="dataForm.buildType"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="18">
                            <el-form-item label="备注" prop="remarks">
                                <el-input type="text" v-model="dataForm.remarks"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                </el-form>
            </el-main>

        </el-container>
        <span slot="footer" class="dialog-footer">
            <el-button @click="visible = false">关闭</el-button>
            <!--<el-button type="primary" @click="dataFormSubmit()">确定</el-button>-->
        </span>
    </el-dialog>
</template>

<style>
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
</style>

<script>
    export default {
        data() {
            return {
                visible: false,
                dataForm: {},
                historyList: [],
                dataRule: {},
                processImgSrc: '',
                activeNames: ['1']
            }
        },
        computed: {},
        methods: {
            // 初始化
            init(row) {
                this.visible = true;
                this.dataFormFill(row.actProcInstId);
            },
            //表单填充
            dataFormFill(id) {
                this.$http({
                    url: this.$http.adornUrl('/api/wf/fillSupervisorForm'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'processInstanceId': id,
                    })
                }).then(({data}) => {

                    if (data && data.code === 0) {
                        this.dataForm = data.returnData;
                    } else {
                        this.$message.error(data.msg)
                    }
                });
                this.dataForm = Object.assign({});
            }

        }
    }
</script>

