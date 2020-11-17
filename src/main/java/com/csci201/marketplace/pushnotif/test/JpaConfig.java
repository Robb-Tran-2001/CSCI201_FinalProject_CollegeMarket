package com.csci201.marketplace.pushnotif.test;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootConfiguration
public class JpaConfig {
 
//    public static void main(String[] args) {
//        SpringApplication.run(JpaConfig.class, args);
//    }
	@Autowired Environment env;
	
	@Bean
	public DataSource setDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/marketplace?createDatabaseIfNotExist=true");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("root");
        return dataSourceBuilder.build();
	}

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/marketplace?createDatabaseIfNotExist=true");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("root");
        return dataSourceBuilder.build();
    }
//	@Primary
//	@Bean
//	public DataSource customDataSource() {
//
//	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	    dataSource.setDriverClassName(env.getProperty("custom.datasource.com.mysql.jdbc.Driver"));
//	    dataSource.setUrl(env.getProperty("custom.datasource.jdbc:mysql://localhost:3306/marketplace?createDatabaseIfNotExist=true"));
//	    dataSource.setUsername(env.getProperty("custom.datasource.root"));
//	    dataSource.setPassword(env.getProperty("custom.datasource.root"));
//
//	    return dataSource;
//
//	}
}