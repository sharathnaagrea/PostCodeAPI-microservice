package com.postcode.au.api.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.postcode.au.api.entity.APIStatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public APIStatus handleBadRequestException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(violation.getMessage() + "\n");
        }
        return new APIStatus("API-400", strBuilder.toString());
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIStatus resourceNotFoundException(Exception ex, WebRequest req) {
        return new APIStatus("API-404", "Resource Not Found");
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public APIStatus unknownException(Exception ex, WebRequest req) {
        return new APIStatus("API-500", "Server Error");
    }

    @ExceptionHandler(value = { AuthenticationException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public APIStatus authenticationException(Exception ex, WebRequest req) {
        return new APIStatus("API-401", "Unauthorized");
    }

}
