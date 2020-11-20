package com.csci201.marketplace.user;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.csci201.marketplace.user.service.UserService;

@SpringBootApplication
public class UserApplication {
	
//	@Autowired
//	private UserService userService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
