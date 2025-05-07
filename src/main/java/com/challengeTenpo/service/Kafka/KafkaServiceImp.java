package com.challengeTenpo.service.Kafka;

import com.challengeTenpo.models.DTO.HistorialCalculosDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaServiceImp implements IKafkaService{

    private final KafkaTemplate<String, HistorialCalculosDTO> kafkaTemplate;

    public void send(HistorialCalculosDTO historial) {
        HistorialCalculosDTO message = crearObjeto(historial);

        log.info("Enviando mensaje a Kafka");
        kafkaTemplate.send("call-history-topic", message);
    }

    private HistorialCalculosDTO crearObjeto(HistorialCalculosDTO historial) {
        log.info("Creando objeto mensaje");
        return HistorialCalculosDTO.builder()
                .fecha(historial.getFecha())
                .endpoint(historial.getEndpoint())
                .parametros(historial.getParametros())
                .respuesta(historial.getRespuesta())
                .mensajeError(historial.getMensajeError())
                .error(historial.getMensajeError() != null && !historial.getMensajeError().isEmpty())
                .build();
    }

}
