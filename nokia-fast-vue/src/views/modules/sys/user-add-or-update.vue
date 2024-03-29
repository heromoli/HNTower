<template>
    <el-dialog
            :title="!dataForm.id ? '新增' : '修改'"
            :close-on-click-modal="false"
            :visible.sync="visible">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
                 label-width="80px">
            <el-form-item label="用户名" prop="userName">
                <el-input v-model="dataForm.userName" placeholder="登录帐号"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password" :class="{ 'is-required': !dataForm.id }">
                <el-input show-password v-model="dataForm.password" type="password" minlength="8" maxlength="20"
                          placeholder="请输入8-20位英文字母、数字或者符号（除空格），且大小写字母、数字和标点符号至少包含三种"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="comfirmPassword" :class="{ 'is-required': !dataForm.id }">
                <el-input v-model="dataForm.comfirmPassword" type="password" minlength="8" maxlength="20"
                          placeholder="请输入8-20位英文字母、数字或者符号（除空格），且大小写字母、数字和标点符号至少包含三种"></el-input>
            </el-form-item>
            <el-form-item label="密码强度">
                <password-strength v-model="dataForm.password" style="padding-top: 10px;"></password-strength>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
                <el-input v-model="dataForm.name" placeholder="姓名"></el-input>
            </el-form-item>
            <el-form-item label="分公司" prop="branch">
                <el-input v-model="dataForm.branch" placeholder="分公司"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
            </el-form-item>
            <el-form-item label="手机号">
                <el-input v-model="dataForm.mobile" placeholder="手机号"></el-input>
            </el-form-item>
            <el-form-item label="角色" size="mini" prop="roleIdList">
                <el-checkbox-group v-model="dataForm.roleIdList">
                    <el-checkbox v-for="role in roleList" :key="role.roleId" :label="role.roleId">{{ role.roleName }}
                    </el-checkbox>
                </el-checkbox-group>
            </el-form-item>
            <el-form-item label="分组" size="mini" prop="groupIdList">
                <el-checkbox-group v-model="dataForm.groupIdList">
                    <el-checkbox :key="3" :label="3">需求收集
                    </el-checkbox>
                    <el-checkbox :key="4" :label="4">站址筛查
                    </el-checkbox>
                    <el-checkbox :key="5" :label="5">建设需求确认
                    </el-checkbox>
                    <el-checkbox :key="6" :label="6">需求变更
                    </el-checkbox>
                </el-checkbox-group>
            </el-form-item>
            <el-form-item label="状态" size="mini" prop="status">
                <el-radio-group v-model="dataForm.status">
                    <el-radio :label="0">禁用</el-radio>
                    <el-radio :label="1">正常</el-radio>
                </el-radio-group>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
    </el-dialog>
</template>

