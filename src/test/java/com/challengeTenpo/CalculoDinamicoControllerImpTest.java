package com.challengeTenpo;

import com.challengeTenpo.controller.Imp.CalculoDinamicoControllerImp;
import com.challengeTenpo.exceptions.BaseDatosException;
import com.challengeTenpo.exceptions.CalculoDinamicoException;
import com.challengeTenpo.models.Request.CalculoDinamicoRequest;
import com.challengeTenpo.models.Response.CalculoDinamicoResponse;
import com.challengeTenpo.service.ICalculosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CalculoDinamicoControllerImpTest {

    @InjectMocks
    private CalculoDinamicoControllerImp controller;

    @Mock
    private ICalculosService calculosService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Prueba exitosa del cálculo")
    void calcular_TestOk() throws Exception {
        double numero1 = 100;
        double numero2 = 200;
        CalculoDinamicoResponse response = new CalculoDinamicoResponse();
        response.setResultado(300.0);

        when(calculosService.CalculoDinamico(new CalculoDinamicoRequest(numero1, numero2), "someUrl"))
                .thenReturn(response);

        ResponseEntity<CalculoDinamicoResponse> result = controller.calcular(numero1, numero2);
        assertEquals(300, result.getBody().getResultado());
    }

    @Test
    @DisplayName("Prueba de excepción CalculoDinamicoException")
    void calcular_TestFailCalculoDinamicoException() throws BaseDatosException {
        double numero1 = 100;
        double numero2 = 200;

        when(calculosService.CalculoDinamico(new CalculoDinamicoRequest(numero1, numero2), "someUrl"))
                .thenThrow(new CalculoDinamicoException("Error en el cálculo"));

        assertThrows(CalculoDinamicoException.class, () -> {
            controller.calcular(numero1, numero2);
        });
    }

    @Test
    @DisplayName("Prueba de excepción BaseDatosException")
    void calcular_TestFailBaseDatosException() throws BaseDatosException {
        double numero1 = 100;
        double numero2 = 200;

        when(calculosService.CalculoDinamico(new CalculoDinamicoRequest(numero1, numero2), "someUrl"))
                .thenThrow(new BaseDatosException("Error en la base de datos"));

        assertThrows(BaseDatosException.class, () -> {
            controller.calcular(numero1, numero2);
        });
    }
}
