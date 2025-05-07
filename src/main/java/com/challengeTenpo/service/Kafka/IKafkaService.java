package com.challengeTenpo.service.Kafka;

import com.challengeTenpo.models.entities.HistorialCalculosEntity;

public interface IKafkaService {

    void send(HistorialCalculosEntity historial);
}
