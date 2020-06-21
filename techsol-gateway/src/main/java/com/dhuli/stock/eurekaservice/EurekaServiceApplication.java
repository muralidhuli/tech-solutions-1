package com.dhuli.stock.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceApplication {
//main boot class for gateway 
	public static void main(String[] args) {
		System.out.println("Main ");
		SpringApplication.run(EurekaServiceApplication.class, args);
	}

}

