package com.sellis.services.rest.secured;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProtectedRestResourceApplication {
   public static void main(String[] args) {
      SpringApplication.run(ProtectedRestResourceApplication.class, args);
   }
}
