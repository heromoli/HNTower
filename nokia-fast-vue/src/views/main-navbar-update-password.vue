<template>
    <el-dialog
            title="修改密码"
            :visible.sync="visible"
            :append-to-body="true">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
                 label-width="80px">
            <el-form-item label="账号">
                <span>{{ userName }}</span>
            </el-form-item>
            <el-form-item label="原密码" prop="password">
                <el-input type="password" v-model="dataForm.password"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="newPassword">
                <el-input show-password type="password" v-model="dataForm.newPassword"
                          minlength="8" maxlength="20"
                          placeholder="请输入8-20位英文字母、数字或者符号（除空格），且大小写字母、数字和标点符号至少包含三种"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="confirmPassword">
                <el-input type="password" v-model="dataForm.confirmPassword"
                          minlength="8" maxlength="20"
                          placeholder="请输入8-20位英文字母、数字或者符号（除空格），且大小写字母、数字和标点符号至少包含三种"></el-input>
            </el-form-item>
            <el-form-item label="密码强度">
                <password-strength v-model="dataForm.newPassword" style="padding-top: 10px;"></password-strength>
            </el-form-item>
            <el-input type="text" v-model="mode" hidden="hidden"></el-input>
        </el-form>
        <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
    </el-dialog>
</template>

<script>
    import PasswordStrength from '@/components/sys/PasswordStrength'
    import {clearLoginInfo} from '@/utils'

    export default {
        data() {
            let validateConfirmPassword = (rule, value, callback) => {
                if (this.dataForm.newPassword !== value) {
                    callback(new Error('确认密码与新密码不一致'))
                } else {
                    callback()
                }
            };
            let validatePsdReg = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请输入密码'))
                }
                if (!/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[\W])[A-Za-z0-9\W]{8,20}$/.test(value)) {
                    callback(new Error('请输入8-20位英文字母、数字或者符号（除空格），且大小写字母、数字和标点符号至少包含三种'))
                } else {
                    callback()
                }
            };
            return {
                visible: false,
                mode: 0,
                dataForm: {
                    password: '',
                    newPassword: '',
                    confirmPassword: ''
                },
                dataRule: {
                    password: [
                        {required: true, message: '原密码不能为空', trigger: 'blur'}
                    ],
                    newPassword: [
                        {required: true, message: '新密码不能为空', trigger: 'blur'},
                        {validator: validatePsdReg, trigger: 'blur'}

                    ],
                    confirmPassword: [
                        {required: true, message: '确认密码不能为空', trigger: 'blur'},
                        {validator: validateConfirmPassword, trigger: 'blur'},
                        {validator: validatePsdReg, trigger: 'blur'}
                    ]
                }
            }
        },
        components: {
            PasswordStrength
        },
        computed: {
            userName: {
                get() {
                    return this.$store.state.user.name
                }
            },
            mainTabs: {
                get() {
                    return this.$store.state.common.mainTabs
                },
                set(val) {
                    this.$store.commit('common/updateMainTabs', val)
                }
            }
        },
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
                            url: this.$http.adornUrl('/sys/user/password'),
                            method: 'post',
                            data: this.$http.adornData({
                                'password': this.dataForm.password,
                                'newPassword': this.dataForm.newPassword
                            })
                        }).then(({data}) => {
                            if (data && data.code === 0) {
                                this.$message({
                                    message: '操作成功',
                                    type: 'success',
                                    duration: 1500,
                                    onClose: () => {
                                        this.visible = false
                                        this.$nextTick(() => {
                                            this.mainTabs = []
                                            clearLoginInfo()
                                            this.$router.replace({name: 'login'})
                                        })
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

<style scope>
    .el-icon-check:before {
        content: "#fe6da";
    }
</style>
