package com.challengeTenpo.controller.Imp;

import com.challengeTenpo.controller.ICalculoDinamicoController;
import com.challengeTenpo.exceptions.BaseDatosException;
import com.challengeTenpo.exceptions.CalculoDinamicoException;
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
        CalculoDinamicoResponse response = null;
        CalculoDinamicoRequest request = crearCalculoDinamicoRequest(numero1, numero2);
        try{
            log.info("Servicio de Calculo Dinamico");
            response = calculosService.CalculoDinamico(request, peticion.getRequestURI() );
        } catch (CalculoDinamicoException | BaseDatosException e) {
            log.error("Error en el calculo : "+ e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    private CalculoDinamicoRequest crearCalculoDinamicoRequest(double numero1, double numero2){
        log.info("Creacion del Objeto Peticion con: Monto: "+numero1+" Porcentaje: {}", numero2);
        CalculoDinamicoRequest request = new CalculoDinamicoRequest();
        request.setNumero1(numero1);
        request.setNumero2(numero2);
        return request;
    }

    @Override
    public ResponseEntity<HistorialCalculosResponse> historial() {
        log.info("Servicio de Historial de Calculos");
        return null;
    }
}
