package com.service.weather.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class WeatherExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public void handleHttpClientErrorException(HttpClientErrorException ex) {
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Error fetching weather data: " + ex.getMessage(), ex);
    }
}
