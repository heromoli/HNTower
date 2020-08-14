<template>
    <div class="mod-log">
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
                <el-input v-model="dataForm.key" placeholder="站址名称" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="getDataList()">查询</el-button>
                <el-button type="success" @click="managementUploadHandle()">导入站址管理数据</el-button>
                <el-button type="success" @click="infoUploadHandle()">导入站址信息数据</el-button>
                <el-button type="success" @click="towerUploadHandle()">导入铁塔信息数据</el-button>
            </el-form-item>
        </el-form>
        <el-table
                :data="dataList"
                border
                v-loading="dataListLoading"
                style="width: 100%">
            <el-table-column
                    prop="id"
                    header-align="center"
                    align="center"
                    width="80"
                    label="ID">
            </el-table-column>
            <el-table-column
                    prop="stationName"
                    header-align="center"
                    align="center"
                    label="站点名称">
            </el-table-column>
            <el-table-column
                    prop="city"
                    header-align="center"
                    align="center"
                    label="区县">
            </el-table-column>
            <el-table-column
                    prop="address"
                    header-align="center"
                    align="center"
                    width="250"
                    :show-overflow-tooltip="true"
                    label="地址">
            </el-table-column>
            <el-table-column
                    prop="longitude"
                    header-align="center"
                    align="center"
                    width="100"
                    :show-overflow-tooltip="true"
                    label="经度">
            </el-table-column>
            <el-table-column
                    prop="latitude"
                    header-align="center"
                    align="center"
                    width="100"
                    label="纬度">
            </el-table-column>
            <el-table-column
                    prop="ifOperatorShare"
                    header-align="center"
                    align="center"
                    width="150"
                    label="共享情况">
            </el-table-column>
            <el-table-column
                    prop="备注"
                    header-align="center"
                    align="center"
                    width="180"
                    label="备注">
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
        <management_upload v-if="managementUploadVisible" ref="management_upload" @refreshDataList="getDataList"></management_upload>
        <info_upload v-if="infoUploadVisible" ref="info_upload" @refreshDataList="getDataList"></info_upload>
        <tower_upload v-if="towerUploadVisible" ref="tower_upload" @refreshDataList="getDataList"></tower_upload>
    </div>
</template>

<script>
    import info_upload from './station_address_info_upload';
    import management_upload from './station_address_management_upload';
    import tower_upload from './station_tower_info_upload';

    export default {
        data() {
            return {
                dataForm: {
                    key: ''
                },
                dataList: [],
                infoUploadVisible: false,
                managementUploadVisible: false,
                towerUploadVisible: false,
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
                selectionDataList: []
            }
        },
        created() {
            this.getDataList()
        },
        components: {
            info_upload,
            management_upload,
            tower_upload
        },
        methods: {
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                this.$http({
                    url: this.$http.adornUrl('/api/zhzygl/stationAddressManagementList'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'page': this.pageIndex,
                        'limit': this.pageSize,
                        'key': this.dataForm.key
                    })
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataList = data.page.list;
                        this.totalPage = data.page.totalCount
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
            // 上传文件
            managementUploadHandle() {
                this.managementUploadVisible = true;
                this.$nextTick(() => {
                    this.$refs.management_upload.init()
                })
            },
            infoUploadHandle() {
                this.infoUploadVisible = true;
                this.$nextTick(() => {
                    this.$refs.info_upload.init()
                })
            },
            towerUploadHandle() {
                this.towerUploadVisible = true;
                this.$nextTick(() => {
                    this.$refs.tower_upload.init()
                })
            }
        }
    }
</script>
