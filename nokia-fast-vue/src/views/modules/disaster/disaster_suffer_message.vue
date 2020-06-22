<template>
    <div class="mod-log">
        <el-form :inline="true" :model="queryParam" @keyup.enter.native="getDataList()">
            <el-form-item>
                <el-form-item label="简报时间">
                    <el-date-picker size="mini"
                                    v-model="queryParam.sdate"
                                    type="date" style="width: 100%"
                                    placeholder="选择日期"
                                    value-format="yyyy-MM-dd">
                    </el-date-picker>
                </el-form-item>
            </el-form-item>
            <el-form-item prop="msg_id" label="简报类型">
                <el-select size="mini" v-model="queryParam.msg_id">
                    <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="简报内容">
                <el-input size="mini" v-model="queryParam.msg" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="mini" type="success" @click="getDataList()">查询</el-button>
                <!--<el-button type="success" size="mini" @click="uploadHandle()">导入</el-button>-->
                <!--<el-button type="primary" size="mini" @click="exportHandle()">导出</el-button>-->
            </el-form-item>
        </el-form>
        <el-table
                :data="dataList"
                border
                v-loading="dataListLoading"
                stripe
                style="height: auto; width: auto">
            <el-table-column
                    prop="id"
                    fixed="left"
                    header-align="center"
                    align="center"
                    label="序号">
            </el-table-column>
            <el-table-column
                    prop="sdate"
                    fixed="left"
                    header-align="center"
                    align="center"
                    label="简报时间">
            </el-table-column>
            <el-table-column
                    prop="msgId"
                    header-align="center"
                    align="center"
                    label="简报类型">
                <template scope="scope">
                    <p v-if="scope.row.msgId=='0'">全省概况</p>
                    <p v-if="scope.row.msgId=='1'">受灾简报</p>
                    <p v-if="scope.row.msgId=='2'">抢修汇报</p>
                </template>
            </el-table-column>
            <el-table-column
                    prop="msg"
                    header-align="center"
                    align="left"
                    width="750"
                    label="内容">
                <!--<template slot-scope="{row,$index}">-->
                    <!--<el-input size="mini" placeholder="请输入内容" style="width: 100%"-->
                              <!--v-model="row.msg"></el-input>-->
                <!--</template>-->
            </el-table-column>
            <el-table-column
                    prop="sendStatus"
                    header-align="center"
                    align="center"
                    :show-overflow-tooltip="true"
                    label="是否发送">
                <template scope="scope">
                    <p v-if="scope.row.sendStatus=='0'">是</p>
                    <p v-if="scope.row.sendStatus=='1'">否</p>
                </template>
            </el-table-column>
            <el-table-column
                    prop="sendDate"
                    header-align="center"
                    align="center"
                    :show-overflow-tooltip="true"
                    label="发送时间">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    header-align="center"
                    label="操作"
                    align="center"
                    width="200">
                <template size="mini" slot-scope="{row,$index}">
                    <!--<el-button type="primary" size="small" @click.native="showDetail(scope.row)">编辑</el-button>-->
                    <el-button type="danger" size="small" @click="transferHandle(row)">发送</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="sizeChangeHandle"
                @current-change="currentChangeHandle"
                :current-page="pageIndex"
                :page-sizes="[10, 20, 50, 100]"
                :page-size="pageSize"
                :total="totalPage"
                layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>
        <!--<show-detail v-if="showDetailVisible" ref="showDetail"></show-detail>-->
        <transfer v-if="transferVisible" ref="transfer"></transfer>
    </div>
</template>

<script>
    import transfer from './message_receiver_transfer'

    export default {
        data() {
            return {
                queryParam: {
                    sdate: '',
                    msg_id: '',
                    msg: ''
                },
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
                showDetailVisible: false,
                transferVisible: false,
                options: [{
                    value: '0',
                    label: '全省概况'
                }, {
                    value: '1',
                    label: '受灾简报'
                }, {
                    value: '2',
                    label: '抢修汇报'
                }],
            }
        },
        created() {
            this.getDataList()
        },
        components: {transfer},
        methods: {
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/disaster/queryDisasterSufferMessage'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': this.pageSize,
                        'queryParam': this.queryParam
                    })
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataList = data.page.list;
                        this.totalPage = data.page.totalCount;
                    } else {
                        this.dataList = [];
                        this.totalPage = 0
                    }
                    this.dataListLoading = false
                })
            },
            // 每页数
            sizeChangeHandle(val) {
                this.pageSize = val;
                this.pageIndex = 1;
                this.getDataList()
            },
            // 当前页
            currentChangeHandle(val) {
                this.pageIndex = val;
                this.getDataList()
            },
            showDetail(row) {
                this.showDetailVisible = true;
                this.$nextTick(() => {
                    this.$refs.showDetail.init(row);
                })
            },
            //选择接收人
            transferHandle(row) {
                this.transferVisible = true;
                this.$nextTick(() => {
                    this.$refs.transfer.init(row)
                })
            }
            // exportHandle() {
            //     window.location.href = this.$http.adornUrl(`/api/wf/supervisionExport?branchCompany=${this.queryParam.branchCompany}&stationName=${this.queryParam.stationName}&address=${this.queryParam.address}&demandNum=${this.queryParam.demandNum}&specialStation=${this.queryParam.specialStation}&token=${this.$cookie.get('token')}`)
            //
            // }
        }
    }
</script>