<script>
    import {isEmail, isMobile} from '@/utils/validate'
    import PasswordStrength from '@/components/sys/PasswordStrength'

    export default {
        data() {
            let validatePassword = (rule, value, callback) => {
                if (!this.dataForm.id && !/\S/.test(value)) {
                    callback(new Error('密码不能为空'))
                }
                else if(!!/\S/.test(value) && !/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[\W])[A-Za-z0-9\W]{8,20}$/.test(value)){
                    callback(new Error('请输入8-20位英文字母、数字或者符号（除空格），且大小写字母、数字和标点符号至少包含三种'))
                }
                else {
                    callback()
                }
            };
            let validateComfirmPassword = (rule, value, callback) => {
                if (!this.dataForm.id && !/\S/.test(value)) {
                    callback(new Error('确认密码不能为空'))
                } else if (this.dataForm.password !== value) {
                    callback(new Error('确认密码与密码输入不一致'))
                } else {
                    callback()
                }
            };
            let validatePsdReg = (rule, value, callback) => {
                if (!/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[\W])[A-Za-z0-9\W]{8,20}$/.test(value)) {
                    callback(new Error('请输入8-20位英文字母、数字或者符号（除空格），且大小写字母、数字和标点符号至少包含三种'))
                } else {
                    callback()
                }
            };
            let validateEmail = (rule, value, callback) => {
                if (!isEmail(value)) {
                    callback(new Error('邮箱格式错误'))
                } else {
                    callback()
                }
            };
            let validateMobile = (rule, value, callback) => {
                if (!isMobile(value)) {
                    callback(new Error('手机号格式错误'))
                } else {
                    callback()
                }
            };
            return {
                visible: false,
                roleList: [],
                dataForm: {
                    id: 0,
                    userName: '',
                    password: '',
                    comfirmPassword: '',
                    salt: '',
                    name: '',
                    branch: '',
                    email: '',
                    mobile: '',
                    roleIdList: [],
                    groupIdList: [],
                    status: 1
                },
                dataRule: {
                    userName: [
                        {required: true, message: '用户名不能为空', trigger: 'blur'}
                    ],
                    password: [
                        {validator: validatePassword, trigger: 'blur'},
                        // {validator: validatePsdReg, trigger: 'blur'}
                    ],
                    comfirmPassword: [
                        {validator: validateComfirmPassword, trigger: 'blur'},
                        // {validator: validatePsdReg, trigger: 'blur'}
                    ],
                    email: [
                        {required: true, message: '邮箱不能为空', trigger: 'blur'},
                        {validator: validateEmail, trigger: 'blur'}
                    ],
                    mobile: [
                        {required: true, message: '手机号不能为空', trigger: 'blur'},
                        {validator: validateMobile, trigger: 'blur'}
                    ]
                }
            }
        },
        components: {
            PasswordStrength
        },
        methods: {
            init(id) {
                this.dataForm.id = id || 0;
                this.$http({
                    url: this.$http.adornUrl('/sys/role/select'),
                    method: 'get',
                    params: this.$http.adornParams()
                }).then(({data}) => {
                    this.roleList = data && data.code === 0 ? data.list : []
                }).then(() => {
                    this.visible = true;
                    this.$nextTick(() => {
                        this.$refs['dataForm'].resetFields()
                    })
                }).then(() => {
                    if (this.dataForm.id) {
                        this.$http({
                            url: this.$http.adornUrl(`/sys/user/info/${this.dataForm.id}`),
                            method: 'get',
                            params: this.$http.adornParams()
                        }).then(({data}) => {
                            if (data && data.code === 0) {
                                this.dataForm.userName = data.user.username;
                                this.dataForm.name = data.user.name;
                                this.dataForm.branch = data.user.branch;
                                this.dataForm.salt = data.user.salt;
                                this.dataForm.email = data.user.email;
                                this.dataForm.mobile = data.user.mobile;
                                this.dataForm.roleIdList = data.user.roleIdList;
                                this.dataForm.status = data.user.status;
                                this.dataForm.groupIdList = data.user.groupIdList;
                            }
                        })
                    }
                })
            },
            // 表单提交
            dataFormSubmit() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        this.$http({
                            url: this.$http.adornUrl(`/sys/user/${!this.dataForm.id ? 'save' : 'update'}`),
                            method: 'post',
                            data: this.$http.adornData({
                                'userId': this.dataForm.id || undefined,
                                'username': this.dataForm.userName,
                                'password': this.dataForm.password,
                                'salt': this.dataForm.salt,
                                'name': this.dataForm.name,
                                'branch': this.dataForm.branch,
                                'email': this.dataForm.email,
                                'mobile': this.dataForm.mobile,
                                'status': this.dataForm.status,
                                'roleIdList': this.dataForm.roleIdList,
                                'groupIdList': this.dataForm.groupIdList
                            })
                        }).then(({data}) => {
                            if (data && data.code === 0) {
                                this.$message({
                                    message: '操作成功',
                                    type: 'success',
                                    duration: 1500,
                                    onClose: () => {
                                        this.visible = false;
                                        this.$emit('refreshDataList')
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
