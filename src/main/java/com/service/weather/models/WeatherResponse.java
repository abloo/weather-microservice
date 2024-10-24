package com.service.weather.models;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WeatherResponse {
    private String cod;
    private int message;
    private int cnt;
    private List<WeatherData> list;
    private City city;

}
