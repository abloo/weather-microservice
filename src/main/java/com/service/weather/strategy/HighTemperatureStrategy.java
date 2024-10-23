package com.service.weather.strategy;

import com.service.weather.config.WeatherConditionConfig;
import com.service.weather.models.WeatherData;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HighTemperatureStrategy implements WeatherConditionStrategy {

    private final WeatherConditionConfig config;

    @Autowired
    public HighTemperatureStrategy(WeatherConditionConfig config) {
        this.config = config;
    }

    @Override
    public String checkCondition(WeatherData data) {
        if ((data.getMain().getTemp_max() - 273.15) > config.getTemperatureThreshold()) {
            return "Use sunscreen lotion!";
        }
        return "";
    }
}
