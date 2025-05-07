package com.challengeTenpo.service.Imp;

import com.challengeTenpo.exceptions.CalculoDinamicoException;
import com.challengeTenpo.exceptions.FeignApiException;
import com.challengeTenpo.models.Request.CalculoDinamicoRequest;
import com.challengeTenpo.models.Response.CalculoDinamicoResponse;
import com.challengeTenpo.service.ICalculosService;
import com.challengeTenpo.service.FeignApi.IPorcentajeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculosServiceImp implements ICalculosService {


    IPorcentajeService porcentajeService;

    @Autowired
    public CalculosServiceImp(IPorcentajeService porcentajeService) {
        this.porcentajeService = porcentajeService;
    }

    @Override
    public CalculoDinamicoResponse CalculoDinamico(CalculoDinamicoRequest request) throws CalculoDinamicoException {
        CalculoDinamicoResponse response = null;
        try{
            log.info("Servicio de Calculo Dinamico");
            response.setResultado(request.getMonto() * (request.getPorcentaje() / porcentajeService.obtenerPorcentaje() ));

        } catch (CalculoDinamicoException e) {
            throw new CalculoDinamicoException();
        } catch (FeignApiException e) {
            throw new FeignApiException();
        }
        return response;
    }

}
