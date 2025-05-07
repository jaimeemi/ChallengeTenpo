package com.challengeTenpo.service.Imp;

import com.challengeTenpo.exceptions.BaseDatosException;
import com.challengeTenpo.exceptions.CalculoDinamicoException;
import com.challengeTenpo.exceptions.FeignApiException;
import com.challengeTenpo.models.DTO.HistorialCalculosDTO;
import com.challengeTenpo.models.Request.CalculoDinamicoRequest;
import com.challengeTenpo.models.Response.CalculoDinamicoResponse;
import com.challengeTenpo.models.entities.HistorialCalculosEntity;
import com.challengeTenpo.repository.ICalculosRepository;
import com.challengeTenpo.service.ICalculosService;
import com.challengeTenpo.service.FeignApi.IPorcentajeService;
import com.challengeTenpo.service.Kafka.IKafkaService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculosServiceImp implements ICalculosService {


    private final IPorcentajeService porcentajeService;
    private final ICalculosRepository calculosRepository;
    private final RedisTemplate<String, Double> redisTemplate;
    private final IKafkaService kafkaService;

    private static final String PORCENTAJE_CACHE = "Percentage";
    private static final String CACHE_NOMBRE = "percentageCache";

    @Autowired
    public CalculosServiceImp(IPorcentajeService porcentajeService,
                              ICalculosRepository calculosRepository,
                              RedisTemplate<String, Double> redisTemplate,
                              IKafkaService kafkaService) {
        this.porcentajeService = porcentajeService;
        this.calculosRepository = calculosRepository;
        this.redisTemplate = redisTemplate;
        this.kafkaService = kafkaService;
    }

    @Override
    @Transactional
    @Cacheable(value = CACHE_NOMBRE, unless = "#result == null")
    public CalculoDinamicoResponse CalculoDinamico(CalculoDinamicoRequest request, String url)
            throws CalculoDinamicoException,
                   FeignApiException,
                   BaseDatosException {

        CalculoDinamicoResponse response = null;
        double numeroAleatorioMiddelware;
        double resultado;
        log.info(" Iniciando Servicio de Calculo Dinamico");
        try{

            numeroAleatorioMiddelware = llamadaMiddelware();

            resultado = realizarCalculo(request, numeroAleatorioMiddelware);
            response.setResultado( resultado );

            persistirOperacion(request, resultado, url, null );

        } catch (CalculoDinamicoException e) {
            log.error("Error en el calculo : "+ e.getMessage());
            persistirOperacion( request, 0,url, e.getMessage() );
            throw new CalculoDinamicoException();
        } catch (FeignApiException e) {
            log.error("Error consulta Api MiddelWare : "+ e.getMessage());
            persistirOperacion( request, 0,url, e.getMessage() );
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
        return request.getNumero1() + request.getNumero2() *  numeroAleatorio ;
    }

    private double llamadaMiddelware(){
        double porcentaje;
        try{
            log.info("Llamada a APi MiddelWare para obtener Porcentaje");
            porcentaje = porcentajeService.obtenerPorcentaje();

            log.info("Guardando Ultimo Porcentaje");
            redisTemplate.opsForValue().set(PORCENTAJE_CACHE, porcentaje);

        } catch (FeignApiException e) {
            throw new FeignApiException(e.getMessage() + "y no hay porcentaje en caché");
        }
        return porcentaje;
    }

    private void persistirOperacion(CalculoDinamicoRequest request, double resultado, String url, String mensajeError)
            throws BaseDatosException {
        HistorialCalculosDTO persistencia = new HistorialCalculosDTO(request, resultado,url , mensajeError);


        HistorialCalculosEntity persistenciaEntity = HistorialCalculosDTO.toEntity(persistencia);
        log.info("Persistiendo Calculo en Base de Datos");
        calculosRepository.save( persistenciaEntity );

        kafkaService.send(persistencia);

    }

    @CacheEvict(value = CACHE_NOMBRE, allEntries = true)
    @Scheduled(fixedRate = 30 * 60 * 1000)
    public void limpiarCachePorcentaje() {
        log.info("Limpiando caché de porcentajes");
    }

}
