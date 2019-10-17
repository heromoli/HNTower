<template>
    <el-dialog
            title="工作流程详情"
            :visible.sync="visible"
            :fullscreen="true"
            :append-to-body="true">
        <el-image :src="processImgSrc"></el-image>
        <!--<img :src="processImgSrc">-->
        <!--<el-input v-model="processImgSrc">-->
        <!--</el-input>-->
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
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="分公司" prop="branchCompany">
                                <el-input type="text" v-model="dataForm.branchCompany"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="地市" prop="county">
                                <el-input type="text" v-model="dataForm.county"></el-input>
                            </el-form-item>
                        </el-col>

                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="区县" prop="region">
                                <el-input type="text" v-model="dataForm.region"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="站点名称" prop="stationName">
                                <el-input type="text" v-model="dataForm.stationName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="场景划分" prop="scene">
                                <el-input type="text" v-model="dataForm.scene"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="站点级别" prop="stationLevel">
                                <el-input type="text" v-model="dataForm.stationLevel"></el-input>
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
                        <el-col :span="12">
                            <el-form-item label="范围描述" prop="coverRangeDescribe">
                                <el-input type="text" v-model="dataForm.coverRangeDescribe"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="偏离半径" prop="deviateRadius">
                                <el-input type="text" v-model="dataForm.deviateRadius"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="交付时间" prop="deliveryTime">
                                <el-date-picker
                                        v-model="dataForm.deliveryTime"
                                        type="date" style="width: 100%"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>

                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="系统数" prop="systemNum">
                                <el-input type="text" v-model="dataForm.systemNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="BBU数量" prop="bbuNum">
                                <el-input type="text" v-model="dataForm.bbuNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="RRU数量" prop="rruNum">
                                <el-input type="text" v-model="dataForm.rruNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="天线挂高范围" prop="antennaHeight">
                                <el-input type="text" v-model="dataForm.antennaHeight"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="天线方向角" prop="antennaAngle">
                                <el-input type="text" v-model="dataForm.antennaAngle"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="天线个数" prop="antennaNum">
                                <el-input type="text" v-model="dataForm.antennaNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="联系人" prop="contact">
                                <el-input type="text" v-model="dataForm.contact"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="是否共享" prop="ifShare">
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

                    <el-collapse>
                        <el-collapse-item title="表4字段" name="1">
                            <div class="el-main" id="showOrHide">
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
            <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
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
                shareOptions: [{
                    value: '是',
                    label: '是'
                }, {
                    value: '否',
                    label: '否'
                }],
                options: [{
                    value: '1',
                    label: '待审核'
                }, {
                    value: '2',
                    label: '站址筛查通过'
                }, {
                    value: '3',
                    label: '站址筛查不通过'
                }, {
                    value: '4',
                    label: '选址谈判完成，开始建站',
                    disabled: true
                }, {
                    value: '5',
                    label: '选址困难，提变更',
                    disabled: true
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

