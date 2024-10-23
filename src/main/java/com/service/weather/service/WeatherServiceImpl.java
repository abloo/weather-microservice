package com.service.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.weather.models.WeatherData;
import com.service.weather.models.WeatherResponse;
import com.service.weather.response.DailyWeather;
import com.service.weather.response.WeatherRestResponse;
import com.service.weather.strategy.WeatherConditionContext;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherClient weatherClient;
    private final WeatherConditionContext weatherConditionContext;

    @Autowired
    public WeatherServiceImpl(WeatherClient weatherClient, WeatherConditionContext weatherConditionContext) {
        this.weatherClient = weatherClient;
        this.weatherConditionContext = weatherConditionContext;
    }

    @Override
    public List<WeatherRestResponse> getWeatherByCity(String city) {
        // Call the weather API
        WeatherResponse weatherResponse = weatherClient.getWeatherByCity(city);

        List<WeatherRestResponse> response=new ArrayList<>();

        for(WeatherData data:weatherResponse.getList())
        {
        WeatherRestResponse.Builder responseBuilder = new WeatherRestResponse.Builder();
        responseBuilder
                .timezoneOffset(weatherResponse.getCity().getTimezone())
                .dailyWeather(mapToDailyWeatherList(data))
                .message(getWeatherMessage(data))
                .icon(data.getWeather().get(0).getIcon())
                .description(data.getWeather().get(0).getDescription())
                .weatherType(data.getWeather().get(0).getMain());
        response.add(responseBuilder.build());
        }
        return response;
    }

    private DailyWeather mapToDailyWeatherList(WeatherData data) {

        DailyWeather.Builder dailyWeatherBuilder = new DailyWeather.Builder();

            dailyWeatherBuilder
                    .minTemperature((int) (data.getMain().getTemp_min() - 273.15))
                    .maxTemperature((int) (data.getMain().getTemp_max() - 273.15))
                    .feelsLike((int) (data.getMain().getFeels_like() - 273.15))
                    .temperature((int) (data.getMain().getTemp() - 273.15))
                    .humidity(data.getMain().getHumidity())
                    .pressure(data.getMain().getPressure())
                    .windSpeed(data.getWind().getSpeed())
                    .visibility(data.getVisibility())
                    .date(data.getDt_txt().split(" ")[0])
                    .time(data.getDt_txt().split(" ")[1]);

            return dailyWeatherBuilder.build();
       
    }

    private String getWeatherMessage(WeatherData data)
    {
        String conditionMessage = weatherConditionContext.evaluateConditions(data);
        if (!conditionMessage.equals("Weather looks fine!")) {
            return conditionMessage;
        }
        return "Weather looks fine!";
    }

}
