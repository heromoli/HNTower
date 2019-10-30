<template>
    <div class="container-fluid">
        <el-row :gutter="5" style="height:95px">
            <el-col :span="3"><div class="grid-content bg-purple">
                <template>
                    <!--<img :src="logo"  :width="200" :height="50" >-->
                    <h1 class="logo_style3">光网在线</h1>
                </template>
            </div></el-col>
            <el-col :span="2" :offset="1"><div class="grid-content bg-purple">
                    <div class="head_card_up">
                        影响用户数
                    </div>
                    <div class="head_card_down">
                        18473
                    </div>
            </div></el-col>
            <el-col :span="2"><div class="grid-content bg-purple">
                <div class="head_card_up">
                    经济损失(万元)
                </div>
                <div class="head_card_down">
                    10086
                </div>
            </div></el-col>
            <el-col :span="2"><div class="grid-content bg-purple">
                <div class="head_card_up">
                    出动抢修人员
                </div>
                <div class="head_card_down">
                    687
                </div>
            </div></el-col>
            <el-col :span="2"><div class="grid-content bg-purple">
                <div class="head_card_up">
                    出动抢修车辆
                </div>
                <div class="head_card_down">
                    282
                </div>
            </div></el-col>
            <el-col :span="3"><div class="grid-content bg-purple">
                <div class="head_card_up">
                    发送预警短信(万户)
                </div>
                <div class="head_card_down">
                    202
                </div>
            </div></el-col>
            <el-col :span="2"><div class="grid-content bg-purple">
                <div class="head_card_up">
                    卫星保障电话
                </div>
                <div class="head_card_down">
                    80
                </div>
            </div></el-col>
            <el-col :span="2"><div class="grid-content bg-purple">
                <div class="head_card_up">
                    应急油机发电
                </div>
                <div class="head_card_down">
                    1772
                </div>
            </div></el-col>
            <el-col :span="5"><div class="grid-content bg-purple">
                <div class="head_card_tq">
                    <iframe width="245" scrolling="no" height="18" frameborder="0" allowtransparency="true" src="//i.tianqi.com/index.php?c=code&id=1&color=%23FFFFFF&icon=1&py=haikou&wind=1&num=1&site=12"></iframe>
                </div>
            </div></el-col>
        </el-row>

        <el-row :gutter="0" :style="'height:'+centerHeight+'px'">
            <el-col :span="19">
                <div class="grid-content bg-purple">
                    <el-row :gutter="0" :style="'height:'+centerHeight+'px'">
                        <el-col :span="7"><div class="grid-content bg-purple">
                            <el-row :gutter="0" >
                                <el-col :span="24" :style="'height:'+centerHeight1+'px'">
                                    <div class="center_div_font">
                                        产权基站
                                    </div>
                                </el-col>
                                <el-col :span="12" :style="'height:'+centerHeight2+'px'">
                                        <div style="height: 100%;width: 100%" id="main70"></div>
                                </el-col>
                                <el-col :span="12" :style="'height:'+centerHeight2+'px'">
                                        <div style="height: 100%;width: 100%" id="main80"></div>
                                </el-col>
                                <el-col :span="12" :style="'height:'+centerHeight1+'px'">
                                    <div class="center_div_font1" :style="'margin-top: '+center_m_top+'px'">
                                        当前停电6个
                                    </div>
                                </el-col>
                                <el-col :span="12" :style="'height:'+centerHeight1+'px'">
                                    <div class="center_div_font1" :style="'margin-top: '+center_m_top+'px'">
                                        累计停电退服16个
                                    </div>
                                </el-col>
                                <el-col :span="24" :style="'height:'+centerHeight1+'px'">
                                    <div class="center_div_font">
                                        光缆
                                    </div>
                                </el-col>
                                <el-col :span="12"  :style="'height:'+centerHeight2+'px'">
                                    <div style="height: 100%;width: 100%" id="main21">2</div>
                                    <!--<div class="center_div_font" :style="'height:'+centerHeight1+'px'">-->
                                        <!--光缆-->
                                    <!--</div>-->
                                    <!--<el-col :span="24" :style="'height:'+centerHeight2+'px'">-->
                                        <!--<div style="height: 100%;width: 100%" id="main21">2</div>-->
                                    <!--</el-col>-->
                                    <!--<el-col :span="24" :style="'height:'+centerHeight1+'px'">-->
                                        <!--<div class="center_div_font1" :style="'margin-top: '+center_m_top+'px'">-->
                                            <!--累计修复6条-->
                                        <!--</div>-->
                                    <!--</el-col>-->
                                </el-col>
                                <el-col  :span="12" :style="'height:'+centerHeight2+'px'">
                                    <div style="height: 100%;width: 100%" id="main22">1</div>
                                </el-col>
                                <el-col :span="24" :style="'height:'+centerHeight1+'px'">
                                    <div class="center_div_font1" :style="'margin-top: '+center_m_top+'px'">
                                        累计修复55条
                                    </div>
                                </el-col>


                            </el-row>
                        </div></el-col>
                        <el-col :span="9"><div class="grid-content bg-purple" :style="'width:100%;height:'+(centerHeightMap)+'px'" id="map">&nbsp;
                            <!--地图-->
                        </div></el-col>
                        <el-col :span="7"><div class="grid-content bg-purple">
                            <el-row :gutter="0" >
                                <el-col :span="24"  :style="'height:'+centerHeight1+'px'">
                                    <div class="center_div_font">
                                        逻辑基站
                                    </div>
                                </el-col>
                                <el-col :span="12"  :style="'height:'+centerHeight2+'px'">
                                    <div style="height: 100%;width: 100%" id="main8"></div>

                                </el-col>
                                <el-col :span="12"  :style="'height:'+centerHeight2+'px'" >
                                    <div style="height: 100%;width: 100%" id="main7"></div>

                                </el-col>
                                <el-col :span="12"  :style="'height:'+centerHeight1+'px'">
                                    <div class="center_div_font1" :style="'margin-top: '+center_m_top+'px'">
                                        累计修复124个
                                    </div>
                                </el-col>
                                <el-col :span="12"  :style="'height:'+centerHeight1+'px'">
                                    <div class="center_div_font1" :style="'margin-top: '+center_m_top+'px'">
                                        当前退服72个
                                    </div>
                                </el-col>
                                <el-col :span="24" class="center_div_mtop"  :style="'height:'+(centerHeight1*2+centerHeight2-10)+'px'">
                                    <table class="bg-row1 center_div_mtop">
                                        <tr>
                                            <td><el-tag type="success" style="color:white">运营商</el-tag></td>
                                            <td><el-tag type="success" style="color:white">基站总数</el-tag></td>
                                            <td><el-tag type="success" style="color:white">退服率</el-tag></td>
                                            <td><el-tag type="success" style="color:white">中断光缆数</el-tag></td>
                                            <td><el-tag type="success" style="color:white">修复率</el-tag></td>
                                        </tr>

                                        <tr v-for="item in tableData">
                                            <td>{{item.yys}}</td>
                                            <td>{{item.jzzs}}</td>
                                            <td>{{item.tfl}}</td>
                                            <td>{{item.zdgls}}</td>
                                            <td>{{item.xfl}}</td>
                                        </tr>
                                    </table>


                                </el-col>
                            </el-row>
                        </div></el-col>
                    </el-row>
                </div>
            </el-col>
            <el-col :span="5"><div class="grid-content bg-purple">

                    <el-col :span="24"  style="padding-bottom: 1px">
                        <el-card class="box-card box-card-style" :style="'height:'+rightHeight+'px'">
                            <div style="height: 100%;text-align: center">
                                台风信息
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="24"  style="padding-bottom: 1px">
                        <el-card class="box-card box-card-style" :style="'height:'+rightHeight+'px'">
                            <table class="bg-row2">
                                <tr>
                                    <td><el-tag type="success" style="color:white;width:75px">站点名称</el-tag></td>
                                    <td><el-tag type="success" style="color:white;width:79px">加油站电话</el-tag></td>
                                    <td><el-tag type="success" style="color:white;width:auto">加油站地址</el-tag></td>
                                </tr>

                                <tr v-for="item in rightTable1">
                                    <td >{{item.站点名称}}</td>
                                    <td>{{item.加油站电话}}</td>
                                    <td class="card_space">{{item.加油站地址}}</td>
                                </tr>
                            </table>
                        </el-card>
                    </el-col>
                    <el-col :span="24">
                        <el-card class="box-card box-card-style" :style="'height:'+rightHeight+'px'">
                            <div style="height: 100%;text-align: center">
                                其他应急信息
                            </div>
                        </el-card>
                    </el-col>
            </div></el-col>
        </el-row>
        <el-row :gutter="0" :style="'height:'+centerHeight3+'px'">
            <el-col :span="24">
                <!--<el-card class="box-card"  style="background-color: #061d46">-->
                    <div id="bottom_line" :style="'height:'+centerHeight3+'px'"></div>
                <!--</el-card>-->
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import echarts from 'echarts'
    import hainan_json from '@/utils/460002.json'
    export default {
        name: "larg-screen",
        data() {
            return {
                centerHeight:400,
                centerHeight1:30,
                centerHeight2:145,
                centerHeight3:120,
                center_m_top:-20,
                tableData:[
                    {'yys':'移动','jzzs':'14785','tfl':'0.8%	','zdgls':'33','xfl':'97%'},
                    {'yys':'联通','jzzs':'6395','tfl':'3.1%','zdgls':'19','xfl':'94.7%'},
                    {'yys':'电信','jzzs':'5436','tfl':'2.0%','zdgls':'5','xfl':'100%'},
                ],
                rightTableData_num:0,
                rightTableData_timer:null,
                rightTableData:[
                    {'所在市县':'海口','所属公司':'中石化','站点名称':'海口西秀','站长':'黎基明',	'站长电话':'13976088765','加油站电话':'68644846','加油站地址':'海口市庆龄大道军区车船大队旁（军区车船大队旁）'}
                    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口广场','站长':'吴鹏',	'站长电话':'13807598465','加油站电话':'68624791','加油站地址':'海口市长滨路市政府旁边'}
                    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口五西路','站长':'柯亚昌',	'站长电话':'13198902362','加油站电话':'66184455','加油站地址':'海口市海甸五西路18号（市政工程设计研究院旁）（海大北门对面）'}
                    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口长风','站长':'陈益松',	'站长电话':'13322030837','加油站电话':'66103167','加油站地址':'海口市长堤路长沙坡28号(水巷口、和平桥往世纪大桥方向)'}
                    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口龙昆南','站长':'陈运锋',	'站长电话':'13322013886','加油站电话':'66760836','加油站地址':'海口市龙昆南路西侧昌茂花园旁（昌茂花园旁边）'}
                    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口货运大道','站长':'关倩',	'站长电话':'18217843010','加油站电话':'66978283','加油站地址':'海口市货运大道与学院路交界处南侧（省交通快速处理中心对面）'}
                    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口海府','站长':'唐卫卿',	'站长电话':'13976634871','加油站电话':'65342373','加油站地址':'海口市海府大道163号（东站斜对面）'}
                    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口荣泰','站长':'赵玉才',	'站长电话':'13907661068','加油站电话':'66978295','加油站地址':'海口市苍峰路南侧与货运大道交叉处北侧（椰海商贸广场对面）'}
                    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口滨海','站长':'符福存',	'站长电话':'13876809868','加油站电话':'66773900','加油站地址':'海口市滨海大道(滨海公园斜对面，新华书店旁边）'}
                    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口疏港','站长':'林育',	'站长电话':'18789156828','加油站电话':'68918720','加油站地址':'海口市邱海大道红绿灯旁（海瑞桥）'}
                    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口秀英','站长':'孙晓红',	'站长电话':'13976776999','加油站电话':'68912148','加油站地址':'海口市秀英村（顺发新春对面、国美电器旁边）西站附近海垦天桥旁边'}
                    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口金盘','站长':'李平珠',	'站长电话':'13322021715','加油站电话':'66811566','加油站地址':'海口市工业大道中段美国工业村对面（美国工业村对面）（海职院旁边）'}
                    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口山高','站长':'李鹏',	'站长电话':'13876378207','加油站电话':'65907892','加油站地址':'海口市龙昆南路与凤翔路拐角处侧（海口高铁东站）'}
                    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口海达','站长':'曾竟',	'站长电话':'13307572325','加油站电话':'66269167','加油站地址':'海口市海甸岛海达路海达加油站'}
                    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口海港','站长':'张  洋',	'站长电话':'18689709200','加油站电话':'68653741','加油站地址':'海口市秀英区滨海大道82号（秀英码头旁）'}
                    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口珠龙','站长':'米利红',	'站长电话':'15799066551','加油站电话':'65365060','加油站地址':'海口市美祥路28号中石油珠龙加油站'}
                    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口龙桥西','站长':'王茂民',	'站长电话':'13098938260','加油站电话':'65502282','加油站地址':'海口市东线高速公路龙桥出口处西侧中石油龙桥西加油站'}
                    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口城东','站长':'冯海锋',	'站长电话':'13876398783','加油站电话':'65828673','加油站地址':'海口市琼山区府城镇琼州大道12号'}
                    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口城西','站长':'王浩',	'站长电话':'13322008385','加油站电话':'66894252','加油站地址':'海口市城西路和南航部队交叉口处中石油加油站'}
                    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口瑞海','站长':'吴挺芳',	'站长电话':'13976557668','加油站电话':'68668137','加油站地址':'海口市秀英区丘海大道西侧罗牛山肉联厂旁'}
                    ,{'所在市县':'三亚','所属公司':'中石化','站点名称':'凤凰','站长':'卢宏壮',	'站长电话':'13322051960','加油站电话':'88341968','加油站地址':'三亚市海榆西线凤凰机场路入口（400米）'}
                    ,{'所在市县':'三亚','所属公司':'中石化','站点名称':'东线','站长':'何伟',	'站长电话':'13807525123','加油站电话':'','加油站地址':'三亚市田独镇(济阳大道）（海螺姑娘斜对面）'}
                    ,{'所在市县':'三亚','所属公司':'中石化','站点名称':'西线','站长':'林明智',	'站长电话':'13876917123','加油站电话':'88343228','加油站地址':'三亚市羊栏镇西线高速公路入口(桶井村附近)'}
                    ,{'所在市县':'三亚','所属公司':'中石化','站点名称':'金凤','站长':'李梅',	'站长电话':'13036072059','加油站电话':'88332380','加油站地址':'三亚市羊栏镇凤凰村入口旁（昌元水晶对面）'}
                    ,{'所在市县':'三亚','所属公司':'中石化','站点名称':'红沙','站长':'何明权',	'站长电话':'18089799555','加油站电话':'88670390','加油站地址':'三亚市红沙镇'}
                    ,{'所在市县':'三亚','所属公司':'中石油','站点名称':'三亚东岸','站长':'陈川明',	'站长电话':'13876663228','加油站电话':'88383232','加油站地址':'三亚市迎宾路东岸村'}
                    ,{'所在市县':'三亚','所属公司':'中石油','站点名称':'三亚交协','站长':'曾鹏',	'站长电话':'13700498844','加油站电话':'88340728','加油站地址':'三亚市羊栏镇高墩坡'}
                    ,{'所在市县':'儋州','所属公司':'中石化','站点名称':'中兴','站长':'郑天成',	'站长电话':'13976784918','加油站电话':'23322068','加油站地址':'儋州市那大镇中兴大道西(12点后不加摩托车)（东坡学校对面）'}
                    ,{'所在市县':'儋州','所属公司':'中石化','站点名称':'宏腾','站长':'李秋燕',	'站长电话':'13907678904','加油站电话':'23317279','加油站地址':'儋州市那大美扶乡美扶村那大镇中兴大道VII—22地段(靠近烟草公司）'}
                    ,{'所在市县':'儋州','所属公司':'中石油','站点名称':'儋州城北','站长':'符现鹗',	'站长电话':'18876642687','加油站电话':'23705523','加油站地址':'儋州市那东公路西联路口（中国石油加油站）'}
                    ,{'所在市县':'儋州','所属公司':'中石油','站点名称':'儋州学院','站长':'谭之孔',	'站长电话':'13876959892','加油站电话':'23300050','加油站地址':'儋州市热作两院铺前村（中国石油加油站）'}
                    ,{'所在市县':'琼海','所属公司':'中石化','站点名称':'加积','站长':'覃之梁',	'站长电话':'13976395658','加油站电话':'62931768','加油站地址':'琼海市加积镇振海路与富海路交叉口（市委附近）新海路-银海路'}
                    ,{'所在市县':'琼海','所属公司':'中石化','站点名称':'海文','站长':'陈山',	'站长电话':'13976391115','加油站电话':'62939481','加油站地址':'琼海市嘉积镇富海路先锋城'}
                    ,{'所在市县':'琼海','所属公司':'中石油','站点名称':'琼海金海','站长':'郑鸣雪',	'站长电话':'13647557917','加油站电话':'62922711','加油站地址':'琼海市金海北路'}
                    ,{'所在市县':'琼海','所属公司':'中石油','站点名称':'琼海兴海','站长':'周志伟',	'站长电话':'13876608029','加油站电话':'62922248','加油站地址':'琼海市加积镇兴海中路'}
                    ,{'所在市县':'琼海','所属公司':'中石油','站点名称':'琼海新安','站长':'陈长超',	'站长电话':'13647595259','加油站电话':'62687718','加油站地址':'琼海市中原镇中兴北路'}
                    ,{'所在市县':'文昌','所属公司':'中石化','站点名称':'文清','站长':'周裔',	'站长电话':'13976693899','加油站电话':'63330801','加油站地址':'文昌市文城镇文清路（政府旁）'}
                    ,{'所在市县':'文昌','所属公司':'中石化','站点名称':'文城','站长':'潘正涛',	'站长电话':'13876228887','加油站电话':'63223779','加油站地址':'文昌市文城镇文建路维加大酒店附近'}
                    ,{'所在市县':'文昌','所属公司':'中石油','站点名称':'文昌文西','站长':'云峰',	'站长电话':'13807614338','加油站电话':'63225062','加油站地址':'文昌市文城镇文西路中国石油加油站（南阳路口红绿灯旁）'}
                    ,{'所在市县':'万宁','所属公司':'中石化','站点名称':'万州','站长':'李    莉',	'站长电话':'13976555308','加油站电话':'62225595','加油站地址':'万宁市万州大道绿春原酒店附近'}
                    ,{'所在市县':'万宁','所属公司':'中石化','站点名称':'高速路南','站长':'杨智勇',	'站长电话':'13976558995','加油站电话':'62182888','加油站地址':'万宁市长丰镇加劳芳田（长风路口）'}
                    ,{'所在市县':'万宁','所属公司':'中石油','站点名称':'万宁长安','站长':'朱深胡',	'站长电话':'13907525984','加油站电话':'62245163','加油站地址':'万宁市长丰镇'}
                    ,{'所在市县':'万宁','所属公司':'中石油','站点名称':'万宁大茂','站长':'文钦辉',	'站长电话':'18708983603','加油站电话':'','加油站地址':'万宁市大茂镇海榆公路东线西侧'}
                    ,{'所在市县':'东方','所属公司':'中石化','站点名称':'八所','站长':'周海斌',	'站长电话':'13876015020','加油站电话':'25529033','加油站地址':'东方市八所镇东海大街（三角公园、树宝宾馆旁边、绿宝楼斜对面）'}
                    ,{'所在市县':'东方','所属公司':'中石化','站点名称':'顺发','站长':'符育阳',	'站长电话':'13876603523','加油站电话':'25584325','加油站地址':'东方市八所镇九龙北路九龙绿地广场左侧（下高速进市区）'}
                    ,{'所在市县':'东方','所属公司':'中石油','站点名称':'东方瑞龙','站长':'文  冲',	'站长电话':'18608991142','加油站电话':'25593711','加油站地址':'东方市八所镇东方大道68号临时汽车站旁（中国石油加油站）'}
                    ,{'所在市县':'五指山','所属公司':'中石化','站点名称':'五指山北端','站长':'蔡仕煌',	'站长电话':'13700418337','加油站电话':'86638376','加油站地址':'五指山市河北区市中医院斜对面'}
                    ,{'所在市县':'五指山','所属公司':'中石化','站点名称':'五指山畅好','站长':'陈清',	'站长电话':'18608971799','加油站电话':'86669916','加油站地址':'五指山市畅好农场中学斜对面红星小学对面'}
                    ,{'所在市县':'乐东','所属公司':'中石化','站点名称':'新三角','站长':'林志平',	'站长电话':'13637625520','加油站电话':'85522999','加油站地址':'乐东县抱由镇乐安路三角交叉路口(十字路)'}
                    ,{'所在市县':'乐东','所属公司':'中石化','站点名称':'九龙','站长':'王军',	'站长电话':'13016280953','加油站电话':'85821003','加油站地址':'乐东县九所镇西线高速九所出口路交叉口(296公里）九所镇上'}
                    ,{'所在市县':'乐东','所属公司':'中石油','站点名称':'乐东山荣','站长':'郭细',	'站长电话':'18876077077','加油站电话':'85526184','加油站地址':'乐东县县城番豆桥头'}
                    ,{'所在市县':'澄迈','所属公司':'中石化','站点名称':'龙腾','站长':'曾祥雷',	'站长电话':'18976139119','加油站电话':'67611002','加油站地址':'澄迈县金江镇文化北路西侧'}
                    ,{'所在市县':'澄迈','所属公司':'中石化','站点名称':'江北','站长':'王录坚',	'站长电话':'13368918765','加油站电话':'67610407','加油站地址':'澄迈县金江镇环城西路与人民西路交叉路线（澄迈汽车车站）'}
                    ,{'所在市县':'澄迈','所属公司':'中石油','站点名称':'福山福源','站长':'林家春',	'站长电话':'18889599621','加油站电话':'67583775','加油站地址':'澄迈县福山镇福桥路博才村路口'}
                    ,{'所在市县':'澄迈','所属公司':'中石油','站点名称':'澄迈振兴','站长':'牛艳芳',	'站长电话':'13637582043','加油站电话':'67489123','加油站地址':'澄迈县老城经济开发区快速干道往盈滨半岛十字路口'}
                    ,{'所在市县':'澄迈','所属公司':'中石油','站点名称':'澄迈金江','站长':'姜婉',	'站长电话':'13807692313','加油站电话':'67600629','加油站地址':'澄迈县金江镇金马大道县供电公司隔壁'}
                    ,{'所在市县':'临高','所属公司':'中石化','站点名称':'路路通','站长':'许健善',	'站长电话':'13976650011','加油站电话':'28289874','加油站地址':'临城镇临美路临美大道西侧'}
                    ,{'所在市县':'临高','所属公司':'中石化','站点名称':'临城','站长':'邓友娟',	'站长电话':'15298921812','加油站电话':'28284484','加油站地址':'临高县临城镇跃进路城西门解放路交叉路口'}
                    ,{'所在市县':'临高','所属公司':'中石油','站点名称':'临高新桥南','站长':'吴小龙',	'站长电话':'13876603949','加油站电话':'28276026','加油站地址':'临高县临城镇新桥南路县法院新办公楼斜对面'}
                    ,{'所在市县':'定安','所属公司':'中石化','站点名称':'定安和平','站长':'王聘秀',	'站长电话':'18689837810','加油站电话':'63826478','加油站地址':'定安县定城镇见龙大道'}
                    ,{'所在市县':'定安','所属公司':'中石化','站点名称':'定安仙沟','站长':'郑干',	'站长电话':'13876128015','加油站电话':'63825468','加油站地址':'东线高速公路36公里处仙沟服务区'}
                    ,{'所在市县':'定安','所属公司':'中石油','站点名称':'定安塔岭','站长':'龚燕南',	'站长电话':'15808902903','加油站电话':'63830870','加油站地址':'定安县定城镇富民路县公路局隔壁'}
                    ,{'所在市县':'定安','所属公司':'中石油','站点名称':'定安见龙','站长':'李兴龙',	'站长电话':'15595786968','加油站电话':'63807887','加油站地址':'定安县定城镇见龙大道西段（原大众西路）南侧'}
                    ,{'所在市县':'屯昌','所属公司':'中石化','站点名称':'屯昌大道','站长':'蔡其雄',	'站长电话':'13976304682','加油站电话':'67819286','加油站地址':'屯昌县屯昌大道北侧'}
                    ,{'所在市县':'屯昌','所属公司':'中石化','站点名称':'屯昌屯昌','站长':'杜运顺',	'站长电话':'13518029938','加油站电话':'67817384','加油站地址':'屯昌县海榆中线85公里处昌盛路自来水公司旁边(交警中队附近）'}
                    ,{'所在市县':'屯昌','所属公司':'中石油','站点名称':'屯昌屯北','站长':'邓美标',	'站长电话':'13907575322','加油站电话':'67831687','加油站地址':'屯昌县屯城镇昌盛北路1号（大转盘处）'}
                    ,{'所在市县':'屯昌','所属公司':'中石油','站点名称':'屯昌城南','站长':'吴宗桥',	'站长电话':'13518884324','加油站电话':'67831726','加油站地址':'屯昌县海榆中线88公里处'}
                    ,{'所在市县':'陵水','所属公司':'中石化','站点名称':'交通','站长':'董新红',	'站长电话':'13876647618','加油站电话':'83327153','加油站地址':'陵水县陵城镇北斗'}
                    ,{'所在市县':'陵水','所属公司':'中石化','站点名称':'192西','站长':'李才鲁',	'站长电话':'13876557788','加油站电话':'83328138','加油站地址':'陵水县东线高速公路192公里处西侧'}
                    ,{'所在市县':'陵水','所属公司':'中石油','站点名称':'陵水北斗','站长':'王齐仁',	'站长电话':'18789566630','加油站电话':'83313266','加油站地址':'陵水县北斗路'}
                    ,{'所在市县':'昌江','所属公司':'中石化','站点名称':'昌江保梅','站长':'杨耀精',	'站长电话':'13389882275','加油站电话':'26651422','加油站地址':'昌江县石碌镇人民北路生太街邮政局对面'}
                    ,{'所在市县':'昌江','所属公司':'中石化','站点名称':'昌江汇成','站长':'王小军 ',	'站长电话':'13976141616','加油站电话':'26682080','加油站地址':'昌江县石碌镇太坡（县政府往西线高速）'}
                    ,{'所在市县':'昌江','所属公司':'中石油','站点名称':'昌江石碌','站长':'苏安平',	'站长电话':'18898959303','加油站电话':'26652527','加油站地址':'昌江县石碌镇人民北路192号（中国石油加油站）'}
                    ,{'所在市县':'保亭','所属公司':'中石化','站点名称':'保亭三道','站长':'林川',	'站长电话':'13976223090','加油站电话':'83881648','加油站地址':'保亭县三道农场路口斜对面海榆中线旁（呀诺达嘀附近）'}
                    ,{'所在市县':'保亭','所属公司':'中石化','站点名称':'保亭','站长':'王丽梅',	'站长电话':'18789368889','加油站电话':'83669356','加油站地址':'保亭县保城镇保陵公路与东环路交叉路口东北侧中石化保亭加油站'}
                    ,{'所在市县':'琼中','所属公司':'中石化','站点名称':'琼中乌石','站长':'符芳雄',	'站长电话':'13976218858','加油站电话':'86305547','加油站地址':'琼中县湾岭镇乌石墟乌石镇省道边'}
                    ,{'所在市县':'琼中','所属公司':'中石化','站点名称':'琼中城东','站长':'王贤静',	'站长电话':'13876516237','加油站电话':'86236186','加油站地址':'琼中县营根镇海榆中线135公里处(财政局对面）'}
                    ,{'所在市县':'琼中','所属公司':'中石油','站点名称':'琼中城北','站长':'陈玉斯',	'站长电话':'18907578284','加油站电话':'86239278','加油站地址':'琼中县海榆中线琼中汽车站旁'}
                    ,{'所在市县':'白沙','所属公司':'中石化','站点名称':'白沙七坊','站长':'符成永',	'站长电话':'13976400166','加油站电话':'27663049','加油站地址':'白沙黎族自治县七坊镇'}
                    ,{'所在市县':'白沙','所属公司':'中石化','站点名称':'白沙县城','站长':'张绍辉',	'站长电话':'13398990318','加油站电话':'27727087','加油站地址':'白沙县黎族自治县牙叉镇石油路'}
                    ,{'所在市县':'白沙','所属公司':'中石油','站点名称':'白沙白邦','站长':'吴光波',	'站长电话':'13976710720','加油站电话':'27721389','加油站地址':'白沙县牙叉镇白邦路（中国石油加油站）'}
                ],
                bottomData:[
                    {'name':'海口','total':'11240','back':'72'}
                    ,{'name':'三亚','total':'5300','back':'110'}
//                    ,{'name':'三沙','total':'69','back':'0'}
                    ,{'name':'儋州','total':'3084','back':'55'}
                    ,{'name':'琼海','total':'2514','back':'12'}
                    ,{'name':'文昌','total':'2499','back':'10'}
                    ,{'name':'万宁','total':'2147','back':'39'}
                    ,{'name':'东方','total':'1578','back':'18'}
                    ,{'name':'五指山','total':'582','back':'34'}
                    ,{'name':'乐东','total':'1658','back':'88'}
                    ,{'name':'澄迈','total':'1779','back':'48'}
                    ,{'name':'临高','total':'1318','back':'11'}
                    ,{'name':'定安','total':'986','back':'2'}
                    ,{'name':'屯昌','total':'853','back':'8'}
                    ,{'name':'陵水','total':'1685','back':'109'}
                    ,{'name':'昌江','total':'1069','back':'12'}
                    ,{'name':'保亭','total':'707','back':'38'}
                    ,{'name':'琼中','total':'808','back':'55'}
                    ,{'name':'白沙','total':'747','back':'23'}
                ],
            }
        },
        mounted() {
            let _self = this;
            _self.resize();
            window.onresize = function temp() {
                _self.resize();
            };
            this.rightTableData_timer = setInterval(function(){
//                console.log("mounted",_self.rightTableData_num)
                if(_self.rightTableData_num+1==_self.rightTableData.length){
                    _self.rightTableData_num = 0;
                }else{
                    _self.rightTableData_num= _self.rightTableData_num + 1;
                }
            }, 5000);

        },
        computed:{
            centerHeightMap:function(){
                return this.centerHeight;
            },
            rightHeight:function(){
                return (this.centerHeight-2)/3;
            },
            rightTable1:function(){
                let t = [],len=4;
//                console.log("rightTable1",this.rightTableData_num)
                if(this.rightTableData_num+len<this.rightTableData.length){
                    t = this.rightTableData.slice(this.rightTableData_num,this.rightTableData_num+len);
                }else{
                    t = this.rightTableData.slice(this.rightTableData_num,this.rightTableData.length).concat(
                        this.rightTableData.slice(0,(this.rightTableData_num+len)-this.rightTableData.length)
                    )
                }
                return t;
            }
        },
        beforeDestroy() {
            clearInterval(this.rightTableData_timer);
        },
        methods: {
            resize:function(){
                var height = window.innerHeight-95;
                this.centerHeight3 = height/4;
                this.centerHeight = height-this.centerHeight3;
                this.centerHeight2 = (this.centerHeight/2)-70;
//                console.log(this.centerHeight3)
//                console.log(this.centerHeight2)
                if(this.centerHeight2<160){
                    this.center_m_top = 0
                }else if(this.centerHeight2<200){
                    this.center_m_top = -50
                }
                else if(this.centerHeight2<250){
                    this.center_m_top = -90
                }
                else if(this.centerHeight2<300){
                    this.center_m_top = -150
                }else{
                    this.center_m_top = -150
                }
//                console.log(this.centerHeight2,this.center_m_top)
                //this.center_m_top = -1*(this.centerHeight2/4);
                this.init();
            },
            init(){
                this.draw();
            },
            draw(){
               let pData = {
                   unit:'%',
                   title:{
                       text:'1.07%',
                       subtext:'停电率',

                   },
                    series:{
                        name: '停电率',
                        data:[
                            {value: 1.07, name: '停电'},
                            {value: 98.93, name: '未停电'},

                        ]
                    }
                }
                this.drawPie(pData,"main70")


                pData = {
                    unit:'%',
                    title:{
                        text:'8.56%',
                        subtext:'退服率',

                    },
                    series:{
                        name: '退服率',
                        data:[
                            {value: 8.56, name: '退服'},
                            {value: 91.44, name: '未退服'},

                        ]
                    }
                }
                this.drawPie(pData,"main80")


                pData = {
                    unit:'%',
                    title:{
                        text:'96.5%',
                        subtext:'修复率',

                    },
                    series:{
                        name: '修复率',
                        data: [
                            {value: 96.5, name: '已修复'},
                            {value: 3.5, name: '未修复'},

                        ]
                    }
                }
                this.drawPie(pData,"main21")

                pData = {
                    title:{
                        text:'57',
                        subtext:'中断数',
                        textStyle:{
                            color:'#FFF',
                        }
                    },
                    series:{
                        name: '中断数',
                        radius:['0%', '80%'],
                        data: [
                            {value: 100, name: '中断数'},

                        ]
                    }
                }
                this.drawPie(pData,"main22")




                pData = {
                    title:{
                        text:'66.32%',
                        subtext:'修复率',
                        textStyle:{
                            color:'#FFF',
                        }
                    },
                    series:{
                        name: '修复率',
                        data: [

                            {value: 66.32, name: '修复'},
                            {value: 33.68, name: '未修复'},

                        ]
                    }
                }
                this.drawPie(pData,"main8")
                pData = {
                    title:{
                        text:'0.64%',
                        subtext:'退服率',
                        textStyle:{
                            color:'#FFF',
                        }
                    },
                    series:{
                        name: '退服率',
                        data:[
                            {value: 0.64, name: '退服'},
                            {value: 99.36, name: '未退服'},

                        ]
                    }
                }
                this.drawPie(pData,"main7")



                this.drawMap(this.initMap(),'map')
                this.drawLine();
            },
            drawLine(){
                // 基于准备好的dom，初始化echarts实例
                //echarts.init(document.getElementById('cityStation_chartPie'));
                var myChart = echarts.init(document.getElementById('bottom_line'));
                let _self = this;
                _self.bottomData.sort(function(x,y){
                    try{
                        if(Number(x.per)>Number(y.per)){
                            return -1;
                        }else if(Number(x.per)<Number(y.per)){
                            return 1;
                        }else{
                            return 0;
                        }
                    }catch(e) {
                        return -1
                    }
                })
                    //console.log(this.bottomData);
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: ''
                    },
                    tooltip: {
                        trigger: "axis",
                        axisPointer: {
                            type: "shadow",
                        },
                        //formatter:'{b}:{c}%'
                        formatter: function (params) {
                            // 假设此轴的 type 为 'time'。
                            console.log(params)
                            let [a,b] = params;
                            let str = a.axisValue+'<br/>'+
                                        '<div style="color: '+a.color+'">'+a.seriesName+':'+a.value+'</div>'+
                                        '<div style="color: '+a.color+'">'+b.seriesName+':'+b.value+'%</div>';
                            return str;
                        }
                    },

                    grid: {
                        top: "50px",
                        left: "15px",
                        right: "15px",
                        bottom: "20px"
                    },

                    legend: {
                        data: ['退服基站', '退服率'],
                        textStyle: {
                            fontSize: 15,
                            color: '#F1F1F3'
                        }
                    },
                    xAxis: {
                        data: _self.bottomData.map(v =>{return v.name;}),
                        //设置坐标轴字体颜色和宽度
                        axisLine: {
                            lineStyle: {
                                color: '#F1F1F3',
                                width: 2
                            }
                        },
                    },
                    yAxis: [{
                        show: false,
//                        axisTick: {
//                            show: false
//                        },

//                        splitLine: {
//                            show: false
//                        },
//                        axisLine: {
//                            lineStyle: {
//                                color: '#6495ED',
//                                width: 2
//                            }
//                        },

                    },{
                        show: false,
//                        axisTick: {
//                            show: false
//                        },
//
//                        splitLine: {
//                            show: false
//                        },
//                        axisLine: {
//                            lineStyle: {
//                                color: '#6495ED',
//                                width: 2
//                            }
//                        },
                    }],
                    series: [
                        {
                            name: '退服基站',
                            type: 'bar',
//                            data: [10, 20, 20, 10, 10, 20, 10, 20, 10, 20, 20, 10, 10, 20, 10, 20],
                            data: _self.bottomData.map(v =>{return Number(v.back);}),
                            barWidth: 15,
                            itemStyle: {
                                normal: {
                                        color: function (params){
                                            let v = _self.bottomData[params.dataIndex].per;
                                            let c = '';
                                            if( v >= 4 )  c = '#f7390c';
                                            else if( v < 4 && v >= 3 )  c = 'orange';
                                            else if( v < 3 && v >= 2 )  c = 'yellow';
                                            else c = '#0cf70c';
                                            return c;
                                        }
                                }
                            },
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    color: '#e1f3d8'
                                }
                            },

                        },
                        {
                            name: '退服率',
                            type: 'line',
//                            data: [10, 20, 20, 10, 10, 20, 10, 20, 10, 20, 20, 10, 10, 20, 10, 20],
                            data: _self.bottomData.map(v =>{return Number(v.per);}),
//                            barWidth: 15,
                            yAxisIndex:1,
//                            itemStyle: {
//                                normal: {
//                                    color: function (params){
//                                        let v = _self.bottomData[params.dataIndex].per;
//                                        let c = '';
//                                        if( v >= 4 )  c = '#f7390c';
//                                        else if( v < 4 && v >= 3 )  c = 'orange';
//                                        else if( v < 3 && v >= 2 )  c = 'yellow';
//                                        else c = '#0cf70c';
//                                        return c;
//                                    }
//                                }
//                            },
//                            label: {
//                                normal: {
//                                    show: false,
//                                    position: 'top',
//                                    color: '#e1f3d8'
//                                }
//                            },
//                            tooltip:{
//                                formatter:'{b}:{c}%'
//                            }

                        }


                    ]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            },

            drawPie(pData,htmlId){

                var myChart8 = echarts.init(document.getElementById(htmlId));

                // 指定图表的配置项和数据

                var option = {
                    title:{
                        text:pData.title.text,
                        left:'47%',
                        top:'38%',
                        textAlign:'center',
                        subtext:pData.title.subtext,
                        textStyle:{
                            color:pData.title.textStyle&&pData.title.textStyle.color?pData.title.textStyle.color:'#FFF',
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        position:'right',
                        formatter: "{a} <br/>{b} : {c} ({d}"+(pData.unit?pData.unit:'')+")"
                    },
                    legend: {
                        show:false,
                        x: 'left',
                        data: []
                    },
                    calculable: true,
                    series: [
                        {
                            name: pData.series.name,
                            type: 'pie',
                            radius: pData.series.radius?pData.series.radius:['50%', '70%'],
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: false
                                    },
                                    labelLine: {
                                        show: false
                                    }
                                },
//                                emphasis: {
//                                    label: {
//                                        show: true,
//                                        position: 'center',
//                                        textStyle: {
//                                            fontSize: '30',
//                                            fontWeight: 'bold'
//                                        }
//                                    }
//                                }
                            },
                            data: pData.series.data
                        }
                    ]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart8.setOption(option);
            },
            initMap(){

              let mapData = {
                  data:this.bottomData.map(v=>{
                      v.per = Math.floor((v.back/v.total)*10000)/100;
//                      console.log(v.back,v.total,v.per);
                      return {
                          name: v.name,
                          value: v.per
                      }
                  }),
//                  max:max,
//                  min:min,
              }
              return mapData;
            },
            drawMap(mapData,htmlId){


                var name = 'hainan';
                var myChart = echarts.init(document.getElementById(htmlId));
                myChart.showLoading();

                myChart.hideLoading();
//                echarts.registerMap(name, geoJson);
                  echarts.registerMap('hainan', hainan_json);

                myChart.setOption({
                    backgroundColor: '#061d45',
                    toolbox: {
                        feature: {
                            saveAsImage: {
                                show: false
                            }
                        }
                    },
                    tooltip:{
                        trigger: 'item',
                        formatter: function (params,ticket,callback){
                            // console.log(params,ticket)
                            if(params.name)
                                return params.name+":"+params.value+'%';
                            else return "";
                        }
                    },
                    visualMap: {
                        show: true,
                        min: mapData.min,
                        max: mapData.max,
                        top: 'bottom',
                        type:'',
                        splitNumber:4,                      //对于连续型数据，自动平均切分成几段。默认为5段
                        pieces: [                           //自定义『分段式视觉映射组件（visualMapPiecewise）』的每一段的范围，以及每一段的文字，以及每一段的特别的样式
                            {min: 4 , label: '退服率严重'  , color: '#f7390c'},
                            {min: 3, max: 4 , label: '退服率一般'  , color: 'orange'},
                            {min: 2, max: 3, label: '退服率很少'  , color: 'yellow'},
                            {max: 2, label: '基本无退服'  , color: '#0cf70c'},
                        ],

                        textStyle: {
                            color: '#fff',
                            fontSize: 12
                        },
                        //align:'left',
                        //left: 'right',
                        right:'15%',
                        top: '70%',

                        showLabel:true,


                        inRange: {
                            color: ['lightskyblue','yellow', 'orangered'],
                            symbolSize: [0, 100]
                        }
                    },
                    series: [{
                        type: 'map',
                        mapType: name,
                        roam: false,//是否能被缩放 拖动
                        selectedMode:'single',
                        left:'5%',
                        label: {
                            normal: {
                                show: true
                            },
                            emphasis: {
                                textStyle: {
                                    color: '#fff'
                                }
                            }
                        },
                        itemStyle: {

                            normal: {
                                borderColor: '#389BB7',
                                areaColor: '#fff',
                            },
                            emphasis: {
                                areaColor: '#389BB7',
                                borderWidth: 0
                            }
                        },
                        animation: true,
                        data: mapData.data,

                        markArea:{
                            label:true,
                            show:true,
                        }

                    }]
                });



                myChart.on("mapselectchanged", function(param) {
                    var name = param.batch[0].name;
                    var selecteds = param.batch[0].selected;
                    var selected = selecteds[name];

                    console.log(param,name,selected);
                    if(selected) {


                    }})
            }
           ,

            tableRowClassName({row, rowIndex}){
                return 'bg-row';
            }

    }

    }
