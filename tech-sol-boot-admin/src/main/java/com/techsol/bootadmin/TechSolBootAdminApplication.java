package com.techsol.bootadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class TechSolBootAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechSolBootAdminApplication.class, args);
	}

}
