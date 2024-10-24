package com.service.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

import com.service.weather.models.WeatherResponse;

@Slf4j
@Component
public class WeatherClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;
    
    @Value("${weather.api.cnt}")
    private String cnt;
    // Apply Circuit Breaker with a fallback method and Retry mechanism
    @Retry(name = "weatherClientRetry", fallbackMethod = "fallbackWeather")
    @CircuitBreaker(name = "weatherClientCircuitBreaker", fallbackMethod = "fallbackWeather")
    public WeatherResponse getWeatherByCity(String city) {
        log.info("Calling Open Weather API with City : {}",city);
        String url = String.format("https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s&cnt=%s", city, apiKey,cnt);
        
        return restTemplate.getForObject(url, WeatherResponse.class);
    }

    // Fallback method for Circuit Breaker
    public WeatherResponse fallbackWeather(String city, Throwable throwable) {
        // Return a default response or handle the error accordingly
        WeatherResponse fallbackResponse = new WeatherResponse();
        // Populate fallbackResponse with default or cached data
        return fallbackResponse;
    }
}
