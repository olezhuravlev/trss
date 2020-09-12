package com.testref.springsecurity.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Spring application configuration.
 */
@Configuration
@PropertySource("classpath:mysqldb.properties")
public class ApplicationConfig {
    
    @Autowired
    private Environment environment;
    
    @Bean
    public DataSource getDataSource() {
        
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(environment.getProperty("pgsql.driver"));
        basicDataSource.setUrl("pgsql.jdbcUrl");
        basicDataSource.setUsername("pgsql.username");
        basicDataSource.setPassword("pgsql.password");
        
        return basicDataSource;
    }
}
