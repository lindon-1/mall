package com.lindl.mall.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 14:26
 */
@Configuration
@MapperScan(basePackages = "com.lindl.mall.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DbConfig {
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.mall")//application.yml中对应属性的前缀
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource druidDataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(druidDataSource);
        //分页插件
        bean.setPlugins(new Interceptor[]{pageInterceptor()});
        //bean.setPlugins(new Interceptor[]{new MyPluginIntecetper()});
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource")DataSource druidDataSource){
        return new DataSourceTransactionManager(druidDataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory")SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    // 分页拦截器
    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        // 详见 com.github.pagehelper.page.PageParams
        Properties p = new Properties();
//        p.setProperty("offsetAsPageNum", "false");
//        p.setProperty("rowBoundsWithCount", "false");
//        p.setProperty("reasonable", "false");
        // 设置数据库方言 ， 也可以不设置，会动态获取
        p.setProperty("helperDialect", "mysql");
        pageInterceptor.setProperties(p);
        return pageInterceptor;
    }

}
