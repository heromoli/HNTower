<template>
    <el-dialog
            title="工作流程详情"
            :visible.sync="visible"
            :fullscreen="true"
            :append-to-body="true">
        <el-image :src="processImgSrc"></el-image>
        <el-timeline>
            <el-timeline-item v-for="hi in historyList" :timestamp="hi.startTime" placement="top">
                <el-card>
                    <h4>{{hi.comment}}</h4>
                    <p>{{hi.assignee}} {{hi.endTime}}</p>
                </el-card>
            </el-timeline-item>
        </el-timeline>

        <el-container>
            <el-header>需求单详情</el-header>
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
                            <el-form-item label="电信企业" prop="operatorName">
                                <el-input type="text" v-model="dataForm.operatorName" :disabled="true"></el-input>
                                <!--<el-select v-model="dataForm.operatorName"  placeholder="请选择" style="width: 100%">-->
                                    <!--<el-option-->
                                            <!--v-for="item in operatorOptions"-->
                                            <!--:key="item.value"-->
                                            <!--:label="item.label"-->
                                            <!--:value="item.value">-->
                                    <!--</el-option>-->
                                <!--</el-select>-->
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="分公司" prop="branchCompany">
                                <el-input type="text" v-model="dataForm.branchCompany" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="区县" prop="region">
                                <el-input type="text" v-model="dataForm.region" :disabled="true"></el-input>
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
                            <el-form-item label="场景划分" prop="scene">
                                <el-select v-model="dataForm.scene" placeholder="请选择" style="width: 100%">
                                    <el-option
                                            v-for="item in sceneOptions"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="站点级别" prop="stationLevel">
                                <el-input type="text" v-model="dataForm.stationLevel"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="交付时间" prop="deliveryTime">
                                <el-date-picker v-model="dataForm.deliveryTime"
                                                type="date" placeholder="选择日期" style="width: 100%"
                                                value-format="yyyy-MM-dd">
                                </el-date-picker>
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
                            <el-form-item label="建设方式" prop="buildType">
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
                            <el-form-item label="是否共享" prop="ifShare">
                                <!--<el-input type="text" v-model="dataForm.ifShare"></el-input>-->
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
                        <el-col :span="6">
                            <el-form-item label="铁塔种类" prop="towerType">
                                <!--<el-input type="text" v-model="dataForm.towerType"></el-input> -->
                                <el-select v-model="dataForm.towerType" placeholder="请选择" style="width: 100%">
                                    <el-option
                                            v-for="item in towerTypeOptions"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="铁塔种类细分" prop="towerTypeDetail">
                                <!--<el-input type="text" v-model="dataForm.towerTypeDetail"></el-input> towerTypeDetailOptions-->
                                <el-select v-model="dataForm.towerTypeDetail" placeholder="请选择" style="width: 100%">
                                    <el-option
                                            v-for="item in towerTypeDetailOptions"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-collapse>
                        <el-collapse-item title="表5字段" name="1">
                            <div class="el-main">
                                <el-row>
                                    <el-col :span="24">
                                        <el-form-item></el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="站点编号" prop="stationNum">
                                            <el-input type="text" v-model="dataForm.stationNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="机房类型" prop="roomType">
                                            <el-input type="text" v-model="dataForm.roomType"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="产品配置" prop="productConfig">
                                            <el-input type="text" v-model="dataForm.productConfig"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="系统数" prop="systemNum">
                                            <el-input type="text" v-model="dataForm.systemNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>

                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="天线数" prop="antennaNum">
                                            <el-input type="text" v-model="dataForm.antennaNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="天线挂高" prop="antennaHeight">
                                            <el-input type="text" v-model="dataForm.antennaHeight"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="机位数" prop="seatNum">
                                            <el-input type="text" v-model="dataForm.seatNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="客户总功耗" prop="transEquitFullPower">
                                            <el-input type="text" v-model="dataForm.transEquitFullPower"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>

                                <!--<el-row>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="BBU数量" prop="bbuNum">-->
                                <!--<el-input type="text" v-model="dataForm.bbuNum"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="BBU尺寸" prop="bbuSize">-->
                                <!--<el-input type="text" v-model="dataForm.bbuSize"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="BBU重量" prop="bbuWeight">-->
                                <!--<el-input type="text" v-model="dataForm.bbuWeight"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="BBU总功耗" prop="bbuPower">-->
                                <!--<el-input type="text" v-model="dataForm.bbuPower"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--</el-row>-->

                                <!--<el-row>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="RRU数量" prop="rruNum">-->
                                <!--<el-input type="text" v-model="dataForm.rruNum"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="RRU尺寸" prop="rruSize">-->
                                <!--<el-input type="text" v-model="dataForm.rruSize"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="RRU重量" prop="rruWeight">-->
                                <!--<el-input type="text" v-model="dataForm.rruWeight"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="RRU总功耗" prop="rruPower">-->
                                <!--<el-input type="text" v-model="dataForm.rruPower"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--</el-row>-->

                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="风压系数" prop="windPressure">
                                            <el-input type="text" v-model="dataForm.windPressure"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="电力引入方式" prop="rruSupplyType">
                                            <el-input type="text" v-model="dataForm.rruSupplyType"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="备注" prop="remarks">
                                            <el-input type="text" v-model="dataForm.remarks"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="审核状态" prop="actProcStatus">
                                            <el-select v-model="dataForm.actProcStatus" placeholder="请选择"
                                                       style="width: 100%">
                                                <el-option
                                                        v-for="item in options"
                                                        :key="item.value"
                                                        :label="item.label"
                                                        :value="item.value"
                                                        :disabled="item.disabled">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                </el-row>

                                <!--<el-row>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="电池容量" prop="batteryCapacity">-->
                                <!--<el-input type="text" v-model="dataForm.batteryCapacity"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="电池保障时间" prop="batteryHour">-->
                                <!--<el-input type="text" v-model="dataForm.batteryHour"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="传输设备尺寸" prop="transEquitSize">-->
                                <!--<el-input type="text" v-model="dataForm.transEquitSize"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="传输设备重量" prop="transEquitWeight">-->
                                <!--<el-input type="text" v-model="dataForm.transEquitWeight"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--</el-row>-->

                                <!--<el-row>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="平台数量" prop="platformNum">-->
                                <!--<el-input type="text" v-model="dataForm.platformNum"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="调测时限" prop="debuggingTimeLimit">-->
                                <!--<el-input type="text" v-model="dataForm.debuggingTimeLimit"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="机房尺寸" prop="roomSize">-->
                                <!--<el-input type="text" v-model="dataForm.roomSize"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="与BBU共机柜" prop="ifBbu">-->
                                <!--<el-input type="text" v-model="dataForm.ifBbu"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--</el-row>-->

                                <!--<el-row>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="天线数量" prop="antennaNum">-->
                                <!--<el-input type="text" v-model="dataForm.antennaNum"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="单根天线尺寸" prop="antennaSize">-->
                                <!--<el-input type="text" v-model="dataForm.antennaSize"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="单根天线重量" prop="antennaWeight">-->
                                <!--<el-input type="text" v-model="dataForm.antennaWeight"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->

                                <!--</el-row>-->

                                <!--<el-row>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="电费结算方式" prop="payType">-->
                                <!--<el-input type="text" v-model="dataForm.payType"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="满配容量" prop="fullCapacity">-->
                                <!--<el-input type="text" v-model="dataForm.fullCapacity"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="本期配置容量" prop="thisCapacity">-->
                                <!--<el-input type="text" v-model="dataForm.thisCapacity"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="6">-->
                                <!--<el-form-item label="铁塔高度" prop="towerHeight">-->
                                <!--<el-input type="text" v-model="dataForm.towerHeight"></el-input>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!---->
                                <!--</el-row>-->
                            </div>
                        </el-collapse-item>
                    </el-collapse>

                </el-form>
            </el-main>
        </el-container>
        <span slot="footer" class="dialog-footer">
            <el-button @click="visible = false">关闭</el-button>
            <el-button v-if="isAuth('gzl:table4:update')" type="primary" @click="dataFormSubmit()">确定</el-button>
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
                operatorOptions: [{
                    value: '移动',
                    label: '移动'
                }, {
                    value: '联通',
                    label: '联通'
                }, {
                    value: '电信',
                    label: '电信'
                }],
                options: [{
                    value: '1',
                    label: '待审核',
                    disabled: true
                }, {
                    value: '2',
                    label: '站址筛查通过'
                }, {
                    value: '3',
                    label: '站址筛查不通过',
                    disabled: true
                }, {
                    value: '4',
                    label: '选址谈判完成，开始建站'
                }, {
                    value: '5',
                    label: '选址困难，提变更'
                }, {
                    value: '6',
                    label: '建站完成',
                    disabled: true
                }, {
                    value: '7',
                    label: '建站工程受阻，提变更',
                    disabled: true
                }, {
                    value: '8',
                    label: '建站进行中',
                    disabled: true
                }],
                shareOptions: [{
                    value: '是',
                    label: '是'
                }, {
                    value: '否',
                    label: '否'
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
                towerTypeOptions: [
                    {
                        value: '普通地面塔',
                        label: '普通地面塔'
                    }, {
                        value: '普通楼面塔',
                        label: '普通楼面塔'
                    }, {
                        value: '简易塔',
                        label: '简易塔'
                    }, {
                        value: '景观塔',
                        label: '景观塔'
                    }, {
                        value: '楼面抱杆',
                        label: '楼面抱杆'
                    }, {
                        value: '无铁塔',
                        label: '无铁塔'
                    }, {
                        value: '社会塔改造共享',
                        label: '社会塔改造共享'
                    }],
                towerTypeDetailOptions: [
                    {
                        value: 'H杆塔',
                        label: 'H杆塔'
                    }, {
                        value: '单管塔',
                        label: '单管塔'
                    }, {
                        value: '灯杆景观塔',
                        label: '灯杆景观塔'
                    }, {
                        value: '地面支撑杆',
                        label: '地面支撑杆'
                    }, {
                        value: '仿生树',
                        label: '仿生树'
                    }, {
                        value: '简易落地塔',
                        label: '简易落地塔'
                    }, {
                        value: '角钢塔',
                        label: '角钢塔'
                    }, {
                        value: '楼面抱杆',
                        label: '楼面抱杆'
                    }, {
                        value: '楼面拉线塔',
                        label: '楼面拉线塔'
                    }, {
                        value: '楼面美化塔',
                        label: '楼面美化塔'
                    }, {
                        value: '楼面增高架',
                        label: '楼面增高架'
                    }, {
                        value: '路灯杆塔',
                        label: '路灯杆塔'
                    }, {
                        value: '落地拉线塔',
                        label: '落地拉线塔'
                    }, {
                        value: '三管塔',
                        label: '三管塔'
                    }, {
                        value: '水泥杆塔',
                        label: '水泥杆塔'
                    }],
                dataRule: {
                    // longitude: [
                    //     {required: true, message: '经度不能为空', type: 'float', trigger: 'blur'}
                    // ],
                    // latitude: [
                    //     {required: true, message: '纬度不能未空', type: 'float', trigger: 'blur'}
                    // ]
                },
                processImgSrc: '',
                activeNames: ['1']

            }
        },
        computed: {},
        methods: {
            // 初始化
            init(row) {
                this.visible = true;
                this.history(row.actProcInstId);
                this.processImg(row.actProcInstId);
                this.dataFormFill(row.actProcInstId);
            },
            // 工作流记录
            history(id) {
                this.$http({
                    url: this.$http.adornUrl('/api/wf/findProcessHistoryById'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'processInstanceId': id,
                    })
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.historyList = data.history;
                    }else {
                        this.historyList = []
                    }
                })
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
            },
            // 表单提交
            dataFormSubmit() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        this.$http({
                            url: this.$http.adornUrl('/api/wf/checkProcesses'),
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
            processImg(id) {
                let url = this.$http.adornUrl(`/api/wf/processImg?processInstanceId=` + id);
                this.processImgSrc = url;

            },
            handleChange(val) {
            }

        }
    }
</script>

