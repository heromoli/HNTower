var dataleft1 =
    {
        '产权':{
            '停电':'597',
            '退服':'403',
            '合计':'16829',
        },
        '光缆':{
            '中断':'57',
            '修复':'55',
        },
        '逻辑':{
            '退服':'417',
            '合计':'26616',
        }
    };
var dataleft2= [
    {'运营商':'移动','逻辑基站':'14785','退服率':'0.8% ','修复率':'85.9%'},
    {'运营商':'联通','逻辑基站':'6395','退服率':'3.1%','修复率':'84.1%'},
    {'运营商':'电信','逻辑基站':'5436','退服率':'2.0%','修复率':'91.1%'},
];

var dataleft3=[
    {'运营商':'移动','产权基站':'1118','停电':'19','停电率':'1.7%','退服':'14','退服率':'1.3%'},
    {'运营商':'联通','产权基站':'388','停电':'25','停电率':'6.4%','退服':'10','退服率':'2.6%'},
    {'运营商':'电信','产权基站':'716','停电':'3','停电率':'0.4%','退服':'9','退服率':'1.3%'},
    {'运营商':'铁塔','产权基站':'14607','停电':'550','停电率':'3.8%','退服':'370','退服率':'2.5%'},
    {'运营商':'合计','产权基站':'16829','停电':'597','停电率':'3.5%','退服':'403','退服率':'2.4%'},
];

var datacenter1 ={
    '影响用户数':18473,
    '经济损失(万元)':816,
    '出动抢修人员':2651,
    '出动抢修车辆':1110,
    '预警短信(万户)':2682,
    '卫星保障电话':60,
    '应急油机发电':1775
};

var datacenter23 = [
    {name: "海口", value: 5.4, "退服数": 70, "总站数": 2783},
    {name: '琼海', value: 4.7, "退服数": 10, "总站数": 2783},
    {name: '文昌', value: 3.5, "退服数": 20, "总站数": 3783},
    {name: '儋州', value: 6.5, "退服数": 40, "总站数": 2783},
    {name: '东方', value: 4.5, "退服数": 40, "总站数": 2783},
    {name: '昌江', value: 3.5, "退服数": 40, "总站数": 2783},
    {name: '白沙', value: 8.5, "退服数": 10, "总站数": 2783},
    {name: '万宁', value: 3.5, "退服数": 80, "总站数": 2783},
    {name: '定安', value: 6.1, "退服数": 40, "总站数": 2783},
    {name: '三亚', value: 6.4, "退服数": 10, "总站数": 2783},
    {name: '保亭', value: 5.4, "退服数": 60, "总站数": 2783},
    {name: '乐东', value: 5.9, "退服数": 80, "总站数": 2783},
    {name: '澄迈', value: 5.7, "退服数": 30, "总站数": 2783},
    {name: '琼中', value: 5.3, "退服数": 40, "总站数": 2783},
    {name: '屯昌', value: 5.2, "退服数": 20, "总站数": 2783},
    {name: '临高', value: 5.1, "退服数": 10, "总站数": 2783},
    {name: '陵水', value: 5, "退服数": 60, "总站数": 2783},
    {name: '五指山', value: 4, "退服数": 60, "总站数": 2783},
];

var dataright1= [];

