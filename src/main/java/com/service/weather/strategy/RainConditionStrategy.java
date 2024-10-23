package com.service.weather.strategy;

import org.springframework.stereotype.Component;

import com.service.weather.models.WeatherData;

@Component
public class RainConditionStrategy implements WeatherConditionStrategy {
    
    @Override
    public String checkCondition(WeatherData data) {
        boolean rainPredicted = data.getWeather().stream()
                .anyMatch(weather -> weather.getMain().equalsIgnoreCase("Rain"));
        return rainPredicted ? " Carry umbrella" : "";
    }
}
