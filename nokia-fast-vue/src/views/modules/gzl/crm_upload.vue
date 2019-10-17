<template>
    <el-dialog
            title="上传CRM文件"
            :close-on-click-modal="false"
            @close="closeHandle"
            :visible.sync="visible">
        <!--multiple-->
        <el-upload
                drag
                :action="url"
                :before-upload="beforeUploadHandle"
                :on-success="successHandle"
                :data="{'groupId':groupId}"
                :file-list="fileList"
                style="text-align: center;">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只支持xls、xlsx 格式的文件！</div>
        </el-upload>
    </el-dialog>
</template>

<script>
    export default {
        data() {
            return {
                visible: false,
                url: '',
                num: 0,
                successNum: 0,
                groupId: '',
                fileList: []
            }
        },
        methods: {
            init(id) {
                this.url = this.$http.adornUrl(`/api/wf/crmUpload?token=${this.$cookie.get('token')}`);
                this.visible = true;
                this.groupId = id;
            },
            // 上传之前
            beforeUploadHandle(file) {
                if (file.type !== 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' && file.type !== 'application/vnd.ms-excel') {
                    this.$message.error('只支持xls、xlsx 格式的文件！');
                    return false
                }
                this.num++
            },
            // 上传成功
            successHandle(response, file, fileList) {
                this.fileList = fileList;
                this.successNum++;
                if (response && response.code === 0) {
                    this.$message({
                        message: '操作成功',
                        type: 'success',
                        duration: 1500,
                        onClose: () => {
                            this.visible = false
                        }
                    });

                } else {
                    this.$message.error(response.msg)
                }
            },
            // 弹窗关闭时
            closeHandle() {
                this.fileList = [];
                this.$emit('refreshDataList')
            }
        }
    }
</script>
