package com.dvlms;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CodeGenerator {
    /**
     * 代码生成器
     */


    public static void main(String[] args) {
        List<String> tables = new ArrayList<>();
//        tables.add("BussinessInfo");
//        tables.add("ClientContract");
//        tables.add("ClientInfo");
//        tables.add("EntityInfo");
//        tables.add("GuarantorCircle");
//        tables.add("GuarantorInfo");
//        tables.add("LoanInfo");
        tables.add("UserRole");

        FastAutoGenerator.create("jdbc:mysql://47.106.222.90:3306/db_system?useUnicode=true&useSSL=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai", "root", "mysql")
                .globalConfig(builder -> {
                    builder.author("zhb")               //作者
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")    //输出路径(写到java目录)
                            .enableSwagger()           //开启swagger
                            .commentDate("yyyy-MM-dd")
                            .fileOverride();

                })
                .packageConfig(builder -> {
                    builder.parent("com.dvlms")
                            .entity("entity")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
                            .addTablePrefix("p_")
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            .enableBaseResultMap()  //生成通用的resultMap
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}

