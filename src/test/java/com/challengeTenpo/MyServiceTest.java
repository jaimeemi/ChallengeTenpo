package com.challengeTenpo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class MyServiceTest {

    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void testWithoutRealKafka() {
        // Tu lógica de test aquí
    }
}