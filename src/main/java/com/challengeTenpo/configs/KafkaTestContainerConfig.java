package com.challengeTenpo.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
@Profile("dev")
public class KafkaTestContainerConfig {

    @Bean
    public KafkaContainer kafkaContainer() {
        KafkaContainer container = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"));
        container.start();
        System.setProperty("KAFKA_BOOTSTRAP_SERVERS", container.getBootstrapServers());
        return container;
    }
}