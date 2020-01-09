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
                        <el-select v-model="dataForm.operatorName" placeholder="请选择" style="width: 100%">
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
                        <!--<el-input type="text" v-model="dataForm.branchCompany"> :onchange="countyOnBranchChange()"</el-input>-->
                        <el-select v-model="dataForm.branchCompany" @change="countyOnBranchChange"  placeholder="请选择" style="width: 100%">
                            <el-option
                                    v-for="item in branchOptions"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="地市" prop="county">
                        <!--<el-input type="text" v-model="dataForm.county"></el-input>-->
                        <el-select v-model="dataForm.county"  placeholder="请选择" style="width: 100%">
                            <el-option
                                    v-for="item in countyOptions"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="区县" prop="region">
                        <!--<el-input type="text" v-model="dataForm.region"></el-input>-->
                        <el-select v-model="dataForm.region"  placeholder="请选择" style="width: 100%">
                            <el-option
                                    v-for="item in regionOptions"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
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
                        <!--<el-input type="text" v-model="dataForm.scene"></el-input> sceneOptions-->
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
                                placeholder="选择日期"
                                value-format="yyyy-MM-dd">
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
                branchOptions: [{
                    value: '海口',
                    label: '海口'
                }, {
                    value: '三亚',
                    label: '三亚'
                }, {
                    value: '琼海',
                    label: '琼海'
                }, {
                    value: '儋州',
                    label: '儋州'
                }],
                countyOptions: [],
                regionOptions: [],
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
                }]
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
            },
            countyOnBranchChange() {
                this.countyOptions = [];
                this.regionOptions = [];
                if (this.dataForm.branchCompany === '海口') {
                    this.countyOptions.push({value: '海口', label: '海口'});

                    this.regionOptions.push({value: '龙华区', label: '龙华区'});
                    this.regionOptions.push({value: '美兰区', label: '美兰区'});
                    this.regionOptions.push({value: '琼山区', label: '琼山区'});
                    this.regionOptions.push({value: '秀英区', label: '秀英区'});
                } else if (this.dataForm.branchCompany === '三亚') {
                    this.countyOptions.push({value: '三亚', label: '三亚'});
                    this.countyOptions.push({value: '陵水', label: '陵水'});
                    this.countyOptions.push({value: '保亭', label: '保亭'});
                    this.countyOptions.push({value: '乐东', label: '乐东'});
                    this.countyOptions.push({value: '五指山', label: '五指山'});

                    this.regionOptions.push({value: '天涯区', label: '天涯区'});
                    this.regionOptions.push({value: '海棠区', label: '海棠区'});
                    this.regionOptions.push({value: '崖城区', label: '崖城区'});
                    this.regionOptions.push({value: '吉阳区', label: '吉阳区'});
                    this.regionOptions.push({value: '陵水', label: '陵水'});
                    this.regionOptions.push({value: '保亭', label: '保亭'});
                    this.regionOptions.push({value: '乐东', label: '乐东'});
                    this.regionOptions.push({value: '五指山', label: '五指山'});
                } else if (this.dataForm.branchCompany === '琼海') {
                    this.countyOptions.push({value: '琼海', label: '琼海'});
                    this.countyOptions.push({value: '万宁', label: '万宁'});
                    this.countyOptions.push({value: '文昌', label: '文昌'});
                    this.countyOptions.push({value: '定安', label: '定安'});
                    this.countyOptions.push({value: '屯昌', label: '屯昌'});
                    this.countyOptions.push({value: '琼中', label: '琼中'});

                    this.regionOptions.push({value: '琼海', label: '琼海'});
                    this.regionOptions.push({value: '万宁', label: '万宁'});
                    this.regionOptions.push({value: '文昌', label: '文昌'});
                    this.regionOptions.push({value: '定安', label: '定安'});
                    this.regionOptions.push({value: '屯昌', label: '屯昌'});
                    this.regionOptions.push({value: '琼中', label: '琼中'});
                } else if (this.dataForm.branchCompany === '儋州') {
                    this.countyOptions.push({value: '儋州', label: '儋州'});
                    this.countyOptions.push({value: '澄迈', label: '澄迈'});
                    this.countyOptions.push({value: '临高', label: '临高'});
                    this.countyOptions.push({value: '昌江', label: '昌江'});
                    this.countyOptions.push({value: '白沙', label: '白沙'});
                    this.countyOptions.push({value: '东方', label: '东方'});

                    this.regionOptions.push({value: '儋州', label: '儋州'});
                    this.regionOptions.push({value: '澄迈', label: '澄迈'});
                    this.regionOptions.push({value: '临高', label: '临高'});
                    this.regionOptions.push({value: '昌江', label: '昌江'});
                    this.regionOptions.push({value: '白沙', label: '白沙'});
                    this.regionOptions.push({value: '东方', label: '东方'});
                }
            }
        }
    }
</script>

