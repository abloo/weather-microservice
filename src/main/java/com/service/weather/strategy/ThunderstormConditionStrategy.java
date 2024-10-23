package com.service.weather.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.weather.config.WeatherConditionConfig;
import com.service.weather.models.WeatherData;

@Component
public class ThunderstormConditionStrategy implements WeatherConditionStrategy {
    private final WeatherConditionConfig config;

    @Autowired
    public ThunderstormConditionStrategy(WeatherConditionConfig config) {
        this.config = config;
    }

    @Override
    public String checkCondition(WeatherData data) {
        if (data.getWeather().get(0).getMain().equalsIgnoreCase(config.getThunderstormCondition())) {
            return "Don’t step out! A Storm is brewing!";
        }
        return "";
    }
}
