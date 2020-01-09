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
            <el-header>需求变更确认</el-header>
            <el-main>
                <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
                         label-width="100px">
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
                            <el-form-item label="站点编号" prop="stationNum">
                                <el-input type="text" v-model="dataForm.stationNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
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
                        <el-col :span="12">
                            <el-form-item label="地址" prop="address">
                                <el-input type="text" v-model="dataForm.address"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="变更原因" prop="changeReason">
                                <el-input type="text" v-model="dataForm.changeReason"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="变更内容及解决方案" label-width="150px" prop="towerType">
                                <el-input type="text" v-model="dataForm.changeContentAndSolution"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="变更费用" prop="roomType">
                                <el-input type="text" v-model="dataForm.changeCost"></el-input>
                            </el-form-item>
                        </el-col>
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
                dataForm: {
                    changeReason: "",
                    changeContentAndSolution: "",
                    changeCost: "",
                    tableId: ""
                },
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
                console.log(row);
                this.visible = true;
                this.history(row.actProcInstId);
                this.processImg(row.actProcInstId);
                this.dataFormFill(row.actProcInstId);
                this.dataForm.tableId = row.tableId;
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
                            url: this.$http.adornUrl('/api/wf/demandChange'),
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

