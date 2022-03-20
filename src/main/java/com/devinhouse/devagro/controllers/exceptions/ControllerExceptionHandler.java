package com.devinhouse.devagro.controllers.exceptions;

import com.devinhouse.devagro.services.exceptions.EntityNotFoundException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound //
    (EntityNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Entidade não encontrada");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        return new ResponseEntity<>(
                new JSONObject().put("Campo Obrigatório Inválido ou não preenchido", extractValidationMessage(exception)).toString(),
                HttpStatus.BAD_REQUEST);
    }

    private String extractValidationMessage(MethodArgumentNotValidException exception) {
        String exceptionMessage = exception.getMessage();
        String[] messageParts = exceptionMessage.split(";");
        String finalPart = messageParts[messageParts.length - 1];

        return finalPart.trim().replaceAll("default message \\[|]]", "");
    }
}


