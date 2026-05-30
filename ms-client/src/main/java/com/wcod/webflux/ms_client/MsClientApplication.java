package com.wcod.webflux.ms_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsClientApplication.class, args);
        System.out.println("Hello galaxy 😝");
    }
}
