package cn.felord.kono.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * 代码生成器配置
 *
 * @author Dax
 * @since 10 :39  2018/9/9
 */
public class CodeGenerator {
    private String dbUrl;
    private String userName;
    private String password;
    private String dir;
    private String xmlDir;
    private String packageName;

    private CodeGenerator() {
    }

    /**
     * The type Config builder.
     */
    public static class ConfigBuilder {

        private String dbUrl;
        private String userName;
        private String password;
        private String dir;
        private String xmlDir;
        private String packageName;


        /**
         * Db url config builder.
         *
         * @param dbUrl the db url
         * @return the config builder
         */
        public ConfigBuilder dbUrl(final String dbUrl) {
            this.dbUrl = dbUrl;
            return this;
        }

        /**
         * User name config builder.
         *
         * @param userName the user name
         * @return the config builder
         */
        public ConfigBuilder userName(final String userName) {
            this.userName = userName;
            return this;
        }

        /**
         * Password config builder.
         *
         * @param password the password
         * @return the config builder
         */
        public ConfigBuilder password(final String password) {
            this.password = password;
            return this;
        }

        /**
         * Dir config builder.
         *
         * @param dir the dir
         * @return the config builder
         */
        public ConfigBuilder dir(final String dir) {
            this.dir = dir;
            return this;
        }

        /**
         * Dir config builder.
         *
         * @param xmlDir the dir
         * @return the config builder
         */
        public ConfigBuilder xmlDir(final String xmlDir) {
            this.xmlDir = xmlDir;
            return this;
        }

        /**
         * Package name config builder.
         *
         * @param packageName the package name
         * @return the config builder
         */
        public ConfigBuilder packageName(final String packageName) {
            this.packageName = packageName;
            return this;
        }

        /**
         * Build code generator.
         *
         * @return the code generator
         */
        public CodeGenerator build() {
            CodeGenerator generator = new CodeGenerator();

            generator.dbUrl = Optional.of(this.dbUrl).get();
            generator.userName = Optional.of(this.userName).get();
            generator.password = Optional.of(this.password).get();
            generator.dir = Optional.of(this.dir).get();
            generator.xmlDir = Optional.of(this.xmlDir).get();
            generator.packageName = Optional.of(this.packageName).get();
            return generator;
        }
    }


    /**
     * Code.
     *
     * @param tableNames the table names
     */
    public void code(String... tableNames) {
        codingMysql(true, false, true, this.dbUrl, this.userName, this.password, this.dir, this.xmlDir, this.packageName, tableNames);
    }

    /**
     * 生成器核心部分
     *
     * @param serviceNameStartWithI 是否前缀I
     * @param createController      是否生成controller
     * @param useLombok             是否使用 lombok
     * @param dbUrl                 数据库连接
     * @param username              用户名称
     * @param password              密码
     * @param outDir                输出目录
     * @param xmlDir                xml 文件目录
     * @param packageName           包路径
     * @param tableNames            表名称
     */
    private static void codingMysql(boolean serviceNameStartWithI,
                                    boolean createController,
                                    boolean useLombok,
                                    String dbUrl,
                                    String username,
                                    String password,
                                    String outDir,
                                    String xmlDir,
                                    String packageName,
                                    String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        数据库类型 这里使用 mysql
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername(username)
                .setPassword(password)
//                驱动名称  这里使用mysql
                .setDriverName("com.mysql.jdbc.Driver");

        // 自定义xml输出路径
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
//        你也可以定制 xml 的模板
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义xml文件的路径
                return xmlDir + "/mapper/" + tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);


//        策略配置项
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(false)
//                是否使用 lombok
                .setEntityLombokModel(useLombok)
//                下划线转驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                //修改替换成你需要的表名，多个表名传数组
                .setInclude(tableNames);
//        使用 AR 模式
        config.setActiveRecord(true)
//                设置头注释的 author
                .setAuthor("system")
//                项目输出路径
                .setOutputDir(outDir)
//                是否覆盖已经生成的同名文件
                .setFileOverride(true)
//                雪花算法生成id
                .setIdType(IdType.ASSIGN_ID)
//                是否使用缓存
                .setEnableCache(false)
//                是否生成 xml 中的 基础 resultMap
                .setBaseResultMap(true);
        if (!serviceNameStartWithI) {
//            Service 层的 通用格式后缀
            config.setServiceName("%sService");
        }
//             实体类包名
        PackageConfig packageConfig = new PackageConfig().setParent(packageName).setEntity("entity");
        TemplateConfig templateConfig = new TemplateConfig().setXml(null);
//        这里选择不生成 controller  实际上 生成的大多不符合我们需要  到服务层就行了
        if (!createController) {
            templateConfig.setController(null);
        }
//        整合起来运行
        new AutoGenerator()
                .setGlobalConfig(config)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setCfg(cfg)
                .setTemplate(templateConfig)
                .execute();
    }

}
