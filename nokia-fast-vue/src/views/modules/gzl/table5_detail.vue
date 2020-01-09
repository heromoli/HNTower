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
                                <el-input type="text" v-model="dataForm.operatorName"></el-input>
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
                            <el-form-item label="建设方式" prop="buildType">
                                <el-input type="text" v-model="dataForm.buildType"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="交付时间" prop="deliveryTime">
                                <el-input type="text" v-model="dataForm.deliveryTime"></el-input>
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
                            <el-form-item label="铁塔种类" prop="towerType">
                                <el-input type="text" v-model="dataForm.towerType"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="铁塔种类细分" prop="towerTypeDetail">
                                <el-input type="text" v-model="dataForm.towerTypeDetail"></el-input>
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

                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="天线挂高" prop="antennaHeight">
                                <el-input type="text" v-model="dataForm.antennaHeight"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="天线方向角" prop="antennaAngle">
                                <el-input type="text" v-model="dataForm.antennaAngle"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="机位数" prop="seatNum">
                                <el-input type="text" v-model="dataForm.seatNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="设备总功耗" prop="transEquitFullPower">
                                <el-input type="text" v-model="dataForm.transEquitFullPower"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-collapse>
                        <el-collapse-item title="基站建设信息字段" name="1">
                            <div class="el-main">
                                <el-row>
                                    <el-col :span="24">
                                        <el-form-item></el-form-item>
                                    </el-col>
                                </el-row>

                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="征址完成时间" prop="locationFinishTime">
                                            <el-date-picker
                                                    v-model="dataForm.locationFinishTime"
                                                    type="date" style="width: 100%"
                                                    placeholder="选择日期"
                                                    value-format="yyyy-MM-dd">
                                            </el-date-picker>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="进场时间" prop="intoTime">
                                            <el-date-picker
                                                    v-model="dataForm.intoTime"
                                                    type="date" style="width: 100%"
                                                    placeholder="选择日期" :picker-options="pickerOptionsIntoTime"
                                                    value-format="yyyy-MM-dd">
                                            </el-date-picker>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="土建完成时间" prop="buildFinishTime">
                                            <el-date-picker
                                                    v-model="dataForm.buildFinishTime"
                                                    type="date" style="width: 100%"
                                                    placeholder="选择日期" :picker-options="pickerOptionsBuildTime"
                                                    value-format="yyyy-MM-dd">
                                            </el-date-picker>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="铁塔完成时间" prop="towerFinishTime">
                                            <el-date-picker
                                                    v-model="dataForm.towerFinishTime"
                                                    type="date" style="width: 100%"
                                                    placeholder="选择日期" :picker-options="pickerOptionsBuildTime"
                                                    value-format="yyyy-MM-dd">
                                            </el-date-picker>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="引电完成时间" prop="electricFinishTime">
                                            <el-date-picker
                                                    v-model="dataForm.electricFinishTime"
                                                    type="date" style="width: 100%"
                                                    placeholder="选择日期" :picker-options="pickerOptionsBuildTime"
                                                    value-format="yyyy-MM-dd">
                                            </el-date-picker>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="配套完成时间" prop="matchingFinishTime">
                                            <el-date-picker
                                                    v-model="dataForm.matchingFinishTime"
                                                    type="date" style="width: 100%"
                                                    placeholder="选择日期" :picker-options="pickerOptionsMatchingTime"
                                                    value-format="yyyy-MM-dd">
                                            </el-date-picker>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="总体完工时间" prop="totalFinishTime">
                                            <el-date-picker
                                                    v-model="dataForm.totalFinishTime"
                                                    type="date" style="width: 100%"
                                                    placeholder="选择日期" :picker-options="pickerOptionsTotalTime"
                                                    value-format="yyyy-MM-dd">
                                            </el-date-picker>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="内验完成时间" prop="checkFinishTime">
                                            <el-date-picker
                                                    v-model="dataForm.checkFinishTime"
                                                    type="date" style="width: 100%"
                                                    placeholder="选择日期"
                                                    value-format="yyyy-MM-dd">
                                            </el-date-picker>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
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
                            </div>
                        </el-collapse-item>
                    </el-collapse>
                </el-form>
            </el-main>

        </el-container>
        <span slot="footer" class="dialog-footer">
            <el-button @click="visible = false">关闭</el-button>
            <el-button v-if="isAuth('gzl:table5:update')" type="primary" @click="dataFormSubmit()">确定</el-button>
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
                options: [{
                    value: '1',
                    label: '待审核',
                    disabled: true
                }, {
                    value: '2',
                    label: '站址筛查通过',
                    disabled: true
                }, {
                    value: '3',
                    label: '站址筛查不通过',
                    disabled: true
                }, {
                    value: '4',
                    label: '选址谈判完成，开始建站'
                }, {
                    value: '5',
                    label: '选址困难，提变更',
                    disabled: true
                }, {
                    value: '6',
                    label: '建站完成'
                }, {
                    value: '7',
                    label: '建站工程受阻，提变更'
                }, {
                    value: '8',
                    label: '建站进行中'
                }],
                dataRule: {},
                processImgSrc: '',
                activeNames: ['1'],
                pickerOptionsIntoTime:{
                    disabledDate: time => {
                        const beginDateVal = new Date(this.dataForm.locationFinishTime).getTime();
                        if (beginDateVal) {
                            return time.getTime() < beginDateVal + 0
                        }
                    }
                },
                pickerOptionsBuildTime:{
                    disabledDate: time => {
                        const beginDateVal = new Date(this.dataForm.intoTime).getTime();
                        if (beginDateVal) {
                            return time.getTime() < beginDateVal + 0
                        }
                    }
                },
                pickerOptionsMatchingTime:{
                    disabledDate: time => {
                        const beginDateVal = new Date(this.dataForm.towerFinishTime).getTime();
                        if (beginDateVal) {
                            return time.getTime() < beginDateVal + 0
                        }
                    }
                },
                pickerOptionsTotalTime:{
                    disabledDate: time => {
                        const beginDateVal = new Date(this.dataForm.matchingFinishTime).getTime();
                        if (beginDateVal) {
                            return time.getTime() < beginDateVal + 0
                        }
                    }
                }

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
                    } else {
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
//                this.$http({
//                    url: this.$http.adornUrl('/api/wf/processImg'),
//                    method: 'get',
//                    params: this.$http.adornParams({
//                        'processInstanceId': id,
//                    })
//                }).then(({data}) => {
//                    this.processImgSrc = data;
//                })
            }

        }
    }
</script>

