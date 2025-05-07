package com.challengeTenpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TabajoPracticoTenpoMicroservicioKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TabajoPracticoTenpoMicroservicioKafkaApplication.class, args);
	}

}
