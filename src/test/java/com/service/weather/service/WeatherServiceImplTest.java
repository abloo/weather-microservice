package com.service.weather.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.service.weather.exceptions.WeatherServiceException;
import com.service.weather.models.WeatherData;
import com.service.weather.models.WeatherResponse;
import com.service.weather.models.Wind;
import com.service.weather.models.City;
import com.service.weather.models.MainWeatherData;
import com.service.weather.models.Weather;
import com.service.weather.response.WeatherRestResponse;
import com.service.weather.strategy.WeatherConditionContext;

public class WeatherServiceImplTest {

    @Mock
    private WeatherClient weatherClient;

    @Mock
    private WeatherConditionContext weatherConditionContext;

    private WeatherService weatherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        weatherService = new WeatherServiceImpl(weatherClient, weatherConditionContext);
    }

    // Test case for successful weather response
    @Test
    public void testGetWeatherByCity_Success() {
        String city = "New York";

        WeatherResponse weatherResponse = createMockWeatherResponse();
        when(weatherClient.getWeatherByCity(city)).thenReturn(weatherResponse);
        when(weatherConditionContext.evaluateConditions(any(WeatherData.class))).thenReturn("Weather looks fine!");

        List<WeatherRestResponse> result = weatherService.getWeatherByCity(city);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Weather looks fine!", result.get(0).getMessage());
    }

    // Test case when the weather API returns null
    @Test
    public void testGetWeatherByCity_NullResponse() {
        String city = "Los Angeles";
        when(weatherClient.getWeatherByCity(city)).thenReturn(null);

        assertThrows(WeatherServiceException.class, () -> {
            weatherService.getWeatherByCity(city);
        });

    }

    // Test case when the weather API returns a response with null list
    @Test
    public void testGetWeatherByCity_EmptyWeatherList() {
        String city = "Chicago";
        WeatherResponse weatherResponse = new WeatherResponse();
        when(weatherClient.getWeatherByCity(city)).thenReturn(weatherResponse);

        assertThrows(WeatherServiceException.class, () -> {
            weatherService.getWeatherByCity(city);
        });

    }

    @Test
    public void testGetWeatherByCity_NullWeather() {
        String city = "Chicago";
        when(weatherClient.getWeatherByCity(city)).thenReturn(null);

        assertThrows(WeatherServiceException.class, () -> {
            weatherService.getWeatherByCity(city);
        });

    }

    // Test case for exception during API call
    @Test
    public void testGetWeatherByCity_ApiException() {
        String city = "San Francisco";
        when(weatherClient.getWeatherByCity(city)).thenThrow(new RuntimeException("API call failed"));

        assertThrows(WeatherServiceException.class, () -> {
            weatherService.getWeatherByCity(city);
        });

    }

    // Utility method to create a mock WeatherResponse with valid data
    private WeatherResponse createMockWeatherResponse() {
        WeatherData weatherData = new WeatherData();
        Weather weather = new Weather();
        weather.setMain("Clear");
        weather.setIcon("01d");
        weather.setDescription("clear sky");

        weatherData.setWeather(Collections.singletonList(weather));
        weatherData.setMain(new MainWeatherData());
        weatherData.setWind(new Wind());
        weatherData.setVisibility(10000);
        weatherData.setDt_txt("2024-10-24 12:00:00");

        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setList(Collections.singletonList(weatherData));
        weatherResponse.setCity(new City());

        return weatherResponse;
    }

}
