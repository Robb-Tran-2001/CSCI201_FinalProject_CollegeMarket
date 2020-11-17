package com.csci201.marketplace.item;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableJpaRepositories("com.csci201.marketplace.item.ItemDAO")
public class JpaConfig {
 
//    public static void main(String[] args) {
//        SpringApplication.run(JpaConfig.class, args);
//    }
 
//    @Bean
//    public DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.url("jdbc:mysql://localhost:3306/marketplace?createDatabaseIfNotExist=true");
//        dataSourceBuilder.username("root");
//        dataSourceBuilder.password("root");
//        return dataSourceBuilder.build();
//    }

}