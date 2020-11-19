package com.csci201.marketplace.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication/*(scanBasePackageClasses = {com.csci201.marketplace.user.service.UserService.class,
		com.csci201.marketplace.user.dao.UserDAO.class,
		com.csci201.marketplace.user.dao.UserDemoDAO.class})*/
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
