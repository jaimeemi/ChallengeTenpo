package com.challengeTenpo.controller.Imp;

import com.challengeTenpo.controller.ICalculoDinamicoController;
import com.challengeTenpo.exceptions.BaseDatosException;
import com.challengeTenpo.exceptions.CalculoDinamicoException;
import com.challengeTenpo.exceptions.SinHistorialCalculosException;
import com.challengeTenpo.models.Request.CalculoDinamicoRequest;
import com.challengeTenpo.models.Response.CalculoDinamicoResponse;
import com.challengeTenpo.models.Response.HistorialCalculosResponse;
import com.challengeTenpo.service.ICalculosService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CalculoDinamicoControllerImp implements ICalculoDinamicoController {

    private final ICalculosService calculosService;
    private final HttpServletRequest peticion;

    @Autowired
    public CalculoDinamicoControllerImp(ICalculosService calculosService, HttpServletRequest peticion) {
        this.calculosService = calculosService;
        this.peticion = peticion;
    }


    @Override
    public ResponseEntity<CalculoDinamicoResponse> calcular(double numero1, double numero2) {
        CalculoDinamicoRequest request = crearCalculoDinamicoRequest(numero1, numero2);
        log.info("Servicio de Calculo Dinamico");
        CalculoDinamicoResponse response = calculosService.CalculoDinamico(request, peticion.getRequestURI());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<HistorialCalculosResponse>> historial() {
        log.info("Servicio de Historial de Calculos");
        List<HistorialCalculosResponse> response = calculosService.historial();
        return ResponseEntity.ok(response);
    }

    private CalculoDinamicoRequest crearCalculoDinamicoRequest(double numero1, double numero2) {
        log.info("Creacion del Objeto Peticion con: Monto: "+numero1+" Porcentaje: {}", numero2);
        CalculoDinamicoRequest request = new CalculoDinamicoRequest();
        request.setNumero1(numero1);
        request.setNumero2(numero2);
        return request;
    }
}
