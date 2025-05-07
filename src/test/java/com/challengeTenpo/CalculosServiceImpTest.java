package com.challengeTenpo;

import com.challengeTenpo.exceptions.BaseDatosException;
import com.challengeTenpo.exceptions.CalculoDinamicoException;
import com.challengeTenpo.exceptions.FeignApiException;
import com.challengeTenpo.models.Request.CalculoDinamicoRequest;
import com.challengeTenpo.models.Response.CalculoDinamicoResponse;
import com.challengeTenpo.service.FeignApi.IPorcentajeService;
import com.challengeTenpo.service.ICalculosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CalculosServiceImpTest {

    @InjectMocks
    private ICalculosService calculosService;

    @Mock
    private IPorcentajeService porcentajeService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Prueba exitosa del cálculo dinámico")
    void CalculoDinamico_TestOk() throws Exception {
        CalculoDinamicoRequest request = new CalculoDinamicoRequest();
        request.setNumero1(100.0);
        request.setNumero2(200.0);
        String url = "http://example.com";

        CalculoDinamicoResponse response = calculosService.CalculoDinamico(request, url);

        assertEquals(300, response.getResultado());
    }

    @Test
    @DisplayName("Prueba de excepción FeignApiException")
    void CalculoDinamico_TestFailFeignApiException() {
        CalculoDinamicoRequest request = new CalculoDinamicoRequest();
        request.setNumero1(100.0);
        request.setNumero2(200.0);
        String url = "http://example.com";

        when(porcentajeService.obtenerPorcentaje()).thenThrow(new FeignApiException("Error en la llamada a la API"));

        assertThrows(FeignApiException.class, () -> {
            calculosService.CalculoDinamico(request, url);
        });
    }

    @Test
    @DisplayName("Prueba de excepción CalculoDinamicoException")
    void CalculoDinamico_TestFailCalculoDinamicoException() {
        CalculoDinamicoRequest request = new CalculoDinamicoRequest();
        request.setNumero1(100.0);
        request.setNumero2(200.0);
        String url = "http://example.com";

        when(porcentajeService.obtenerPorcentaje()).thenThrow(new CalculoDinamicoException("Error en el cálculo"));

        assertThrows(CalculoDinamicoException.class, () -> {
            calculosService.CalculoDinamico(request, url);
        });
    }

    @Test
    @DisplayName("Prueba de excepción BaseDatosException")
    void CalculoDinamico_TestFailBaseDatosException() {
        CalculoDinamicoRequest request = new CalculoDinamicoRequest();
        request.setNumero1(100.0);
        request.setNumero2(200.0);
        String url = "http://example.com";

        when(porcentajeService.obtenerPorcentaje()).thenThrow(new BaseDatosException("Error en la base de datos"));

        assertThrows(BaseDatosException.class, () -> {
            calculosService.CalculoDinamico(request, url);
        });
    }
}
