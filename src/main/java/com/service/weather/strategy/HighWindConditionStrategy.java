package com.service.weather.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.weather.config.WeatherConditionConfig;
import com.service.weather.models.WeatherData;

@Component
public class HighWindConditionStrategy implements WeatherConditionStrategy {
    private final WeatherConditionConfig config;

    @Autowired
    public HighWindConditionStrategy(WeatherConditionConfig config) {
        this.config = config;
    }

    @Override
    public String checkCondition(WeatherData data) {
        if (data.getWind().getSpeed() > config.getWindSpeedThreshold()) {
            return "It’s too windy, watch out!";
        }
        return "";
    }
}
