package com.peseer.ops.model;

import javax.sql.DataSource;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;
import com.peseer.ops.config.JFWebConfig;

/**
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 */
public class _JFinalDemoGenerator {

	public static DataSource getDataSource() {
		DruidPlugin c3p0Plugin = JFWebConfig.getDataSource();
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}

	public static DataSource createC3p0Plugin() {
//        String url = XxlConfClient.get("test.jdbc.opsrdd.url", null);
//        String username = XxlConfClient.get("default.jdbc.username", null);
//        String password = XxlConfClient.get("default.jdbc.password",null);

		String url = "jdbc:mysql://106.14.152.222:3306/ops_rdd?Unicode=true&characterEncoding=UTF-8&remarks=true&useInformationSchema=true";

        DruidPlugin c3p0Plugin = new  DruidPlugin(url,"binfo","binfo123");
        c3p0Plugin.start();
        return c3p0Plugin.getDataSource();
	}

	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPackageName = "com.peseer.ops.model.base";
		// base model 文件保存路径
		String baseModelOutputDir = PathKit.getWebRootPath() + "/src/main/java/com/peseer/ops/model/base";

		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "com.peseer.ops.model";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = baseModelOutputDir + "/..";

		// 创建生成器
		Generator gernerator = new Generator(createC3p0Plugin(), new _BaseModelGenerator(baseModelPackageName, baseModelOutputDir), new _ModelGenerator(modelPackageName, baseModelPackageName, modelOutputDir));
		_MetaBuilder metaBuilder = new _MetaBuilder(createC3p0Plugin());
		gernerator.setMetaBuilder(metaBuilder);
		// 添加不需要生成的表名
		gernerator.addExcludedTable("adv");
		// 设置是否在 Model 中生成 dao 对象
		gernerator.setGenerateDaoInModel(true);
		// 设置是否生成字典文件
		gernerator.setGenerateDataDictionary(false);
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
		gernerator.setRemovedTableNamePrefixes("t_");
		// 生成
		gernerator.generate();
	}
}




