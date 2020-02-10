<template>
    <el-dialog
            title="工作流程详情"
            :visible.sync="visible"
            :fullscreen="true"
            :append-to-body="true">
        <el-image :src="processImgSrc"></el-image>
        <el-timeline>
            <el-timeline-item v-for="hi in historyList" :timestamp="hi.startTime" placement="top">
                <el-card>
                    <h4>{{hi.comment}}</h4>
                    <p>{{hi.assignee}} {{hi.endTime}}</p>
                </el-card>
            </el-timeline-item>
        </el-timeline>

        <el-container>
            <el-header>需求单详情</el-header>
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
                            <el-form-item label="电信企业" prop="operatorName">
                                <el-input type="text" v-model="dataForm.operatorName" :disabled="true"></el-input>
                                <!--<el-select v-model="dataForm.operatorName"  placeholder="请选择" style="width: 100%">-->
                                    <!--<el-option-->
                                            <!--v-for="item in operatorOptions"-->
                                            <!--:key="item.value"-->
                                            <!--:label="item.label"-->
                                            <!--:value="item.value">-->
                                    <!--</el-option>-->
                                <!--</el-select>-->
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="分公司" prop="branchCompany">
                                <el-input type="text" v-model="dataForm.branchCompany" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="地市" prop="county">
                                <el-input type="text" v-model="dataForm.county" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>

                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="区县" prop="region">
                                <el-input type="text" v-model="dataForm.region" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="站点名称" prop="stationName">
                                <el-input type="text" v-model="dataForm.stationName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="场景划分" prop="scene">
                                <el-select v-model="dataForm.scene"  placeholder="请选择" style="width: 100%">
                                    <el-option
                                            v-for="item in sceneOptions"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="站点级别" prop="stationLevel">
                                <el-input type="text" v-model="dataForm.stationLevel"></el-input>
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
                        <el-col :span="12">
                            <el-form-item label="详细地址" prop="address">
                                <el-input type="text" v-model="dataForm.address"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="范围描述" prop="coverRangeDescribe">
                                <el-input type="text" v-model="dataForm.coverRangeDescribe"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="偏离半径" prop="deviateRadius">
                                <el-input type="text" v-model="dataForm.deviateRadius"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="交付时间" prop="deliveryTime">
                                <el-date-picker
                                        v-model="dataForm.deliveryTime"
                                        type="date" style="width: 100%"
                                        placeholder="选择日期"
                                        value-format="yyyy-MM-dd">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>

                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="系统数" prop="systemNum">
                                <el-input type="text" v-model="dataForm.systemNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="BBU数量" prop="bbuNum">
                                <el-input type="text" v-model="dataForm.bbuNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="RRU数量" prop="rruNum">
                                <el-input type="text" v-model="dataForm.rruNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="天线挂高范围" prop="antennaHeight">
                                <el-input type="text" v-model="dataForm.antennaHeight"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="天线方向角" prop="antennaAngle">
                                <el-input type="text" v-model="dataForm.antennaAngle"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="天线个数" prop="antennaNum">
                                <el-input type="text" v-model="dataForm.antennaNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="联系人" prop="contact">
                                <el-input type="text" v-model="dataForm.contact"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="建设方式" prop="contact">
                                <!--<el-input type="text" v-model="dataForm.buildType"></el-input> buildTypeOptions-->
                                <el-select v-model="dataForm.buildType" placeholder="请选择" style="width: 100%">
                                    <el-option
                                            v-for="item in buildTypeOptions"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="能否形成订单" prop="ifCanOrder">
                                <el-select v-model="dataForm.ifCanOrder" placeholder="请选择" style="width: 100%">
                                    <el-option
                                            v-for="item in shareOptions"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="18">
                            <el-form-item label="不能形成订单原因" prop="cantOrderReason">
                                <el-input type="text" v-model="dataForm.cantOrderReason"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-collapse>
                        <el-collapse-item title="候选站点填写" name="1">
                            <div class="el-main" id="showOrHide">
                                <el-row>
                                    <el-col :span="18">
                                        <el-form-item><span>候补站点1</span></el-form-item>

                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="站点查询" prop="queryStation1">
                                            <el-select v-model="queryStation1"
                                                       filterable
                                                       remote
                                                       reserve-keyword
                                                       placeholder="请输入站名"
                                                       :remote-method="remoteData1"
                                                       :loading="loading"
                                                       no-data-text=""
                                                       default-first-option
                                                       @change="setHx1Value" style="width: 100%">
                                                <el-option
                                                        v-for="item in options1"
                                                        :key="item.id"
                                                        :label="item.stationName"
                                                        :value="item">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="站点名称" prop="hx1StationName">
                                            <el-input type="text" v-model="dataForm.hx1StationName"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="站点编号" prop="hx1StationNum">
                                            <el-input type="text" v-model="dataForm.hx1StationNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="经度" prop="hx1Longitude">
                                            <el-input type="text" v-model="dataForm.hx1Longitude"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="纬度" prop="hx1Latitude">
                                            <el-input type="text" v-model="dataForm.hx1Latitude"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="详细地址" prop="hx1Address">
                                            <el-input type="text" v-model="dataForm.hx1Address"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="是否共享已有站址" prop="hx1IfShare">
                                            <el-input type="text" v-model="dataForm.hx1IfShare"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共享存量站址产权归属方" prop="hx1ShareStationBelong">
                                            <el-input type="text" v-model="dataForm.hx1ShareStationBelong"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="原有运营商数(户)" prop="hx1OldOperatorNum">
                                            <el-input type="text" v-model="dataForm.hx1OldOperatorNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="原有具体运营商" prop="hx1OldOperator">
                                            <el-input type="text" v-model="dataForm.hx1OldOperator"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="本次新增运营商数(户)" prop="hx1NewOperatorNum">
                                            <el-input type="text" v-model="dataForm.hx1NewOperatorNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="新增具体运营商" prop="hx1NewOperator">
                                            <el-input type="text" v-model="dataForm.hx1NewOperator"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="天线挂高范围" prop="hx1AntennaHeight">
                                            <el-input type="text" v-model="dataForm.hx1AntennaHeight"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="铁塔类型" prop="hx1TowerType">
                                            <el-input type="text" v-model="dataForm.hx1TowerType"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="铁塔细分种类" prop="hx1TowerTypeDetail">
                                            <el-input type="text" v-model="dataForm.hx1TowerTypeDetail"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="机房类型" prop="hx1RoomType">
                                            <el-input type="text" v-model="dataForm.hx1RoomType"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="建设方式" prop="hx1BuildType">
                                            <el-input type="text" v-model="dataForm.hx1BuildType"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="共建方1名称" prop="hx1Gjf1Name">
                                            <el-input type="text" v-model="dataForm.hx1Gjf1Name"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共建方1需求站点名称" prop="hx1Gjf1StationName">
                                            <el-input type="text" v-model="dataForm.hx1Gjf1StationName"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共建方1需求站点编号" prop="hx1Gjf1StationNum">
                                            <el-input type="text" v-model="dataForm.hx1Gjf1StationNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共建方1需求来源批次" prop="hx1Gjf1SourceBatch">
                                            <el-input type="text" v-model="dataForm.hx1Gjf1SourceBatch"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="共建方2名称" prop="hx1Gjf2Name">
                                            <el-input type="text" v-model="dataForm.hx1Gjf2Name"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共建方2需求站点名称" prop="hx1Gjf2StationName">
                                            <el-input type="text" v-model="dataForm.hx1Gjf2StationName"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共建方2需求站点编号" prop="hx1Gjf2StationNum">
                                            <el-input type="text" v-model="dataForm.hx1Gjf2StationNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共建方2需求来源批次" prop="hx1Gjf2SourceBatch">
                                            <el-input type="text" v-model="dataForm.hx1Gjf2SourceBatch"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="18">
                                        <el-form-item><span>候补站点2</span></el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="站点查询" prop="queryStation2">
                                            <el-select v-model="queryStation2"
                                                       filterable
                                                       remote
                                                       reserve-keyword
                                                       placeholder="请输入站名"
                                                       :remote-method="remoteData2"
                                                       :loading="loading"
                                                       no-data-text=""
                                                       default-first-option
                                                       @change="setHx2Value" style="width: 100%">
                                                <el-option
                                                        v-for="item in options2"
                                                        :key="item.id"
                                                        :label="item.stationName"
                                                        :value="item">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="站点名称" prop="hx2StationName">
                                            <el-input type="text" v-model="dataForm.hx2StationName"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="站点编号" prop="hx2StationNum">
                                            <el-input type="text" v-model="dataForm.hx2StationNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="经度" prop="hx2Longitude">
                                            <el-input type="text" v-model="dataForm.hx2Longitude"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="纬度" prop="hx2Latitude">
                                            <el-input type="text" v-model="dataForm.hx2Latitude"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="详细地址" prop="hx2Address">
                                            <el-input type="text" v-model="dataForm.hx2Address"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="是否共享已有站址" prop="hx2IfShare">
                                            <el-input type="text" v-model="dataForm.hx2IfShare"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共享存量站址产权归属方" prop="hx2ShareStationBelong">
                                            <el-input type="text" v-model="dataForm.hx2ShareStationBelong"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="原有运营商数(户)" prop="hx2OldOperatorNum">
                                            <el-input type="text" v-model="dataForm.hx2OldOperatorNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="原有具体运营商" prop="hx2OldOperator">
                                            <el-input type="text" v-model="dataForm.hx2OldOperator"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="本次新增运营商数(户)" prop="hx2NewOperatorNum">
                                            <el-input type="text" v-model="dataForm.hx2NewOperatorNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="新增具体运营商" prop="hx2NewOperator">
                                            <el-input type="text" v-model="dataForm.hx2NewOperator"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="天线挂高范围" prop="hx2AntennaHeight">
                                            <el-input type="text" v-model="dataForm.hx2AntennaHeight"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="铁塔类型" prop="hx2TowerType">
                                            <el-input type="text" v-model="dataForm.hx2TowerType"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="铁塔细分种类" prop="hx2TowerTypeDetail">
                                            <el-input type="text" v-model="dataForm.hx2TowerTypeDetail"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="机房类型" prop="hx2RoomType">
                                            <el-input type="text" v-model="dataForm.hx2RoomType"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="建设方式" prop="hx2BuildType">
                                            <el-input type="text" v-model="dataForm.hx2BuildType"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="共建方1名称" prop="hx2Gjf1Name">
                                            <el-input type="text" v-model="dataForm.hx2Gjf1Name"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共建方1需求站点名称" prop="hx2Gjf1StationName">
                                            <el-input type="text" v-model="dataForm.hx2Gjf1StationName"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共建方1需求站点编号" prop="hx2Gjf1StationNum">
                                            <el-input type="text" v-model="dataForm.hx2Gjf1StationNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共建方1需求来源批次" prop="hx2Gjf1SourceBatch">
                                            <el-input type="text" v-model="dataForm.hx2Gjf1SourceBatch"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="共建方2名称" prop="hx2Gjf2Name">
                                            <el-input type="text" v-model="dataForm.hx2Gjf2Name"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共建方2需求站点名称" prop="hx2Gjf2StationName">
                                            <el-input type="text" v-model="dataForm.hx2Gjf2StationName"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共建方2需求站点编号" prop="hx2Gjf2StationNum">
                                            <el-input type="text" v-model="dataForm.hx2Gjf2StationNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共建方2需求来源批次" prop="hx2Gjf2SourceBatch">
                                            <el-input type="text" v-model="dataForm.hx2Gjf2SourceBatch"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="站点级别" prop="stationLevel">
                                            <el-input type="text" v-model="dataForm.stationLevel"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="共享用户数" prop="shareUserNum">
                                            <el-input type="text" v-model="dataForm.shareUserNum"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="备注" prop="remarks">
                                            <el-input type="text" v-model="dataForm.remarks"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="审核状态" prop="actProcStatus">
                                            <el-select v-model="dataForm.actProcStatus" placeholder="请选择"
                                                       style="width: 100%">
                                                <el-option
                                                        v-for="item in options"
                                                        :key="item.value"
                                                        :label="item.label"
                                                        :value="item.value"
                                                        :disabled="item.disabled">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            </div>
                        </el-collapse-item>
                    </el-collapse>
                </el-form>
            </el-main>
        </el-container>
        <span slot="footer" class="dialog-footer">
            <el-button @click="visible = false">关闭</el-button>
            <el-button v-if="isAuth('gzl:table3:update')" type="primary" @click="dataFormSubmit()">确定</el-button>
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
                operatorOptions: [{
                    value: '移动',
                    label: '移动'
                }, {
                    value: '联通',
                    label: '联通'
                }, {
                    value: '电信',
                    label: '电信'
                }],
                shareOptions: [{
                    value: '是',
                    label: '是'
                }, {
                    value: '否',
                    label: '否'
                }],
                options: [{
                    value: '1',
                    label: '待审核'
                }, {
                    value: '2',
                    label: '站址筛查通过'
                }, {
                    value: '3',
                    label: '站址筛查不通过'
                }, {
                    value: '4',
                    label: '选址谈判完成，开始建站',
                    disabled: true
                }, {
                    value: '5',
                    label: '选址困难，提变更',
                    disabled: true
                }, {
                    value: '6',
                    label: '建站完成',
                    disabled: true
                }, {
                    value: '7',
                    label: '建站工程受阻，提变更',
                    disabled: true
                }, {
                    value: '8',
                    label: '建站进行中',
                    disabled: true
                }],
                options1: [],
                options2: [],
                buildTypeOptions: [{
                    value: '新建',
                    label: '新建'
                }, {
                    value: '改造',
                    label: '改造'
                }, {
                    value: '存量直接满足',
                    label: '存量直接满足'
                }],
                sceneOptions: [{
                    value: '密集市区',
                    label: '密集市区'
                }, {
                    value: '一般市区',
                    label: '一般市区'
                }, {
                    value: '县城',
                    label: '县城'
                }, {
                    value: '乡镇',
                    label: '乡镇'
                }, {
                    value: '农村',
                    label: '农村'
                }],
                dataRule: {},
                queryStation1: '',
                queryStation2: '',
                loading: false,
                processImgSrc: '',
                activeNames: ['1']
            }
        },
        computed: {},
        methods: {
            // 初始化
            init(row) {
                this.visible = true;
                this.history(row.actProcInstId);
                this.processImg(row.actProcInstId);
                this.dataFormFill(row.actProcInstId);
            },
            // 工作流记录
            history(id) {
                this.$http({
                    url: this.$http.adornUrl('/api/wf/findProcessHistoryById'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'processInstanceId': id,
                    })
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.historyList = data.history;
                    } else {

                    }
                })
            },
            //表单填充
            dataFormFill(id) {
                this.$http({
                    url: this.$http.adornUrl('/api/wf/fillSupervisorForm'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'processInstanceId': id,
                    })
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataForm = data.returnData;
                    } else {
                        this.$message.error(data.msg)
                    }
                });
                this.dataForm = Object.assign({});
            },
            // 表单提交
            dataFormSubmit() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        this.$http({
                            url: this.$http.adornUrl('/api/wf/checkProcesses'),
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
                                        this.$emit('refreshDataList');
                                    }
                                })
                            } else {
                                this.$message.error(data.msg)
                            }
                        })
                    }
                })
            },
            processImg(id) {
                let url = this.$http.adornUrl(`/api/wf/processImg?processInstanceId=` + id);
                this.processImgSrc = url;
//                this.$http({
//                    url: this.$http.adornUrl('/api/wf/processImg'),
//                    method: 'get',
//                    params: this.$http.adornParams({
//                        'processInstanceId': id,
//                    })
//                }).then(({data}) => {
//                    this.processImgSrc = data;
//                })
            },
            remoteData1(query) {
                if (query !== '') {
                    this.loading = true;
                    setTimeout(() => {
                        this.$http({
                            url: this.$http.adornUrl('/api/zhzygl/stationAddressManagementList'),
                            method: 'get',
                            params: this.$http.adornParams({
                                'key': query
                            })
                        }).then(({data}) => {
                            if (data && data.code === 0) {
                                this.options1 = data.page.list;
                            } else {
                                this.options1 = [];
                            }
                            this.loading = false;
                        });
                    }, 1000);

                }
            },
            setHx1Value(value) {
                this.dataForm.hx1StationName = value.stationName;
                this.dataForm.hx1StationNum = value.stationNum;
                this.dataForm.hx1Longitude = value.longitude;
                this.dataForm.hx1Latitude = value.latitude;
                this.dataForm.hx1Address = value.address;
                this.dataForm.hx1IfShare = value.ifOperatorShare;
                this.dataForm.hx1RoomType = value.roomType;
            },
            remoteData2(query) {
                if (query !== '') {
                    this.loading = true;
                    setTimeout(() => {
                        this.$http({
                            url: this.$http.adornUrl('/api/zhzygl/stationAddressManagementList'),
                            method: 'get',
                            params: this.$http.adornParams({
                                'key': query
                            })
                        }).then(({data}) => {
                            if (data && data.code === 0) {
                                this.options2 = data.page.list;
                            } else {
                                this.options2 = [];
                            }
                            this.loading = false;
                        });
                    }, 1000);

                }
            },
            setHx2Value(value) {
                this.dataForm.hx2StationName = value.stationName;
                this.dataForm.hx2StationNum = value.stationNum;
                this.dataForm.hx2Longitude = value.longitude;
                this.dataForm.hx2Latitude = value.latitude;
                this.dataForm.hx2Address = value.address;
                this.dataForm.hx2IfShare = value.ifOperatorShare;
                this.dataForm.hx2RoomType = value.roomType;
            }

        }
    }
</script>

