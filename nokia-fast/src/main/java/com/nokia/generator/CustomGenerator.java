//package com.nokia.generator;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * Created by wow on 2019/6/26.
// */
//public class CustomGenerator {
//
//    public static void main(String[] args) throws InterruptedException {
//        //代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        //全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath+"/src/main/java");//生成目文件的输出目录
//        gc.setAuthor("***");//开发人员
//        gc.setOpen(false);//文件生成后，是否需要打开所在路径
//        gc.setFileOverride(true);//是否覆盖已有文件
//        gc.setEnableCache(false);//是否在xml中添加二级缓存设置
//        gc.setActiveRecord(false);//开启ActiveRecord模式
//        gc.setBaseResultMap(true);//开启BaseResultMap
//        gc.setBaseColumnList(true);//开启BaseColumnList
//        gc.setDateType(DateType.TIME_PACK);//时间类型对应策略
//        gc.setControllerName("%sController");
//        gc.setEntityName("%s");//实体命名方式
//        gc.setServiceName("%sService");
//        gc.setMapperName("%sMapper");
//        gc.setXmlName("%sMapper");
//        gc.setIdType(IdType.AUTO);
//
//        mpg.setGlobalConfig(gc);
//
//        //数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/nokia_fast?useUnicode=true&characterEncoding=UTF-8");
//        dsc.setDriverName("com.mysql.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("root");
//        mpg.setDataSource(dsc);
//
//
//
//        //包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setParent("com.nokia");//父包名，如果为空，将下面子包名必须写全部，否则就只需写子包名
//        pc.setModuleName("");//父包模块名称
//        mpg.setPackageInfo(pc);
//
////        //自定义配置
////        InjectionConfig cfg = new InjectionConfig() {
////            @Override
////            public void initMap() {
////
////            }
////        };
////
////        // 如果模板引擎是 freemarker
////        String templatePath = "/templates/mapper.xml.ftl";
////
////        //自定义输出配置
////        List<FileOutConfig> fileOutConfigs = new ArrayList<>();
////        //自定义配置会被优先输出
////        fileOutConfigs.add(new FileOutConfig(templatePath) {
////            @Override
////            public String outputFile(TableInfo tableInfo) {
////                //自定义输出文件名
////                return projectPath+"/src/main/com/sswwhat/mapper/mapping/"+tableInfo.getEntityName()+"Mapper"+ StringPool.DOT_XML;
////            }
////        });
////
////        cfg.setFileOutConfigList(fileOutConfigs);//配置FileOutConfig指定模板文件、输出文件达到自定义文件生成目的
////        mpg.setCfg(cfg);
//        mpg.setTemplate(new TemplateConfig().setXml(null));
//
//        StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig.setCapitalMode(true)
//                //这里结合Lombok使用，有true,无false
//                .setEntityLombokModel(true)
//                .setNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
//        strategyConfig.setRestControllerStyle(true);
//        strategyConfig.setSuperControllerClass("com.baomidou.ant.common.BaseController");
//        //strategyConfig.setInclude();
//        strategyConfig.setSuperEntityColumns("id");
//        strategyConfig.setControllerMappingHyphenStyle(true);
//        strategyConfig.setTablePrefix(pc.getModuleName()+"_");
//        mpg.setStrategy(strategyConfig);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//
//        mpg.execute();
//    }
//}
