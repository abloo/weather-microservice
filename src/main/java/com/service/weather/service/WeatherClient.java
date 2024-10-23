package com.service.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.service.weather.models.WeatherResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WeatherClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.cnt}")
    private String cnt;

    public WeatherResponse getWeatherByCity(String city) {
        log.info("Calling Open Weather API with City : {}",city);
        String url = String.format("https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s&cnt=%s", city, apiKey,cnt);
        
        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}
