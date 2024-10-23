package com.service.weather.strategy;

import com.service.weather.models.WeatherData;

public interface WeatherConditionStrategy {
    String checkCondition(WeatherData data);
}
