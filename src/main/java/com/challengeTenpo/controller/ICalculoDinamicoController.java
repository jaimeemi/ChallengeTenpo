package com.challengeTenpo.controller;

import com.challengeTenpo.models.Response.CalculoDinamicoResponse;
import com.challengeTenpo.models.Response.HistorialCalculosResposne;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/calculo-dinamico")
@SuppressWarnings("unused")
public interface ICalculoDinamicoController {

    @GetMapping("/calcular")
    ResponseEntity<CalculoDinamicoResponse> calcular(int num1, int num2);

    @GetMapping("/historial")
    ResponseEntity<HistorialCalculosResposne> historial();

}
