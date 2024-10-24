package com.service.weather.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WeatherRestResponse {

    @ApiModelProperty
    private String message;

    @ApiModelProperty
    private String icon;

    @ApiModelProperty
    private String description;

    @ApiModelProperty
    private DailyWeather dailyWeather;

    @ApiModelProperty
    private String weatherType;

    @ApiModelProperty
    private int timezoneOffset;

    // Private constructor to enforce the use of builder
    private WeatherRestResponse(Builder builder) {
        this.message = builder.message;
        this.icon = builder.icon;
        this.description = builder.description;
        this.dailyWeather = builder.dailyWeather;
        this.weatherType = builder.weatherType;
        this.timezoneOffset = builder.timezoneOffset;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DailyWeather getDailyWeather() {
        return dailyWeather;
    }

    public void setDailyWeather(DailyWeather dailyWeather) {
        this.dailyWeather = dailyWeather;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public int getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(int timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    // Static Builder class
    public static class Builder {
        private String message;
        private String icon;
        private String description;
        private DailyWeather dailyWeather;
        private String weatherType;
        private int timezoneOffset;

        // Builder methods for setting fields
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder icon(String icon) {
            this.icon = icon;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder dailyWeather(DailyWeather dailyWeathers) {
            this.dailyWeather = dailyWeathers;
            return this;
        }

        public Builder weatherType(String weatherType) {
            this.weatherType = weatherType;
            return this;
        }

        public Builder timezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
            return this;
        }

        // Build method to create an instance of WeatherRestResponse
        public WeatherRestResponse build() {
            return new WeatherRestResponse(this);
        }
    }
}
