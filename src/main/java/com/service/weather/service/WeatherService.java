package com.service.weather.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.service.weather.response.WeatherRestResponse;

@Service
public interface WeatherService {
    List<WeatherRestResponse> getWeatherByCity(String city);
}
