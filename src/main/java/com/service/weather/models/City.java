package com.service.weather.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class City {
    private long id;
    private String name;
    private Coordinates coord;
    private String country;
    private int population;
    private int timezone;
    private long sunrise;
    private long sunset;

}
