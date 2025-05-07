package com.challengeTenpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
@EnableScheduling
@EnableAsync
public class TabajoPracticoTenpoMicroservicioKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TabajoPracticoTenpoMicroservicioKafkaApplication.class, args);
	}

}
