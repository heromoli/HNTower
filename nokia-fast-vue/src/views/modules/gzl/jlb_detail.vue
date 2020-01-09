<template>
    <el-dialog
            title="监理表详情"
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
                                <el-input type="text" v-model="dataForm.branchCompany" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="市县" prop="region">
                                <el-input type="text" v-model="dataForm.county" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="区域" prop="scene" :disabled="true">
                                <el-input type="text" v-model="dataForm.region"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="站点名称" prop="stationName">
                                <el-input type="text" v-model="dataForm.stationName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="运营商站点名" prop="operatorStationName">
                                <el-input type="text" v-model="dataForm.operatorStationName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="专项站点" prop="specialStation">
                                <el-input type="text" v-model="dataForm.specialStation"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="站址编号" prop="stationNum">
                                <el-input type="text" v-model="dataForm.stationNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="机房编号" prop="roomNum">
                                <el-input type="text" v-model="dataForm.roomNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="订单编号" prop="orderNum">
                                <el-input type="text" v-model="dataForm.orderNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="运营商编号" prop="operatorNum">
                                <el-input type="text" v-model="dataForm.operatorNum"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="运营商修正号" prop="operatorNumCorrect">
                                <el-input type="text" v-model="dataForm.operatorNumCorrect"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="需求提出时间" prop="demandProposeTime">
                                <!--<el-input type="text" v-model="dataForm.demandProposeTime"></el-input>-->
                                <el-date-picker
                                        v-model="dataForm.demandProposeTime"
                                        type="date" style="width: 100%"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="交付时间" prop="deliveryTime">
                                <!--<el-input type="text" v-model="dataForm.deliveryTime"></el-input>-->
                                <el-date-picker
                                        v-model="dataForm.deliveryTime"
                                        type="date" style="width: 100%"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="场景划分" prop="scene">
                                <el-select v-model="dataForm.scene"  placeholder="请选择" style="width: 100%">
                                    <el-option
                                            v-for="item in sceneOptions"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
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
                        <el-col :span="12">
                            <el-form-item label="详细地址" prop="address">
                                <el-input type="text" v-model="dataForm.address"></el-input>
                            </el-form-item>
                        </el-col>

                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="建设方式" prop="contact">
                                <el-select v-model="dataForm.buildType" placeholder="请选择" style="width: 100%">
                                    <el-option
                                            v-for="item in buildTypeOptions"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="移动建设模式" prop="mobileBuildType">
                                <el-input type="text" v-model="dataForm.mobileBuildType"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="共享共建方式" prop="ifShare">
                                <el-input type="text" v-model="dataForm.ifShare"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="楼面/地面" prop="buildingOrGround">
                                <el-input type="text" v-model="dataForm.buildingOrGround"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="征址完成时间" prop="locationFinishTime">
                                <!--<el-input type="text" v-model="dataForm.locationFinishTime"></el-input>-->
                                <el-date-picker
                                        v-model="dataForm.locationFinishTime"
                                        type="date" style="width: 100%"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="进场时间" prop="intoTime">
                                <!--<el-input type="text" v-model="dataForm.intoTime"></el-input>-->
                                <el-date-picker
                                        v-model="dataForm.intoTime"
                                        type="date" style="width: 100%"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="土建完成时间" prop="buildFinishTime">
                                <!--<el-input type="text" v-model="dataForm.buildFinishTime"></el-input>-->
                                <el-date-picker
                                        v-model="dataForm.buildFinishTime"
                                        type="date" style="width: 100%"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="铁塔完成时间" prop="towerFinishTime">
                                <!--<el-input type="text" v-model="dataForm.towerFinishTime"></el-input>-->
                                <el-date-picker
                                        v-model="dataForm.towerFinishTime"
                                        type="date" style="width: 100%"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="引电完成时间" prop="electricFinishTime">
                                <!--<el-input type="text" v-model="dataForm.electricFinishTime"></el-input>-->
                                <el-date-picker
                                        v-model="dataForm.electricFinishTime"
                                        type="date" style="width: 100%"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="配套完成时间" prop="matchingFinishTime">
                                <!--<el-input type="text" v-model="dataForm.matchingFinishTime"></el-input>-->
                                <el-date-picker
                                        v-model="dataForm.matchingFinishTime"
                                        type="date" style="width: 100%"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="总体完工时间" prop="totalFinishTime">
                                <!--<el-input type="text" v-model="dataForm.totalFinishTime"></el-input>-->
                                <el-date-picker
                                        v-model="dataForm.totalFinishTime"
                                        type="date" style="width: 100%"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="内验完成时间" prop="checkFinishTime">
                                <!--<el-input type="text" v-model="dataForm.checkFinishTime"></el-input>-->
                                <el-date-picker
                                        v-model="dataForm.checkFinishTime"
                                        type="date" style="width: 100%"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="交付完成时间" prop="deliverFinishTime">
                                <el-date-picker
                                        v-model="dataForm.deliverFinishTime"
                                        type="date" style="width: 100%"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="工程状态分类" prop="projectState">
                                <!--<el-input type="text" v-model="dataForm.projectState"></el-input>-->
                                <el-select v-model="dataForm.projectState" placeholder="请选择" style="width: 100%">
                                    <el-option
                                            v-for="item in projectStateOptions"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="工程状态描述" prop="projectStateDescribe">
                                <el-input type="text" v-model="dataForm.projectStateDescribe"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="任务年份" prop="projectYear">
                                <el-input type="text" v-model="dataForm.projectYear"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="是否政府协调" prop="ifGovernment">
                                <el-input type="text" v-model="dataForm.ifGovernment"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </el-main>
        </el-container>
        <span slot="footer" class="dialog-footer">
            <el-button @click="visible = false">关闭</el-button>
            <el-button v-if="isAuth('gzl:jlb:update')"  type="primary" @click="dataFormSubmit()">确定</el-button>
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
                dataRule: {},
                shareOptions: [{
                    value: '是',
                    label: '是'
                }, {
                    value: '否',
                    label: '否'
                }],
                sceneOptions: [{
                    value: '密集市区',
                    label: '密集市区'
                }, {
                    value: '一般市区',
                    label: '一般市区'
                }, {
                    value: '县城',
                    label: '县城'
                }, {
                    value: '乡镇',
                    label: '乡镇'
                }, {
                    value: '农村',
                    label: '农村'
                }],
                buildTypeOptions: [{
                    value: '新建',
                    label: '新建'
                }, {
                    value: '改造',
                    label: '改造'
                }, {
                    value: '存量直接满足',
                    label: '存量直接满足'
                }],
                projectStateOptions: [{
                    value: '整合中',
                    label: '整合中'
                }, {
                    value: '未征址',
                    label: '未征址'
                }, {
                    value: '正常建设',
                    label: '正常建设'
                }, {
                    value: '建设受阻',
                    label: '建设受阻'
                }, {
                    value: '未内验',
                    label: '未内验'
                }, {
                    value: '交付流程中',
                    label: '交付流程中'
                }, {
                    value: '运营商原因不肯交付',
                    label: '运营商原因不肯交付'
                }, {
                    value: '起租流程中',
                    label: '起租流程中'
                }, {
                    value: '运营商原因不肯起租',
                    label: '运营商原因不肯起租'
                }, {
                    value: '已销项',
                    label: '已销项'
                }, {
                    value: '未销项',
                    label: '未销项'
                }, {
                    value: '未起租',
                    label: '未起租'
                }],
            }
        },
        computed: {},
        methods: {
            // 初始化
            init(row) {
                this.visible = true;
                this.dataFormFill(row.id);
            },
            //表单填充
            dataFormFill(id) {
                this.$http({
                    url: this.$http.adornUrl('/api/wf/fillSupervisorFormById'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'id': id,
                    })
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataForm = data.returnData;
                    } else {
                        this.$message.error(data.msg)
                    }
                });
                this.dataForm = Object.assign({});
            },
            dataFormSubmit() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        this.$http({
                            url: this.$http.adornUrl('/api/wf/supervisionUpdate'),
                            method: 'post',
                            data: this.$http.adornData(this.dataForm)
                        }).then(({data}) => {
                            if (data && data.code === 0) {
                                this.$message({
                                    message: '操作成功',
                                    type: 'success',
                                    duration: 1500,
                                    onClose: () => {
                                        this.visible = false;
                                        this.$emit('refreshDataList');
                                    }
                                })
                            } else {
                                this.$message.error(data.msg)
                            }
                        })
                    }
                })
            },

        }
    }
</script>

