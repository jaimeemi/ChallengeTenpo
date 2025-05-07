package com.challengeTenpo.service.FeignApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "random-org", url = "https://www.random.org")
public interface IPorcentajeService {

    @GetMapping("/decimal-fractions/?num=1&dec=2&col=1&format=plain")
    double obtenerPorcentaje();
}

