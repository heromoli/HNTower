<template>
    <div class="mod-log">
        <el-form :inline="true" @keyup.enter.native="getTyphoonList()">
            <el-form-item label="输入年份">
                <el-input size="mini" v-model="year" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="mini" type="success" @click="getTyphoonList()">查询</el-button>
            </el-form-item>
        </el-form>
        <el-table
                :data="typhoonList"
                border
                v-loading="dataListLoading"
                stripe
                style="height: auto; width: auto">
            <el-table-column
                    type="index"
                    fixed="left"
                    header-align="center"
                    align="center"
                    label="序号">
            </el-table-column>
            <el-table-column
                    prop="tfid"
                    fixed="left"
                    header-align="center"
                    align="center"
                    label="编号">
                <!--<template slot-scope="scope">-->
                <!--{{scope.row[0]}}-->
                <!--</template>-->
            </el-table-column>
            <el-table-column
                    prop="enname"
                    header-align="center"
                    align="center"
                    label="英文名称">
                <!--<template slot-scope="scope">-->
                <!--{{scope.row[1]}}-->
                <!--</template>-->
            </el-table-column>
            <el-table-column
                    prop="name"
                    header-align="center"
                    align="center"
                    label="中文名称">
                <!--<template slot-scope="scope">-->
                <!--{{scope.row[2]}}-->
                <!--</template>-->
            </el-table-column>
            <el-table-column
                    prop="starttime"
                    header-align="center"
                    align="center"
                    label="开始时间">
                <!--<template slot-scope="scope">-->
                <!--<p v-if="scope.row[3]!=null">20{{scope.row[3]}}</p>-->
                <!--</template>-->
            </el-table-column>
            <el-table-column
                    prop="endtime"
                    header-align="center"
                    align="center"
                    :show-overflow-tooltip="true"
                    label="结束时间">
                <!--<template slot-scope="scope">-->
                <!--{{scope.row[6]}}-->
                <!--</template>-->
            </el-table-column>
            <el-table-column
                    prop="isactive"
                    header-align="center"
                    align="center"
                    :show-overflow-tooltip="true"
                    label="是否停编">
                <template scope="scope">
                    <p v-if="scope.row.isactive === '0'">是</p>
                    <p v-if="scope.row.isactive !== '0'">否</p>
                </template>
            </el-table-column>
            <el-table-column
                    prop="warnlevel"
                    header-align="center"
                    align="center"
                    label="警报级别">
                <!--<template slot-scope="scope">-->
                <!--{{scope.row[2]}}-->
                <!--</template>-->
            </el-table-column>
            <el-table-column
                    fixed="right"
                    header-align="center"
                    label="操作"
                    align="center"
                    width="200">
                <template size="mini" slot-scope="{row,$index}">
                    <el-button type="primary" size="small" @click="getTyphoonDetail(row)">下载详细信息</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>

    export default {
        data() {
            return {
                queryParam: {},
                dataList: [],
                year: '2020',
                dataListLoading: false,
                showDetailVisible: false,
                typhoonList: [],
            }
        },
        created() {
            // this.getDefaultList();
            this.getTyphoonList();
        },
        methods: {
            // 获取数据列表
            getDefaultList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/weather/typhoonActivity'),
                    method: 'get',
                }).then(({data}) => {
                    if (data.json != null && data.code === 0) {
                        this.typhoonList = data.json.typhoonList;
                    }
                    this.dataListLoading = false
                })
            },
            getTyphoonList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/weather/typhoonList'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'year': this.year
                    })
                }).then(({data}) => {
                    if (data.json != null && data.code === 0) {
                        this.typhoonList = data.json;
                    }
                    this.dataListLoading = false
                });
            },
            getTyphoonDetail(row) {
                // this.dataListLoading = true;
                // this.$http({
                //     url: this.$http.adornUrl('/api/weather/typhoonInfo'),
                //     method: 'get',
                //     params: this.$http.adornParams({
                //         'tfid': row.tfid
                //     })
                // }).then(({data}) => {
                //     if (data && data.code === 0) {
                //         console.log(data);
                //     } else {
                //
                //     }
                //     this.dataListLoading = false
                // });
                window.location.href = this.$http.adornUrl(`/api/weather/typhoonInfo?tfid=${row.tfid}&token=${this.$cookie.get('token')}`)
            },
            // exportHandle() {
            //     window.location.href = this.$http.adornUrl(`/api/wf/supervisionExport?branchCompany=${this.queryParam.branchCompany}&stationName=${this.queryParam.stationName}&address=${this.queryParam.address}&demandNum=${this.queryParam.demandNum}&specialStation=${this.queryParam.specialStation}&token=${this.$cookie.get('token')}`)
            //
            // }
        }
    }
</script>
