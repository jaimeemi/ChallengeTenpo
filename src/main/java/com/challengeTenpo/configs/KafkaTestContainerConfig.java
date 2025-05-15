package com.challengeTenpo.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;


@Configuration
@Profile("test")
public class KafkaTestContainerConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public KafkaContainer kafkaContainer() {
        return new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"))
                .withReuse(true); // Permite reutilizar el contenedor entre pruebas
    }
}