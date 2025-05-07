package com.challengeTenpo.repository.mappers;

import com.challengeTenpo.models.Response.HistorialCalculosResponse;
import com.challengeTenpo.models.entities.HistorialCalculosEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistorialCalculosMapper {
    HistorialCalculosMapper INSTANCE = Mappers.getMapper(HistorialCalculosMapper.class);

    HistorialCalculosResponse entityToResponse(HistorialCalculosEntity entity);
    List<HistorialCalculosResponse> entitiesToResponses(List<HistorialCalculosEntity> entities);
}