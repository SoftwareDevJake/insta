package com.insta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages={"com.insta.Dao", "com.insta.service.ArticleService"})
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class InstaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstaApplication.class, args);
	}

}
