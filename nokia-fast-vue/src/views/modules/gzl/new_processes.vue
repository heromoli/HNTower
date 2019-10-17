<template>
    <el-dialog
            title="新建工作流"
            :visible.sync="visible"
            :append-to-body="true">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
            <el-form-item label="分公司" prop="company">
                <el-input type="company" v-model="dataForm.company"></el-input>
            </el-form-item>
            <el-form-item label="运营商" prop="operator">
                <el-input type="operator" v-model="dataForm.operator"></el-input>
            </el-form-item>
            <el-form-item label="需求编码" prop="xuqiubianma">
                <el-input type="xuqiubianma" v-model="dataForm.xuqiubianma"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
    </el-dialog>
</template>

<script>
    export default {
        data () {
            return {
                visible: false,
                dataForm: {
                    company: '海口',
                    operator: '移动',
                    xuqiubianma: 'A000001'
                },
                dataRule: {
                    company: [
                        { required: true, message: '分公司不能为空', trigger: 'blur' }
                    ],
                    operator: [
                        { required: true, message: '运营商不能未空', trigger: 'blur' }
                    ],
                    xuqiubianma: [
                        { required: true, message: '需求编码不能为空', trigger: 'blur' },
                    ]
                }
            }
        },
        computed: {

        },
        methods: {
            // 初始化
            init () {
                this.visible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].resetFields()
                })
            },
            // 表单提交
            dataFormSubmit () {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        this.$http({
                            url: this.$http.adornUrl('/api/wf/startProcesses'),
                            method: 'post',
                            data: this.$http.adornData({
                                'actProcessDefinitionId': 'tt_gcb_xxsp',
                                'company': this.dataForm.company,
                                'operator': this.dataForm.operator,
                                'xuqiubianma': this.dataForm.xuqiubianma
                            })
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

