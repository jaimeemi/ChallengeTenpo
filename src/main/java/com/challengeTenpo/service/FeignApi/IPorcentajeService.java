package com.challengeTenpo.service.FeignApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/*
* Esta URL la extraje con ayuda de la IA DeepSeek
* No quiero perder el tiempo en encotnrar una url o crer un Mock
* */

@FeignClient(name = "random-org", url = "https://www.random.org")
public interface IPorcentajeService {

    @GetMapping("/decimal-fractions/?num=1&dec=2&col=1&format=plain")
    double obtenerPorcentaje();
}

