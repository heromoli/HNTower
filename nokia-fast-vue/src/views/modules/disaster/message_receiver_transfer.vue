<template>
    <el-dialog
            center
            width="36%"
            :append-to-body="true"
            :lock-scroll="true"
            :modal="true"
            title="发送内容确认"
            :close-on-click-modal="false"
            :visible.sync="visible">
        <el-form :model="dataForm" ref="dataForm" @keyup.enter.native="message_send()"
                 label-width="105px">
            <el-row>
                <el-form-item label="简报类型">
                    <el-input v-if="" type="text" v-model="dataForm.msgId" disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="简报内容">
                    <el-input type="textarea" :rows="4" v-model="dataForm.msg"></el-input>
                </el-form-item>
                <el-form-item label="接收人">
                    <el-transfer
                            filterable
                            :filter-method="filterMethod"
                            filter-placeholder="请输入姓名拼音"
                            v-model="receiver"
                            :data="data2"
                            :render-content="renderContent">
                    </el-transfer>
                </el-form-item>
            </el-row>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="visible = false">关闭</el-button>
            <el-button v-if="isAuth('gzl:table3:update')" type="primary" @click="dataFormSubmit()">确定</el-button>
        </span>
    </el-dialog>
</template>

<script>
    export default {
        name: "message_receiver_transfer",
        data() {
            const generateData2 = _ => {
                const data = [];
                const cities = ['李上海', '张北京', '王广州', '刘深圳', '周南京', '吴西安', '林成都'];
                const pinyin = ['shanghai', 'beijing', 'guangzhou', 'shenzhen', 'nanjing', 'xian', 'chengdu'];
                cities.forEach((city, index) => {
                    data.push({
                        label: city,
                        key: index,
                        pinyin: pinyin[index]
                    });
                });
                return data;
            };
            return {
                visible: false,
                dataForm: {},
                data2: generateData2(),
                receiver: [],
                filterMethod(query, item) {
                    return item.pinyin.indexOf(query) > -1;
                }
            };
        },
        methods: {
            init(row) {
                // this.url = this.$http.adornUrl(`/api/wf/upload?token=${this.$cookie.get('token')}`);
                console.log(row);
                this.visible = true;
                this.dataForm.msgId = row.msgId;
                this.dataForm.msg = row.msg;
            },
            message_send() {
                this.$message({
                    message: '发送成功',
                    type: 'success'
                });
            },
            renderContent(h, option) {
                return h('span', {domProps: {title: option.label}}, option.label);
            }
        }

    }
</script>

<style scoped>
    .form_style {
        width: 500px;
    }

    .el-transfer {
        width: 500px;
    }
</style>