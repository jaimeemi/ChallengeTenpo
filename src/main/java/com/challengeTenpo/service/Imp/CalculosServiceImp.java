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
import jakarta.transaction.Transactional;
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
    @Transactional
    public CalculoDinamicoResponse CalculoDinamico(CalculoDinamicoRequest request)
            throws CalculoDinamicoException,
                   FeignApiException,
                   BaseDatosException {

        CalculoDinamicoResponse response = null;
        double numeroAleatorioMiddelware;
        log.info("Servicio de Calculo Dinamico");
        try{

            numeroAleatorioMiddelware = llamadaMiddelware();

            double resultado = realizarCalculo(request, numeroAleatorioMiddelware);
            response.setResultado( resultado );

            graberCalculo(request, resultado, null );

        } catch (CalculoDinamicoException e) {
            log.error("Error en el calculo : "+ e.getMessage());
            graberCalculo( request, 0, e.getMessage() );
            throw new CalculoDinamicoException();
        } catch (FeignApiException e) {
            log.error("Error consulta Api MiddelWare : "+ e.getMessage());
            graberCalculo( request, 0, e.getMessage() );
            throw new FeignApiException();
        } catch (BaseDatosException e) {
            log.error("Error Proceso a Base de Datos : "+ e.getMessage());
            throw new BaseDatosException();
        }
        return response;
    }

    private double realizarCalculo(CalculoDinamicoRequest request, double numeroAleatorio){
        log.info("Realizando Calculo");
        if(numeroAleatorio == 0) {
            throw new CalculoDinamicoException("El porcentaje no puede ser cero");
        }
        return request.getMonto() * (request.getPorcentaje() / numeroAleatorio );
    }

    private double llamadaMiddelware(){
        double porcentaje;
        try{
            log.info("Llamada a APi MiddelWare para obtene Porcentaje");
            porcentaje = porcentajeService.obtenerPorcentaje();
        } catch (FeignApiException e) {
            throw new FeignApiException();
        }
        return porcentaje;
    }

    private void graberCalculo(CalculoDinamicoRequest request, double resultado, String mensajeError)
            throws BaseDatosException {
        HistorialCalculosDTO persistencia = new HistorialCalculosDTO();

        try{
            log.info("Persistiendo Calculo en Base de Datos");
            calculosRepository.save(response);
        } catch (BaseDatosException e) {
            throw new BaseDatosException();
        }
    }

}
