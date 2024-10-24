package com.service.weather.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Coordinates {
    private double lat;
    private double lon;
}
