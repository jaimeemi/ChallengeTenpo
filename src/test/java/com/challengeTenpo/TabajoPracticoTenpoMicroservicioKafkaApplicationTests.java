package com.challengeTenpo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = "server.port=0")
@EmbeddedKafka(partitions = 1, brokerProperties = {
		"listeners=PLAINTEXT://localhost:9092",
		"port=9092"
})
@ActiveProfiles("test")
public class TabajoPracticoTenpoMicroservicioKafkaApplicationTests {

	@Autowired
	private EmbeddedKafkaBroker embeddedKafka;

	@Test
	void contextLoads() {

	}
}