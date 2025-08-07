package com.springEngineerSubha.jurnalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JurnalAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JurnalAppApplication.class, args);
		ConfigurableEnvironment environment = context.getEnvironment();
		System.out.println(environment.getActiveProfiles()[0]);  //prinnt currect enviroment
	}
	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory ){
		return new MongoTransactionManager(dbFactory);
	}

		//PlatformTransactionManager
		//MongoTransactionManager
	//MongoDatabaseFactory Helps to connect database
}
