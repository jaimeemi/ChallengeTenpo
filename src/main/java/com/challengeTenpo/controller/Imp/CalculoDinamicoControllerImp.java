package com.challengeTenpo.controller.Imp;

import com.challengeTenpo.controller.ICalculoDinamicoController;
import com.challengeTenpo.exceptions.CalculoDinamicoException;
import com.challengeTenpo.models.Request.CalculoDinamicoRequest;
import com.challengeTenpo.models.Response.CalculoDinamicoResponse;
import com.challengeTenpo.models.Response.HistorialCalculosResposne;
import com.challengeTenpo.service.ICalculosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CalculoDinamicoControllerImp implements ICalculoDinamicoController {

    ICalculosService calculosService;

    @Autowired
    public CalculoDinamicoControllerImp(ICalculosService calculosService) {
        this.calculosService = calculosService;
    }


    @Override
    public ResponseEntity<CalculoDinamicoResponse> calcular(double monto, double porcentaje) {
        CalculoDinamicoResponse response = null;
        CalculoDinamicoRequest request = crearCalculoDinamicoRequest(monto, porcentaje);
        try{
            log.info("Servicio de Calculo Dinamico");
            response = calculosService.CalculoDinamico(request);
        } catch (CalculoDinamicoException e) {
            log.error("Error en el calculo : "+ e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    private CalculoDinamicoRequest crearCalculoDinamicoRequest(double monto, double porcentaje){
        log.info("Creacion del Objeto Peticion con: Monto: {} Porcentaje: {}", monto, porcentaje);
        CalculoDinamicoRequest request = new CalculoDinamicoRequest();
        request.setMonto(monto);
        request.setPorcentaje(porcentaje);
        return request;
    }

    @Override
    public ResponseEntity<HistorialCalculosResposne> historial() {
        log.info("Servicio de Historial de Calculos");
        return null;
    }
}
