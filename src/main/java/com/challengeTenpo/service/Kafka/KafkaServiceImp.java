package com.challengeTenpo.service.Kafka;

import com.challengeTenpo.models.DTO.HistorialCalculosDTO;
import com.challengeTenpo.models.entities.HistorialCalculosEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaServiceImp implements IKafkaService{

    private final KafkaTemplate<String, HistorialCalculosEntity> kafkaTemplate;

    public void send(HistorialCalculosEntity historial) {
        log.info("Enviando mensaje a Kafka");
        kafkaTemplate.send("call-history-topic", historial);
    }

}
