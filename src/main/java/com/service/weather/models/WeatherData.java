package com.service.weather.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class WeatherData {
    private long dt;
    private MainWeatherData main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private int visibility;
    private Sys sys;
    private String dt_txt;
    private String message;

}
