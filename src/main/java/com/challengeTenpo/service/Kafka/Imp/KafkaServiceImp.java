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
        try {
            kafkaTemplate.send(TOPIC, historial).get();
            log.info("Mensaje enviado correctamente");
        } catch (Exception e) {
            log.error("Error durante el envio del mensaje: {}", historial, e.getMessage());
        }
    }

    @KafkaListener(topics = TOPIC, groupId = "call-history-group")
    public void consume(HistorialCalculosEntity historial) {
        log.info("Enviando mensaje a Kafka, mensaje: {}", historial);
        kafkaTemplate.send(TOPIC, historial);
    }

}
