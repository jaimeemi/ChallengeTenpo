package com.challengeTenpo.controller.GlobalControllerExp;

import com.challengeTenpo.exceptions.BaseDatosException;
import com.challengeTenpo.exceptions.CalculoDinamicoException;
import com.challengeTenpo.exceptions.FeignApiException;
import com.challengeTenpo.exceptions.SinHistorialCalculosException;
import com.challengeTenpo.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CalculoDinamicoException.class, BaseDatosException.class,
            FeignApiException.class, SinHistorialCalculosException.class})
    public ResponseEntity<ErrorResponse> handleBusinessExceptions(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                ex.getClass().getSimpleName(),
                getExceptionMessage(ex),
                request.getDescription(false),
                getErrorCode(ex)
        );
        return new ResponseEntity<>(errorResponse, determineHttpStatus(ex));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handleValidationExceptions(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                "Validation Error",
                ex.getMessage(),
                request.getDescription(false),
                "VALIDATION_ERROR"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private HttpStatus determineHttpStatus(RuntimeException ex) {
        if (ex instanceof FeignApiException) {
            return HttpStatus.SERVICE_UNAVAILABLE;
        } else if (ex instanceof BaseDatosException) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof SinHistorialCalculosException) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.BAD_REQUEST;
    }

    private String getExceptionMessage(RuntimeException ex) {
        if (ex instanceof CalculoDinamicoException) {
            return ((CalculoDinamicoException) ex).getMensaje();
        } else if (ex instanceof FeignApiException) {
            return ((FeignApiException) ex).getMensaje();
        } else if (ex instanceof SinHistorialCalculosException) {
            return ((SinHistorialCalculosException) ex).getMensaje();
        } else if (ex instanceof BaseDatosException) {
            return ((BaseDatosException) ex).getMensaje();
        }
        return ex.getMessage();
    }

    private String getErrorCode(RuntimeException ex) {
        if (ex instanceof CalculoDinamicoException) {
            return ((CalculoDinamicoException) ex).getCodigoError();
        } else if (ex instanceof FeignApiException) {
            return ((FeignApiException) ex).getCodigoError();
        } else if (ex instanceof SinHistorialCalculosException) {
            return ((SinHistorialCalculosException) ex).getCodigoError();
        } else if (ex instanceof BaseDatosException) {
            return ((BaseDatosException) ex).getCodigoError();
        }
        return "UNKNOWN_ERROR";
    }
}