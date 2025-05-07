package com.challengeTenpo.service.Imp;

import com.challengeTenpo.exceptions.BaseDatosException;
import com.challengeTenpo.exceptions.CalculoDinamicoException;
import com.challengeTenpo.exceptions.FeignApiException;
import com.challengeTenpo.models.DTO.HistorialCalculosDTO;
import com.challengeTenpo.models.Request.CalculoDinamicoRequest;
import com.challengeTenpo.models.Response.CalculoDinamicoResponse;
import com.challengeTenpo.repository.ICalculosRepository;
import com.challengeTenpo.service.ICalculosService;
import com.challengeTenpo.service.FeignApi.IPorcentajeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculosServiceImp implements ICalculosService {


    IPorcentajeService porcentajeService;

    ICalculosRepository calculosRepository;

    @Autowired
    public CalculosServiceImp(IPorcentajeService porcentajeService, ICalculosRepository calculosRepository) {
        this.porcentajeService = porcentajeService;
        this.calculosRepository = calculosRepository;
    }

    @Override
    public CalculoDinamicoResponse CalculoDinamico(CalculoDinamicoRequest request)
            throws CalculoDinamicoException,
                   FeignApiException,
                   BaseDatosException {
        CalculoDinamicoResponse response = null;
        double numeroAleatorioMiddelware;
        log.info("Servicio de Calculo Dinamico");
        try{

            numeroAleatorioMiddelware = porcentajeService.obtenerPorcentaje();

            response.setResultado( realizarCalculo( request, numeroAleatorioMiddelware ));

            graberCalculo();

        } catch (CalculoDinamicoException e) {
            throw new CalculoDinamicoException();
        } catch (FeignApiException e) {
            throw new FeignApiException();
        } catch (BaseDatosException e) {
        throw new BaseDatosException();
    }
        return response;
    }

    private double realizarCalculo(CalculoDinamicoRequest request, double numeroAleatorio){

        return request.getMonto() * (request.getPorcentaje() / numeroAleatorio );
    }

    private double llamadaMiddelware(){
        double porcentaje;
        try{
            log.info("Llamada a APi MiddelWare de Porcentaje");
            porcentaje = porcentajeService.obtenerPorcentaje();
        } catch (FeignApiException e) {
            throw new FeignApiException();
        }
        return porcentaje;
    }

    private void graberCalculo() throws BaseDatosException {
        HistorialCalculosDTO persistencia = new HistorialCalculosDTO();

        try{
            calculosRepository.save(response);
        } catch (BaseDatosException e) {
            throw new BaseDatosException();
        }
    }

}
