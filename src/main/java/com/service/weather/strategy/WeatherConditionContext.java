package com.service.weather.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.weather.models.WeatherData;

import java.util.List;

@Component
public class WeatherConditionContext {

    private final List<WeatherConditionStrategy> strategies;

    @Autowired
    public WeatherConditionContext(List<WeatherConditionStrategy> strategies) {
        this.strategies = strategies;
    }

    public String evaluateConditions(WeatherData data) {
        StringBuilder messages = new StringBuilder();
        
        for (WeatherConditionStrategy strategy : strategies) {
            String message = strategy.checkCondition(data);
            if (!message.isEmpty() && message !=null) {
                if (messages.length() > 0) {
                    messages.append(" | ");
                }
                messages.append(message);
            }
        }
        
        return messages.length() > 0 ? messages.toString() : "Weather looks fine!";
    }
}
