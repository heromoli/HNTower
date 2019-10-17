<template>
    <el-dialog
            title="建站新需求"
            :visible.sync="visible"
            :append-to-body="true">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
                 label-width="80px">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="运营商" prop="operatorName">
                        <!--<el-input type="text" v-model="dataForm.operatorName"></el-input>-->
                        <el-select v-model="dataForm.operatorName"  placeholder="请选择" style="width: 100%">
                            <el-option
                                    v-for="item in options"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.label">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="分公司" prop="branchCompany">
                        <el-input type="text" v-model="dataForm.branchCompany"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="地市" prop="county">
                        <el-input type="text" v-model="dataForm.county"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="区县" prop="region">
                        <el-input type="text" v-model="dataForm.region"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="站点名称" prop="stationName">
                        <el-input type="text" v-model="dataForm.stationName"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="场景划分" prop="scene">
                        <el-input type="text" v-model="dataForm.scene"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="站点级别" prop="stationLevel">
                        <el-input type="text" v-model="dataForm.stationLevel"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="经度" prop="longitude">
                        <el-input type="text" v-model="dataForm.longitude"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
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
                    <el-form-item label="覆盖范围" prop="coverRangeDescribe">
                        <el-input type="text" v-model="dataForm.coverRangeDescribe"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="偏离半径" prop="deviateRadius">
                        <el-input type="text" v-model="dataForm.deviateRadius"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="交付时间" prop="deliveryTime">
                        <el-date-picker
                                v-model="dataForm.deliveryTime"
                                type="date" style="width: 100%"
                                placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="系统数" prop="systemNum">
                        <el-input type="text" v-model="dataForm.systemNum"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="BBU数" prop="bbuNum">
                        <el-input type="text" v-model="dataForm.bbuNum"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="RRU数" prop="rruNum">
                        <el-input type="text" v-model="dataForm.rruNum"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="天线挂高" prop="antennaHeight">
                        <el-input type="text" v-model="dataForm.antennaHeight"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="天线方向" prop="antennaAngle">
                        <el-input type="text" v-model="dataForm.antennaAngle"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="天线个数" prop="antennaNum">
                        <el-input type="text" v-model="dataForm.antennaNum"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="联系人" prop="contact">
                        <el-input type="text" v-model="dataForm.contact"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="备注" prop="remarks">
                        <el-input type="text" v-model="dataForm.remarks"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
    </el-dialog>
</template>

<script>
    export default {
        data() {
            return {
                visible: false,
                dataForm: {
                    demandNum: '',
                    operatorName: '',
                    branchCompany: '',
                    county: '',
                    region: '',
                    stationName: '',
                    scene: '',
                    stationLevel: '',
                    longitude: '',
                    latitude: '',
                    address: '',
                    coverRangeDescribe: '',
                    deviateRadius: '',
                    deliveryTime: '',
                    systemNum: '',
                    bbuNum: '',
                    rruNum: '',
                    antennaHeight: '',
                    antennaAngle: '',
                    antennaNum: '',
                    contact: '',
                    remarks: ''
                },
                dataRule: {
                    longitude: [
                        {required: true, message: '经度不能为空', trigger: 'blur'}
                    ],
                    latitude: [
                        {required: true, message: '纬度不能未空', trigger: 'blur'}
                    ]
                },
                options: [{
                    value: 'YDB',
                    label: '移动'
                }, {
                    value: 'LTB',
                    label: '联通'
                }, {
                    value: 'DXB',
                    label: '电信'
                }],
            }
        },
        computed: {},
        methods: {
            // 初始化
            init() {
                this.visible = true;
                this.$nextTick(() => {
                    this.$refs['dataForm'].resetFields()
                })
            },
            // 表单提交
            dataFormSubmit() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        this.$http({
                            url: this.$http.adornUrl('/api/wf/startProcesses'),
                            method: 'post',
                            data: this.$http.adornData(this.dataForm)
                        }).then(({data}) => {

                            if (data && data.code === 0) {
                                this.$message({
                                    message: '操作成功',
                                    type: 'success',
                                    duration: 1500,
                                    onClose: () => {
                                        this.visible = false
//                                        this.$emit('refreshDataList')
                                    }
                                })
                            } else {
                                this.$message.error(data.msg)
                            }
                        })
                    }
                })
            }
        }
    }
</script>

