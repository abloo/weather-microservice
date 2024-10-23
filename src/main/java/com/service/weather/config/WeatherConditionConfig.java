package com.service.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherConditionConfig {

    @Value("${weather.temperature.threshold}")
    private double temperatureThreshold;

    @Value("${weather.wind.speed.threshold}")
    private double windSpeedThreshold;

    @Value("${weather.api.condition.thunderstorm}")
    private String thunderstormCondition;

    public double getTemperatureThreshold() {
        return temperatureThreshold;
    }

    public double getWindSpeedThreshold() {
        return windSpeedThreshold;
    }

    public String getThunderstormCondition() {
        return thunderstormCondition;
    }
}

