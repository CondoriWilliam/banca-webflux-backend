package com.wcod.webflux.ms_bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBffApplication.class, args);
	}

}
