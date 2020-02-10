<template>
    <el-dialog
            title="工程权限分配"
            :visible.sync="visible"
            :append-to-body="true">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
                 label-width="80px">
            <el-form-item label="用户" prop="userId">
                <el-input v-if="false" type="company" v-model="dataForm.userId"></el-input>
                <!--{{dataForm.userName}}-->
                <!--<el-popover-->
                <!--placement="right"-->
                <!--width="230"-->
                <!--trigger="click"-->
                <!--v-model="popoverModel">-->
                <!--<el-table :data="userData" @row-click="rowClick">-->
                <!--<el-table-column width="80" property="userId" label="ID"></el-table-column>-->
                <!--<el-table-column width="120" property="username" label="姓名"></el-table-column>-->
                <!--</el-table>-->
                <!--<el-button slot="reference">选择</el-button>-->
                <!--</el-popover>-->

                <el-select v-model="dataForm.userName"
                           filterable
                           remote
                           reserve-keyword
                           placeholder="请输入内容"
                           :remote-method="querySearchAsync"
                           no-data-text=""
                           default-first-option
                           @change="setUserId">
                    <el-option
                            v-for="item in options.userList"
                            :key="item.userId"
                            :label="item.username"
                            :value="item.userId">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="分公司" prop="company">
                <el-select v-model="dataForm.company" placeholder="请选择">
                    <el-option
                            v-for="item in options.company"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
                <!--<el-input type="company" v-model="dataForm.company"></el-input>-->
            </el-form-item>
            <el-form-item label="运营商" prop="operator">
                <el-select v-model="dataForm.operator" placeholder="请选择">
                    <el-option
                            v-for="item in options.operator"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
                <!--<el-input type="operator" v-model="dataForm.operator"></el-input>-->
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
        data() {
            return {
                visible: false,
                initUser: {//初始默认用户
                    userId: '',
                    username: '',
                },
                dataForm: {
                    userId: '',
                    userName: '',
                    company: '',
                    operator: ''
                },
                userData: [],
                dataRule: {
                    userId: [
                        {required: true, message: '用户不能为空', trigger: 'blur'},
                    ],
                    company: [
                        {required: true, message: '分公司不能为空', trigger: 'blur'}
                    ],
                    operator: [
                        {required: true, message: '运营商不能未空', trigger: 'blur'}
                    ],
                },
                popoverModel: false,
                options: {
                    company: [
                        {label: '全部', value: '全部'},
                        {label: '海口', value: '海口'},
                        {label: '三亚', value: '三亚'},
                        {label: '琼海', value: '琼海'},
                        {label: '儋州', value: '儋州'},
                    ],
                    operator: [
                        {label: '全部', value: '全部'},
                        {label: '移动', value: '移动'},
                        {label: '联通', value: '联通'},
                        {label: '电信', value: '电信'},
                    ],
                    userList: []
                },
                loading: false,
            }
        },
        computed: {},
        methods: {
            // 初始化
            init(row) {
                this.visible = true;
                // this.$nextTick(() => {
                //清空提示
                // this.$refs['dataForm'].resetFields();
                // this.dataForm.userId = row.userId;
                // this.dataForm.userName = row.userName;
                // this.dataForm.company = row.company;
                // this.dataForm.operator = row.operator;

                // this.initUser.userId = row.userId;
                // this.initUser.username = row.userName;
                // this.getUserData();
                // });
            },
            // 表单提交
            dataFormSubmit() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        this.$http({
                            url: this.$http.adornUrl('/sys/project/config/save'),
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
                                        this.$emit('refreshDataList')
                                    }
                                })
                            } else {
                                this.$message.error(data.msg)
                            }
                        })
                    }
                })
            },
            getUserData() {
                this.userData = [];
                if (this.initUser.username)
                    this.userData.push(this.initUser);
                this.$http({
                    url: this.$http.adornUrl('/sys/project/config/userList'),
                    method: 'get',
                    params: this.$http.adornParams({})
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.userData = this.userData.concat(data.userData);
                    }
                })
            },
            querySearchAsync(queryString) {
                this.$http({
                    url: this.$http.adornUrl('/sys/project/config/queryUser'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'userName': queryString
                    })
                }).then(({data}) => {
                    if (data.userList != null && data && data.code === 0) {
                        console.log(data.userList);
                        this.options.userList = data.userList;
                    } else {
                        this.options = [];
                    }
                });
            },
            rowClick(row, column, event) {
                this.dataForm.userId = row.userId;
                this.dataForm.userName = row.username;
                this.popoverModel = false;
            },
            setUserId(value) {
                console.log("UserId:" + value);
                this.dataForm.userId = value;
            }
        }
    }
</script>

