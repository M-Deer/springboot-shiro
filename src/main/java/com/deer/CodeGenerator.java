package com.deer;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.deer.component.util.GlobalUtil;

import java.util.Scanner;

/**
 * @ClassName: CodeGenerator
 * @Author: Mr_Deer
 * @Date: 2019/4/23 20:27
 * @Description: MP 代码生成器
 */
public class CodeGenerator {

    public static void main(String[] args) {
        // 创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        // 设置全局配置
        autoGenerator.setGlobalConfig(globalConfig());
        // 设置数据源
        autoGenerator.setDataSource(dataSourceConfig());
        // 设置包配置
        PackageConfig packageConfig = packageConfig();
        autoGenerator.setPackageInfo(packageConfig);
        // 策略配置
        autoGenerator.setStrategy(strategyConfig(packageConfig));
        // 执行
        autoGenerator.execute();
    }

    /**
     * 配置全局配置
     *
     * @return 设置好的全局配置
     */
    private static GlobalConfig globalConfig() {
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 获取当前项目路径
        String projectPat = System.getProperty("user.dir");
        // 设置输出文件路径
        globalConfig.setOutputDir(projectPat + "/src/main/java");
        globalConfig.setAuthor("Mr_Deer");
        globalConfig.setOpen(false);

        // 返回设置
        return globalConfig;
    }

    /**
     * 配置数据源
     *
     * @return 数据源
     */
    private static DataSourceConfig dataSourceConfig() {
        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 设置URL
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1/t_shiro?useUnicode=true&useSSL=false&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true");
        // 设置驱动
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        // 用户名
        dataSourceConfig.setUsername("root");
        // 密码
        dataSourceConfig.setPassword("123456");

        // 返回配置
        return dataSourceConfig;
    }

    /**
     * 包配置
     *
     * @return 包配置
     */
    private static PackageConfig packageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(scanner("模块名"));
        packageConfig.setParent("com.baomidou");

        // 返回配置
        return packageConfig;
    }

    /**
     * 策略配置
     *
     * @param packageConfig 包配置
     * @return 策略
     */
    private static StrategyConfig strategyConfig(PackageConfig packageConfig) {
        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategyConfig.setSuperEntityColumns("id");
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setTablePrefix(packageConfig.getModuleName() + "_");

        // 返回配置
        return strategyConfig;
    }

    /**
     * 获取控制台输入内容
     *
     * @param tip 输入内容
     * @return 结果
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!GlobalUtil.isEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
