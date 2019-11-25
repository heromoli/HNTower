<template>
    <el-dialog
            title="监理表详情"
            :visible.sync="visible"
            :fullscreen="true"
            :append-to-body="true">

        <el-container>
            <el-header>监理表详情</el-header>
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
                            <el-form-item label="需求来源" prop="demandSource">
                                <el-input type="text" v-model="dataForm.demandSource" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="分公司" prop="branchCompany">
                                <el-input type="text" v-model="dataForm.branchCompany"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="市县" prop="region">
                                <el-input type="text" v-model="dataForm.county"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="区域" prop="scene">
                                <el-input type="text" v-model="dataForm.region"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="站点名称" prop="stationName">
                                <el-input type="text" v-model="dataForm.stationName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="运营商站点名" prop="operatorStationName">
                                <el-input type="text" v-model="dataForm.operatorStationName"></el-input>
                            </el-form-item>
                        </el-col>

                        <el-col :span="6">
                            <el-form-item label="站址编号" prop="stationNum">
                                <el-input type="text" v-model="dataForm.stationNum"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="机房编号" prop="roomNum">
                                <el-input type="text" v-model="dataForm.roomNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="订单编号" prop="orderNum">
                                <el-input type="text" v-model="dataForm.orderNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="需求提出时间" prop="demandProposeTime">
                                <el-input type="text" v-model="dataForm.demandProposeTime"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="交付时间" prop="deliveryTime">
                                <el-input type="text" v-model="dataForm.deliveryTime"></el-input>
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
                        <el-col :span="6">
                            <el-form-item label="详细地址" prop="address">
                                <el-input type="text" v-model="dataForm.address"></el-input>
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
                            <el-form-item label="建设方式" prop="buildType">
                                <el-input type="text" v-model="dataForm.buildType"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="是否共享" prop="ifShare">
                                <el-input type="text" v-model="dataForm.ifShare"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="楼面/地面" prop="buildingOrGround">
                                <el-input type="text" v-model="dataForm.buildingOrGround"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="移动建设模式" prop="mobileBuildType">
                                <el-input type="text" v-model="dataForm.mobileBuildType"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="征址完成时间" prop="locationFinishTime">
                                <el-input type="text" v-model="dataForm.locationFinishTime"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="进场时间" prop="intoTime">
                                <el-input type="text" v-model="dataForm.intoTime"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="土建完成时间" prop="buildFinishTime">
                                <el-input type="text" v-model="dataForm.buildFinishTime"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="铁塔完成时间" prop="towerFinishTime">
                                <el-input type="text" v-model="dataForm.towerFinishTime"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="引电完成时间" prop="electricFinishTime">
                                <el-input type="text" v-model="dataForm.electricFinishTime"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="配套完成时间" prop="matchingFinishTime">
                                <el-input type="text" v-model="dataForm.matchingFinishTime"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="总体完工时间" prop="totalFinishTime">
                                <el-input type="text" v-model="dataForm.totalFinishTime"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="内验完成时间" prop="checkFinishTime">
                                <el-input type="text" v-model="dataForm.checkFinishTime"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="工程状态分类" prop="projectState">
                                <el-input type="text" v-model="dataForm.projectState"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="工程状态描述" prop="projectStateDescribe">
                                <el-input type="text" v-model="dataForm.projectStateDescribe"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="任务年份" prop="projectYear">
                                <el-input type="text" v-model="dataForm.projectYear"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="是否政府协调" prop="ifGovernment">
                                <el-input type="text" v-model="dataForm.ifGovernment"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </el-main>

        </el-container>
        <span slot="footer" class="dialog-footer">
            <el-button @click="visible = false">关闭</el-button>
            <!--<el-button type="primary" @click="dataFormSubmit()">确定</el-button>-->
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
                this.dataFormFill(row.id);
            },
            //表单填充
            dataFormFill(id) {
                this.$http({
                    url: this.$http.adornUrl('/api/wf/fillSupervisorForm'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'id': id,
                    })
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataForm = data.returnData;
                    } else {
                        this.$message.error(data.msg)
                    }
                });
                this.dataForm = Object.assign({});
            }

        }
    }
</script>

