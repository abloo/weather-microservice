package com.service.weather.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.service.weather.models.WeatherResponse;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WeatherClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherClient weatherClient;

    private final String city = "London";
    private final String apiKey = "dummy-api-key";
    private final String cnt = "5";

    @BeforeEach
    void setUp() {
        weatherClient = new WeatherClient(restTemplate, apiKey, cnt);
    }

    @Test
    void testGetWeatherByCitySuccess() {
        // Given
        WeatherResponse mockResponse = new WeatherResponse();
        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(mockResponse);

        // When
        WeatherResponse response = weatherClient.getWeatherByCity(city);

        // Then
        assertNotNull(response);
        verify(restTemplate).getForObject(anyString(), any(Class.class));
    }

    @Test
    void testGetWeatherByCityFallbackMethod() {
        // Given
        Throwable throwable = new RuntimeException("Test Exception");

        // When
        WeatherResponse response = weatherClient.fallbackWeather(city, throwable);

        // Then
        assertNotNull(response);
        // Check if fallback response is a default WeatherResponse
    }
}
