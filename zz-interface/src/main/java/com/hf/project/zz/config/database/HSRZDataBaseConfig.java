package com.hf.project.zz.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Description 织造数据库
 * @Author fyq
 * @Date 2021/10/6 9:35
 **/

@Configuration
@MapperScan(basePackages = HSRZDataBaseConfig.HSRZ_PACKAGE, sqlSessionFactoryRef = "HSRZSqlSessionFactory")
public class HSRZDataBaseConfig {


    private static final String MAPPER_PATH = "classpath:mapper/hsrz/*.xml";
    public static final String HSRZ_PACKAGE = "com.hf.project.common.mapper.datadict";


    @Bean(name = "HSRZDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hsrz")
    public DataSource clusterDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "HSRZSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("HSRZDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("com.hf.project.**.entity");
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_PATH));
        return sessionFactory.getObject();
    }


    @Bean(name = "HSRZTransactionManager")
    public DataSourceTransactionManager ZZTransactionManager(@Qualifier("HSRZDataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name="HSRZSqlSessionTemplate")
    public SqlSessionTemplate clusterSqlSessionTemplate(@Qualifier("HSRZSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

