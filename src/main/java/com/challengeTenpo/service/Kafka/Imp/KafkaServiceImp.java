package com.challengeTenpo.service.Kafka.Imp;


import com.challengeTenpo.models.entities.HistorialCalculosEntity;
import com.challengeTenpo.repository.ICalculosRepository;
import com.challengeTenpo.service.Kafka.IKafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaServiceImp implements IKafkaService {

    private static final String TOPIC = "historial-calculations";
    private static final String TOPIC_HISTORIAL = "historial-calculations";

    private final KafkaTemplate<String, HistorialCalculosEntity> kafkaTemplate;
    private final ICalculosRepository historialRepository;

    @Autowired
    public KafkaServiceImp(KafkaTemplate<String, HistorialCalculosEntity> kafkaTemplate,
                           ICalculosRepository historialRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.historialRepository = historialRepository;
    }

    @Override
    @Async
    public void send(HistorialCalculosEntity historial) {
        kafkaTemplate.send(TOPIC, historial);
    }

    @KafkaListener(topics = TOPIC, groupId = "call-history-group")
    public void consume(HistorialCalculosEntity historial) {
        log.info("Enviando mensaje a Kafka");
        kafkaTemplate.send(TOPIC_HISTORIAL, historial);
    }


}