</script>

<style lang="scss">
    .site-wrapper.site-page--not-found {
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        overflow: hidden;
        .site-content__wrapper {
            padding: 0;
            margin: 0;
            background-color: #fff;
        }
        .site-content {
            position: fixed;
            top: 15%;
            left: 50%;
            z-index: 2;
            padding: 30px;
            text-align: center;
            transform: translate(-50%, 0);
        }
        .not-found-title {
            margin: 20px 0 15px;
            font-size: 10em;
            font-weight: 400;
            color: rgb(55, 71, 79);
        }
        .not-found-desc {
            margin: 0 0 30px;
            font-size: 26px;
            text-transform: uppercase;
            color: rgb(118, 131, 143);
            > em {
                font-style: normal;
                color: #ee8145;
            }
        }
        .not-found-btn-gohome {
            margin-left: 30px;
        }
    }

    .container-fluid{
        background-color: #061d46;
        color:#fff;
        width: 99.85%;
        height: 100%
    }
    .logo_style3 {
        width: auto;
        height: 30px;
        margin-left: 50px;
        font-family: Arial, Helvetica, sans-serif;
        font-size: 2em;
        color: #e1f3d8;
        line-height: 35px;
        text-transform: uppercase
    }
    .head_card_up{
        height: 30%;
        width: 100%;
        text-align:center;
        padding: 5px;

    }
    .head_card_down{
        height: 70%;
        width: 100%;
        text-align:center;
        font-size: 24px;
        line-height: 2;
        color: #0cf70c;
    }
    .head_card_tq{
        height: 100%;
        text-align: center;
        line-height: 50px;
        margin-top: 25px;

    }
    .center_div_font{
        font-size: 18px;
        line-height: 30px;
        text-align: center;
    }
    .center_div_font1{
        font-size: 14px;
        line-height: 30px;
        text-align: center;
        margin-top: -20px;
    }
    .center_div_mtop{
        margin-top: 20px;
    }
    .el-table__row td{
        background-color: #061d46;
        color: #fff;
    }
    .el-table__header .has-gutter {
        background-color: #061d46;
    }
    .has-gutter th{
        background-color: #061d46;
        color: #fff;
    }
    .el-table thead{
        color: #fff;
    }
    .el-table tr{
        background-color: #061d46 !important;
    }
    .el-table th{
        background-color: #061d46 !important;;
    }
    .el-table .bg-row {
        background-color: #061d46;
    }
    .bg-row1 {
        //background-color: #898181;
        width: 100%;
        font-size: 12px;
        margin-left: -5%;
    }
    .bg-row1 tr td{
        background-color: #061d46;
        padding: 2px;
        text-align: center;
        line-height: 30px;
    }
    .box-card-style{
        width: 99%;
        background-color: #061d46 !important;

    }
    .box-card-style div{
        color:#fff
    }
    .bg-row2 {
        //background-color: #898181;
        width: 100%;
        height: 100%;
        font-size: 10px;
       // margin-left: -5%;
    }
    .bg-row2 tr td{
        background-color: #061d46;
        padding: 0px;
        text-align: center;
        line-height: 16px;
    }
    .el-card__body{
        padding: 0px !important;
    }
    .card_space{
        display: -webkit-box;  /*  display: -webkit-box; 必须结合的属性 ，将对象作为弹性伸缩盒子模型显示  */
        -webkit-box-orient: vertical; /*  -webkit-box-orient 必须结合的属性 ，设置或检索伸缩盒对象的子元素的排列方式  */
        -webkit-line-clamp: 2; /*  -webkit-line-clamp用来限制在一个块元素显示的文本的行数 */
        overflow: hidden;
        max-height:38px;
    }
</style>
