package com.service.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.weather.response.WeatherRestResponse;
import com.service.weather.service.WeatherService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1")
@Slf4j
@CrossOrigin(origins = "*")
public class WeatherController {
    
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @ApiOperation(value = "Get weather by city")
    @GetMapping("/{city}")
    public List<WeatherRestResponse> getWeatherByCity(@PathVariable String city) {
        return  weatherService.getWeatherByCity(city);
    }
}
