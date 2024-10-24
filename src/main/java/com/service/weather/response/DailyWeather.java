package com.service.weather.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DailyWeather {

    @ApiModelProperty
    private int minTemperature;

    @ApiModelProperty
    private int maxTemperature;

    @ApiModelProperty
    private int feelsLike;

    @ApiModelProperty
    private int humidity;

    @ApiModelProperty
    private int pressure;

    @ApiModelProperty
    private int temperature;

    @ApiModelProperty
    private String date;

    @ApiModelProperty
    private String time;

    @ApiModelProperty
    private double windSpeed;

    @ApiModelProperty
    private double visibility;

    // Private constructor to enforce the use of builder
    private DailyWeather(Builder builder) {
        this.minTemperature = builder.minTemperature;
        this.maxTemperature = builder.maxTemperature;
        this.feelsLike = builder.feelsLike;
        this.humidity = builder.humidity;
        this.pressure = builder.pressure;
        this.temperature = builder.temperature;
        this.date = builder.date;
        this.time = builder.time;
        this.windSpeed = builder.windSpeed;
        this.visibility = builder.visibility;
    }

    // Static builder class
    public static class Builder {
        private int minTemperature;
        private int maxTemperature;
        private int feelsLike;
        private int humidity;
        private int pressure;
        private int temperature;
        private String date;
        private String time;
        private double windSpeed;
        private double visibility;

        // Builder methods for setting fields
        public Builder minTemperature(int minTemperature) {
            this.minTemperature = minTemperature;
            return this;
        }

        public Builder maxTemperature(int maxTemperature) {
            this.maxTemperature = maxTemperature;
            return this;
        }

        public Builder feelsLike(int feelsLike) {
            this.feelsLike = feelsLike;
            return this;
        }

        public Builder humidity(int humidity) {
            this.humidity = humidity;
            return this;
        }

        public Builder pressure(int pressure) {
            this.pressure = pressure;
            return this;
        }

        public Builder temperature(int temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Builder time(String time) {
            this.time = time;
            return this;
        }

        public Builder windSpeed(double windSpeed) {
            this.windSpeed = (windSpeed*3.6);
            return this;
        }

        public Builder visibility(int visibility) {
            this.visibility = visibility/1000;
            return this;
        }

        // Build method to create an instance of DailyWeather
        public DailyWeather build() {
            return new DailyWeather(this);
        }
    }
}
