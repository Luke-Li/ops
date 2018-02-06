package com.peseer.ops.config;
import org.beetl.ext.jfinal.BeetlRenderFactory;

import com.alibaba.druid.filter.stat.StatFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.tx.TxByActionKeyRegex;
import com.jfinal.plugin.activerecord.tx.TxByActionKeys;
import com.jfinal.plugin.activerecord.tx.TxByMethodRegex;
import com.jfinal.plugin.activerecord.tx.TxByMethods;
import com.jfinal.plugin.druid.DruidPlugin;
import com.peseer.ops.interceptor.GlobalInterceptor;
import com.peseer.ops.model._MappingKit;
import com.peseer.ops.model.account.LoginInfo;
import com.peseer.ops.model.account.UserInfo;
public class JFWebConfig extends JFinalConfig {
    /**
     * 供Shiro插件使用。
     */
	public static final String URL_03 = "jdbc:mysql://101.132.188.47:3306/%s?Unicode=true&characterEncoding=UTF-8&remarks=true&useInformationSchema=true&zeroDateTimeBehavior=convertToNull";
	public static final String URL_db = "jdbc:mysql://106.14.219.225:3306/%s?Unicode=true&characterEncoding=UTF-8&remarks=true&useInformationSchema=true&zeroDateTimeBehavior=convertToNull";
	Routes routes;
    @Override
    public void configConstant(Constants me) {
        me.setError404View("/common/404.html");
        me.setError500View("/common/500.html");
        // 设定Beetl
        me.setMainRenderFactory(new BeetlRenderFactory());
        // 设定为开发者模式
        me.setDevMode(true);
        me.setEncoding("UTF-8");
        //SqlReporter.setLog(true);
    }
    @Override
    public void configRoute(Routes me) {
        AutoBindRoutes autoBindRoutes = new AutoBindRoutes();
        autoBindRoutes.includeAllJarsInLib(false);
        me.add(autoBindRoutes);
    }
    private static StatFilter getStatFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        return statFilter;
    }
    public static DruidPlugin getDataSource(){
    	String db = "ops_rdd";
    	return getNode03DataSource(db);
    }
    public static DruidPlugin getNode03DataSource(String db){
    	// mysql
        String username = "root";
        String password = "2wsx3EDC!QAZ";
        String driverClass = "com.mysql.jdbc.Driver";
        String filters = "stat,wall";
        String url = String.format(URL_03,db);
        // mysql 数据源
        DruidPlugin dsMysql = new DruidPlugin(url, username, password, driverClass, filters);
        dsMysql.addFilter(getStatFilter());
        dsMysql.setMaxActive(200);
        dsMysql.setValidationQuery(" SELECT 1 ");
        dsMysql.setTestOnBorrow(true);
        return dsMysql;
    }
    public static DruidPlugin peseerDataSource(String db){
    	// mysql
    	String url = String.format(URL_db,db);
        String username = "root";
        String password = "2wsx3EDC!QAZ";
        String driverClass = "com.mysql.jdbc.Driver";
        String filters = "stat,wall";
        // mysql 数据源
        DruidPlugin dsMysql = new DruidPlugin(url, username, password, driverClass, filters);
        dsMysql.addFilter(getStatFilter());
        dsMysql.setMaxActive(200);
        dsMysql.setValidationQuery(" SELECT 1 ");
        dsMysql.setTestOnBorrow(true);
        return dsMysql;
    }
    @Override
    public void configPlugin(Plugins me) {
    	DruidPlugin dsMysqlMain = getDataSource();
        me.add(dsMysqlMain);
        ActiveRecordPlugin arpMysql = new ActiveRecordPlugin(dsMysqlMain);
        arpMysql.setContainerFactory(new CaseInsensitiveContainerFactory(true));
        arpMysql.setShowSql(true);
        // 所有表的映射配置在 MappingKit 中搞定
        _MappingKit.mapping(arpMysql);
        me.add(arpMysql);
        DruidPlugin dsMysqlQxb = getNode03DataSource("qxb");
        me.add(dsMysqlQxb);
        ActiveRecordPlugin arpMysqlQxb = new ActiveRecordPlugin("qxb",dsMysqlQxb);
        arpMysqlQxb.setContainerFactory(new CaseInsensitiveContainerFactory(true));
        arpMysqlQxb.setShowSql(true);
        // 所有表的映射配置在 MappingKit 中搞定
        //_MappingKit.mapping(arpMysql);
        me.add(arpMysqlQxb);
        //peseer_login
        DruidPlugin dsMysqlLogin = peseerDataSource("peseer_login");
        me.add(dsMysqlLogin);
        ActiveRecordPlugin arpMysqlLogin = new ActiveRecordPlugin("userInfo",dsMysqlLogin);
        arpMysqlLogin.setContainerFactory(new CaseInsensitiveContainerFactory(true));
        arpMysqlLogin.setShowSql(true);
        // 所有表的映射配置在 MappingKit 中搞定
//		_MappingKit.mapping(arpMysql);
        me.add(arpMysqlLogin);
        arpMysqlLogin.addMapping("login_info", "id", LoginInfo.class);
        arpMysqlLogin.addMapping("user_info", "uid", UserInfo.class);

        //peseer_online
        DruidPlugin peseerOnline = peseerDataSource("peseer_online");
        me.add(peseerOnline);
        ActiveRecordPlugin arpOnline = new ActiveRecordPlugin("peseerOnline",peseerOnline);
        arpOnline.setContainerFactory(new CaseInsensitiveContainerFactory(true));
        arpOnline.setShowSql(true);
        // 所有表的映射配置在 MappingKit 中搞定
//		_MappingKit.mapping(arpMysql);
        me.add(arpOnline);
        com.peseer.ops.model.online._MappingKit.mapping(arpOnline);
//        arpOnline.addMapping("daily_event_75", "id", DailyEvent75.class);

    }

    @Override
    public void configInterceptor(Interceptors me) {
    	me.addGlobalActionInterceptor(new GlobalInterceptor());
        //me.addGlobalServiceInterceptor(new J2CacheByMethodRegex("(.*get.*|.*find.*|.*list.*|.*search.*)","(drop.*|.*update.*|.*delete.*)"));
    	me.add(new TxByMethodRegex("(.*save.*|.*update.*)"));
    	me.add(new TxByMethods("save", "update"));
    	me.add(new TxByActionKeyRegex("/trans.*"));
    	me.add(new TxByActionKeys("/tx/save", "/tx/update"));
    }
    @Override
    public void afterJFinalStart() {
        super.afterJFinalStart();
//        EntFilter.init();
    }
    @Override
    public void configHandler(Handlers me) {
        me.add(new ContextPathHandler("base"));
        me.add(new FakeStaticHandler());
    }
}