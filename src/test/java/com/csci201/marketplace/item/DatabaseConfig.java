//package com.csci201.marketplace.item;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//@Configuration
//public class DatabaseConfig {
//
//   @Value("${spring.datasource.url}")
//   private String dbUrl;
//
//   @Value("${spring.datasource.username}")
//   private String dbUsername;
//
//   @Value("${spring.datasource.password}")
//   private String dbPassword;
//
//   @Bean
//   public DataSource dataSource() {
//     return new DriverManagerDataSource(dbUrl, dbUsername, dbPassword);
//   }
//
//   @Bean
//   public JdbcTemplate jdbcTemplate() {
//     return new JdbcTemplate(dataSource());
//   }
//   
//   
//}