var dataright2 = [
    {'所在市县':'海口','所属公司':'中石化','站点名称':'海口西秀','站长':'黎基明',    '站长电话':'13976088765','加油站电话':'68644846','加油站地址':'海口市庆龄大道军区车船大队旁（军区车船大队旁）'}
    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口广场','站长':'吴鹏',    '站长电话':'13807598465','加油站电话':'68624791','加油站地址':'海口市长滨路市政府旁边'}
    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口五西路','站长':'柯亚昌',  '站长电话':'13198902362','加油站电话':'66184455','加油站地址':'海口市海甸五西路18号（市政工程设计研究院旁）（海大北门对面）'}
    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口长风','站长':'陈益松',   '站长电话':'13322030837','加油站电话':'66103167','加油站地址':'海口市长堤路长沙坡28号(水巷口、和平桥往世纪大桥方向)'}
    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口龙昆南','站长':'陈运锋',  '站长电话':'13322013886','加油站电话':'66760836','加油站地址':'海口市龙昆南路西侧昌茂花园旁（昌茂花园旁边）'}
    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口货运大道','站长':'关倩',  '站长电话':'18217843010','加油站电话':'66978283','加油站地址':'海口市货运大道与学院路交界处南侧（省交通快速处理中心对面）'}
    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口海府','站长':'唐卫卿',   '站长电话':'13976634871','加油站电话':'65342373','加油站地址':'海口市海府大道163号（东站斜对面）'}
    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口荣泰','站长':'赵玉才',   '站长电话':'13907661068','加油站电话':'66978295','加油站地址':'海口市苍峰路南侧与货运大道交叉处北侧（椰海商贸广场对面）'}
    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口滨海','站长':'符福存',   '站长电话':'13876809868','加油站电话':'66773900','加油站地址':'海口市滨海大道(滨海公园斜对面，新华书店旁边）'}
    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口疏港','站长':'林育',    '站长电话':'18789156828','加油站电话':'68918720','加油站地址':'海口市邱海大道红绿灯旁（海瑞桥）'}
    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口秀英','站长':'孙晓红',   '站长电话':'13976776999','加油站电话':'68912148','加油站地址':'海口市秀英村（顺发新春对面、国美电器旁边）西站附近海垦天桥旁边'}
    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口金盘','站长':'李平珠',   '站长电话':'13322021715','加油站电话':'66811566','加油站地址':'海口市工业大道中段美国工业村对面（美国工业村对面）（海职院旁边）'}
    ,{'所在市县':'海口','所属公司':'中石化','站点名称':'海口山高','站长':'李鹏',    '站长电话':'13876378207','加油站电话':'65907892','加油站地址':'海口市龙昆南路与凤翔路拐角处侧（海口高铁东站）'}
    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口海达','站长':'曾竟',    '站长电话':'13307572325','加油站电话':'66269167','加油站地址':'海口市海甸岛海达路海达加油站'}
    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口海港','站长':'张  洋',  '站长电话':'18689709200','加油站电话':'68653741','加油站地址':'海口市秀英区滨海大道82号（秀英码头旁）'}
    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口珠龙','站长':'米利红',   '站长电话':'15799066551','加油站电话':'65365060','加油站地址':'海口市美祥路28号中石油珠龙加油站'}
    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口龙桥西','站长':'王茂民',  '站长电话':'13098938260','加油站电话':'65502282','加油站地址':'海口市东线高速公路龙桥出口处西侧中石油龙桥西加油站'}
    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口城东','站长':'冯海锋',   '站长电话':'13876398783','加油站电话':'65828673','加油站地址':'海口市琼山区府城镇琼州大道12号'}
    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口城西','站长':'王浩',    '站长电话':'13322008385','加油站电话':'66894252','加油站地址':'海口市城西路和南航部队交叉口处中石油加油站'}
    ,{'所在市县':'海口','所属公司':'中石油','站点名称':'海口瑞海','站长':'吴挺芳',   '站长电话':'13976557668','加油站电话':'68668137','加油站地址':'海口市秀英区丘海大道西侧罗牛山肉联厂旁'}
    ,{'所在市县':'三亚','所属公司':'中石化','站点名称':'凤凰','站长':'卢宏壮', '站长电话':'13322051960','加油站电话':'88341968','加油站地址':'三亚市海榆西线凤凰机场路入口（400米）'}
    ,{'所在市县':'三亚','所属公司':'中石化','站点名称':'东线','站长':'何伟',  '站长电话':'13807525123','加油站电话':'','加油站地址':'三亚市田独镇(济阳大道）（海螺姑娘斜对面）'}
    ,{'所在市县':'三亚','所属公司':'中石化','站点名称':'西线','站长':'林明智', '站长电话':'13876917123','加油站电话':'88343228','加油站地址':'三亚市羊栏镇西线高速公路入口(桶井村附近)'}
    ,{'所在市县':'三亚','所属公司':'中石化','站点名称':'金凤','站长':'李梅',  '站长电话':'13036072059','加油站电话':'88332380','加油站地址':'三亚市羊栏镇凤凰村入口旁（昌元水晶对面）'}
    ,{'所在市县':'三亚','所属公司':'中石化','站点名称':'红沙','站长':'何明权', '站长电话':'18089799555','加油站电话':'88670390','加油站地址':'三亚市红沙镇'}
    ,{'所在市县':'三亚','所属公司':'中石油','站点名称':'三亚东岸','站长':'陈川明',   '站长电话':'13876663228','加油站电话':'88383232','加油站地址':'三亚市迎宾路东岸村'}
    ,{'所在市县':'三亚','所属公司':'中石油','站点名称':'三亚交协','站长':'曾鹏',    '站长电话':'13700498844','加油站电话':'88340728','加油站地址':'三亚市羊栏镇高墩坡'}
    ,{'所在市县':'儋州','所属公司':'中石化','站点名称':'中兴','站长':'郑天成', '站长电话':'13976784918','加油站电话':'23322068','加油站地址':'儋州市那大镇中兴大道西(12点后不加摩托车)（东坡学校对面）'}
    ,{'所在市县':'儋州','所属公司':'中石化','站点名称':'宏腾','站长':'李秋燕', '站长电话':'13907678904','加油站电话':'23317279','加油站地址':'儋州市那大美扶乡美扶村那大镇中兴大道VII—22地段(靠近烟草公司）'}
    ,{'所在市县':'儋州','所属公司':'中石油','站点名称':'儋州城北','站长':'符现鹗',   '站长电话':'18876642687','加油站电话':'23705523','加油站地址':'儋州市那东公路西联路口（中国石油加油站）'}
    ,{'所在市县':'儋州','所属公司':'中石油','站点名称':'儋州学院','站长':'谭之孔',   '站长电话':'13876959892','加油站电话':'23300050','加油站地址':'儋州市热作两院铺前村（中国石油加油站）'}
    ,{'所在市县':'琼海','所属公司':'中石化','站点名称':'加积','站长':'覃之梁', '站长电话':'13976395658','加油站电话':'62931768','加油站地址':'琼海市加积镇振海路与富海路交叉口（市委附近）新海路-银海路'}
    ,{'所在市县':'琼海','所属公司':'中石化','站点名称':'海文','站长':'陈山',  '站长电话':'13976391115','加油站电话':'62939481','加油站地址':'琼海市嘉积镇富海路先锋城'}
    ,{'所在市县':'琼海','所属公司':'中石油','站点名称':'琼海金海','站长':'郑鸣雪',   '站长电话':'13647557917','加油站电话':'62922711','加油站地址':'琼海市金海北路'}
    ,{'所在市县':'琼海','所属公司':'中石油','站点名称':'琼海兴海','站长':'周志伟',   '站长电话':'13876608029','加油站电话':'62922248','加油站地址':'琼海市加积镇兴海中路'}
    ,{'所在市县':'琼海','所属公司':'中石油','站点名称':'琼海新安','站长':'陈长超',   '站长电话':'13647595259','加油站电话':'62687718','加油站地址':'琼海市中原镇中兴北路'}
    ,{'所在市县':'文昌','所属公司':'中石化','站点名称':'文清','站长':'周裔',  '站长电话':'13976693899','加油站电话':'63330801','加油站地址':'文昌市文城镇文清路（政府旁）'}
    ,{'所在市县':'文昌','所属公司':'中石化','站点名称':'文城','站长':'潘正涛', '站长电话':'13876228887','加油站电话':'63223779','加油站地址':'文昌市文城镇文建路维加大酒店附近'}
    ,{'所在市县':'文昌','所属公司':'中石油','站点名称':'文昌文西','站长':'云峰',    '站长电话':'13807614338','加油站电话':'63225062','加油站地址':'文昌市文城镇文西路中国石油加油站（南阳路口红绿灯旁）'}
    ,{'所在市县':'万宁','所属公司':'中石化','站点名称':'万州','站长':'李    莉',  '站长电话':'13976555308','加油站电话':'62225595','加油站地址':'万宁市万州大道绿春原酒店附近'}
    ,{'所在市县':'万宁','所属公司':'中石化','站点名称':'高速路南','站长':'杨智勇',   '站长电话':'13976558995','加油站电话':'62182888','加油站地址':'万宁市长丰镇加劳芳田（长风路口）'}
    ,{'所在市县':'万宁','所属公司':'中石油','站点名称':'万宁长安','站长':'朱深胡',   '站长电话':'13907525984','加油站电话':'62245163','加油站地址':'万宁市长丰镇'}
    ,{'所在市县':'万宁','所属公司':'中石油','站点名称':'万宁大茂','站长':'文钦辉',   '站长电话':'18708983603','加油站电话':'','加油站地址':'万宁市大茂镇海榆公路东线西侧'}
    ,{'所在市县':'东方','所属公司':'中石化','站点名称':'八所','站长':'周海斌', '站长电话':'13876015020','加油站电话':'25529033','加油站地址':'东方市八所镇东海大街（三角公园、树宝宾馆旁边、绿宝楼斜对面）'}
    ,{'所在市县':'东方','所属公司':'中石化','站点名称':'顺发','站长':'符育阳', '站长电话':'13876603523','加油站电话':'25584325','加油站地址':'东方市八所镇九龙北路九龙绿地广场左侧（下高速进市区）'}
    ,{'所在市县':'东方','所属公司':'中石油','站点名称':'东方瑞龙','站长':'文  冲',  '站长电话':'18608991142','加油站电话':'25593711','加油站地址':'东方市八所镇东方大道68号临时汽车站旁（中国石油加油站）'}
    ,{'所在市县':'五指山','所属公司':'中石化','站点名称':'五指山北端','站长':'蔡仕煌', '站长电话':'13700418337','加油站电话':'86638376','加油站地址':'五指山市河北区市中医院斜对面'}
    ,{'所在市县':'五指山','所属公司':'中石化','站点名称':'五指山畅好','站长':'陈清',  '站长电话':'18608971799','加油站电话':'86669916','加油站地址':'五指山市畅好农场中学斜对面红星小学对面'}
    ,{'所在市县':'乐东','所属公司':'中石化','站点名称':'新三角','站长':'林志平',    '站长电话':'13637625520','加油站电话':'85522999','加油站地址':'乐东县抱由镇乐安路三角交叉路口(十字路)'}
    ,{'所在市县':'乐东','所属公司':'中石化','站点名称':'九龙','站长':'王军',  '站长电话':'13016280953','加油站电话':'85821003','加油站地址':'乐东县九所镇西线高速九所出口路交叉口(296公里）九所镇上'}
    ,{'所在市县':'乐东','所属公司':'中石油','站点名称':'乐东山荣','站长':'郭细',    '站长电话':'18876077077','加油站电话':'85526184','加油站地址':'乐东县县城番豆桥头'}
    ,{'所在市县':'澄迈','所属公司':'中石化','站点名称':'龙腾','站长':'曾祥雷', '站长电话':'18976139119','加油站电话':'67611002','加油站地址':'澄迈县金江镇文化北路西侧'}
    ,{'所在市县':'澄迈','所属公司':'中石化','站点名称':'江北','站长':'王录坚', '站长电话':'13368918765','加油站电话':'67610407','加油站地址':'澄迈县金江镇环城西路与人民西路交叉路线（澄迈汽车车站）'}
    ,{'所在市县':'澄迈','所属公司':'中石油','站点名称':'福山福源','站长':'林家春',   '站长电话':'18889599621','加油站电话':'67583775','加油站地址':'澄迈县福山镇福桥路博才村路口'}
    ,{'所在市县':'澄迈','所属公司':'中石油','站点名称':'澄迈振兴','站长':'牛艳芳',   '站长电话':'13637582043','加油站电话':'67489123','加油站地址':'澄迈县老城经济开发区快速干道往盈滨半岛十字路口'}
    ,{'所在市县':'澄迈','所属公司':'中石油','站点名称':'澄迈金江','站长':'姜婉',    '站长电话':'13807692313','加油站电话':'67600629','加油站地址':'澄迈县金江镇金马大道县供电公司隔壁'}
    ,{'所在市县':'临高','所属公司':'中石化','站点名称':'路路通','站长':'许健善',    '站长电话':'13976650011','加油站电话':'28289874','加油站地址':'临城镇临美路临美大道西侧'}
    ,{'所在市县':'临高','所属公司':'中石化','站点名称':'临城','站长':'邓友娟', '站长电话':'15298921812','加油站电话':'28284484','加油站地址':'临高县临城镇跃进路城西门解放路交叉路口'}
    ,{'所在市县':'临高','所属公司':'中石油','站点名称':'临高新桥南','站长':'吴小龙',  '站长电话':'13876603949','加油站电话':'28276026','加油站地址':'临高县临城镇新桥南路县法院新办公楼斜对面'}
    ,{'所在市县':'定安','所属公司':'中石化','站点名称':'定安和平','站长':'王聘秀',   '站长电话':'18689837810','加油站电话':'63826478','加油站地址':'定安县定城镇见龙大道'}
    ,{'所在市县':'定安','所属公司':'中石化','站点名称':'定安仙沟','站长':'郑干',    '站长电话':'13876128015','加油站电话':'63825468','加油站地址':'东线高速公路36公里处仙沟服务区'}
    ,{'所在市县':'定安','所属公司':'中石油','站点名称':'定安塔岭','站长':'龚燕南',   '站长电话':'15808902903','加油站电话':'63830870','加油站地址':'定安县定城镇富民路县公路局隔壁'}
    ,{'所在市县':'定安','所属公司':'中石油','站点名称':'定安见龙','站长':'李兴龙',   '站长电话':'15595786968','加油站电话':'63807887','加油站地址':'定安县定城镇见龙大道西段（原大众西路）南侧'}
    ,{'所在市县':'屯昌','所属公司':'中石化','站点名称':'屯昌大道','站长':'蔡其雄',   '站长电话':'13976304682','加油站电话':'67819286','加油站地址':'屯昌县屯昌大道北侧'}
    ,{'所在市县':'屯昌','所属公司':'中石化','站点名称':'屯昌屯昌','站长':'杜运顺',   '站长电话':'13518029938','加油站电话':'67817384','加油站地址':'屯昌县海榆中线85公里处昌盛路自来水公司旁边(交警中队附近）'}
    ,{'所在市县':'屯昌','所属公司':'中石油','站点名称':'屯昌屯北','站长':'邓美标',   '站长电话':'13907575322','加油站电话':'67831687','加油站地址':'屯昌县屯城镇昌盛北路1号（大转盘处）'}
    ,{'所在市县':'屯昌','所属公司':'中石油','站点名称':'屯昌城南','站长':'吴宗桥',   '站长电话':'13518884324','加油站电话':'67831726','加油站地址':'屯昌县海榆中线88公里处'}
    ,{'所在市县':'陵水','所属公司':'中石化','站点名称':'交通','站长':'董新红', '站长电话':'13876647618','加油站电话':'83327153','加油站地址':'陵水县陵城镇北斗'}
    ,{'所在市县':'陵水','所属公司':'中石化','站点名称':'192西','站长':'李才鲁',   '站长电话':'13876557788','加油站电话':'83328138','加油站地址':'陵水县东线高速公路192公里处西侧'}
    ,{'所在市县':'陵水','所属公司':'中石油','站点名称':'陵水北斗','站长':'王齐仁',   '站长电话':'18789566630','加油站电话':'83313266','加油站地址':'陵水县北斗路'}
    ,{'所在市县':'昌江','所属公司':'中石化','站点名称':'昌江保梅','站长':'杨耀精',   '站长电话':'13389882275','加油站电话':'26651422','加油站地址':'昌江县石碌镇人民北路生太街邮政局对面'}
    ,{'所在市县':'昌江','所属公司':'中石化','站点名称':'昌江汇成','站长':'王小军 ',  '站长电话':'13976141616','加油站电话':'26682080','加油站地址':'昌江县石碌镇太坡（县政府往西线高速）'}
    ,{'所在市县':'昌江','所属公司':'中石油','站点名称':'昌江石碌','站长':'苏安平',   '站长电话':'18898959303','加油站电话':'26652527','加油站地址':'昌江县石碌镇人民北路192号（中国石油加油站）'}
    ,{'所在市县':'保亭','所属公司':'中石化','站点名称':'保亭三道','站长':'林川',    '站长电话':'13976223090','加油站电话':'83881648','加油站地址':'保亭县三道农场路口斜对面海榆中线旁（呀诺达嘀附近）'}
    ,{'所在市县':'保亭','所属公司':'中石化','站点名称':'保亭','站长':'王丽梅', '站长电话':'18789368889','加油站电话':'83669356','加油站地址':'保亭县保城镇保陵公路与东环路交叉路口东北侧中石化保亭加油站'}
    ,{'所在市县':'琼中','所属公司':'中石化','站点名称':'琼中乌石','站长':'符芳雄',   '站长电话':'13976218858','加油站电话':'86305547','加油站地址':'琼中县湾岭镇乌石墟乌石镇省道边'}
    ,{'所在市县':'琼中','所属公司':'中石化','站点名称':'琼中城东','站长':'王贤静',   '站长电话':'13876516237','加油站电话':'86236186','加油站地址':'琼中县营根镇海榆中线135公里处(财政局对面）'}
    ,{'所在市县':'琼中','所属公司':'中石油','站点名称':'琼中城北','站长':'陈玉斯',   '站长电话':'18907578284','加油站电话':'86239278','加油站地址':'琼中县海榆中线琼中汽车站旁'}
    ,{'所在市县':'白沙','所属公司':'中石化','站点名称':'白沙七坊','站长':'符成永',   '站长电话':'13976400166','加油站电话':'27663049','加油站地址':'白沙黎族自治县七坊镇'}
    ,{'所在市县':'白沙','所属公司':'中石化','站点名称':'白沙县城','站长':'张绍辉',   '站长电话':'13398990318','加油站电话':'27727087','加油站地址':'白沙县黎族自治县牙叉镇石油路'}
    ,{'所在市县':'白沙','所属公司':'中石油','站点名称':'白沙白邦','站长':'吴光波',   '站长电话':'13976710720','加油站电话':'27721389','加油站地址':'白沙县牙叉镇白邦路（中国石油加油站）'}
];


var dataright3 = [
    {"告警名称":"FSU_离线","告警基站数量":"166","告警基站占比":"1.06%"},
    {"告警名称":"高温","告警基站数量":"144","告警基站占比":"0.92%"},
    {"告警名称":"停电","告警基站数量":"126","告警基站占比":"0.80%"},
    {"告警名称":"水浸","告警基站数量":"44","告警基站占比":"0.28%"},
    {"告警名称":"烟雾","告警基站数量":"3","告警基站占比":"0.02%"}
    ];