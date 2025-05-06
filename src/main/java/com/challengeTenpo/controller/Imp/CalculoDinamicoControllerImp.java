package com.challengeTenpo.controller.Imp;

import com.challengeTenpo.controller.ICalculoDinamicoController;
import com.challengeTenpo.models.Response.CalculoDinamicoResponse;
import com.challengeTenpo.models.Response.HistorialCalculosResposne;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculoDinamicoControllerImp implements ICalculoDinamicoController {




    @Override
    public ResponseEntity<CalculoDinamicoResponse> calcular(int num1, int num2) {
        return null;
    }

    @Override
    public ResponseEntity<HistorialCalculosResposne> historial() {
        return null;
    }
}
