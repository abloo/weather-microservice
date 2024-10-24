package com.service.weather.models;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Wind {
    private double speed;
    private int deg;
    private double gust;

}
