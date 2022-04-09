package com.hf.project.zz.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @Description 织造数据库
 * @Author fyq
 * @Date 2021/10/6 9:35
 **/

@Configuration
@MapperScan(basePackages = {ZZDataBaseConfig.COMMON_PACKAGE, ZZDataBaseConfig.ZZ_PACKAGE}, sqlSessionFactoryRef = "ZZSqlSessionFactory")
public class ZZDataBaseConfig {


    private static final String MAPPER_PATH = "classpath:mapper/zz/*.xml";
    static final String COMMON_PACKAGE = "com.hf.project.common.mapper.common";
    static final String ZZ_PACKAGE = "com.hf.project.zz.mapper.zz";


    @Bean(name = "ZZDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.zz")
    @Primary
    public DataSource clusterDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "ZZSqlSessionFactory")
    @Primary
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("ZZDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_PATH));
        sessionFactory.setTypeAliasesPackage("com.hf.project.**.entity");
        Objects.requireNonNull(sessionFactory.getObject()).getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        return sessionFactory.getObject();
    }


    @Bean(name = "ZZTransactionManager")
    @Primary
    public DataSourceTransactionManager ZZTransactionManager(@Qualifier("ZZDataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name="ZZSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate clusterSqlSessionTemplate(@Qualifier("ZZSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

