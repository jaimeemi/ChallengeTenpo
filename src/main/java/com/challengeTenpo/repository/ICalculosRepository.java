package com.challengeTenpo.repository;

import com.challengeTenpo.models.DTO.HistorialCalculosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICalculosRepository extends JpaRepository<HistorialCalculosDTO, Long> {
}
