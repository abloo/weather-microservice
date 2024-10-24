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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1")
@Slf4j
@CrossOrigin(origins = "*")
@Api(value = "Weather API", tags = "Weather") 
public class WeatherController {
    
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @ApiOperation(value = "Get weather by city", notes = "Fetch weather details for the specified city", response = WeatherRestResponse.class, responseContainer = "List")
    @GetMapping("/{city}")
    public List<WeatherRestResponse> getWeatherByCity(
            @ApiParam(value = "Name of the city for which to fetch weather", required = true) 
            @PathVariable String city) {
        return weatherService.getWeatherByCity(city);
    }
}

