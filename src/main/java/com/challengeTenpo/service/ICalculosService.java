package com.challengeTenpo.service;

import com.challengeTenpo.exceptions.BaseDatosException;
import com.challengeTenpo.exceptions.CalculoDinamicoException;
import com.challengeTenpo.exceptions.FeignApiException;
import com.challengeTenpo.models.Request.CalculoDinamicoRequest;
import com.challengeTenpo.models.Response.CalculoDinamicoResponse;
import org.springframework.stereotype.Service;

@Service
public interface ICalculosService {

    CalculoDinamicoResponse CalculoDinamico (CalculoDinamicoRequest request, String url) throws CalculoDinamicoException, FeignApiException, BaseDatosException;

}
