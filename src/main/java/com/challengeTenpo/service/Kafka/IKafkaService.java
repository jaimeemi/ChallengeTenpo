package com.challengeTenpo.service.Kafka;

import com.challengeTenpo.models.DTO.HistorialCalculosDTO;

public interface IKafkaService {

    void send(HistorialCalculosDTO historial);
}
