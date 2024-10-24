package com.service.weather.controller;


import com.service.weather.response.WeatherRestResponse;
import com.service.weather.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    private List<WeatherRestResponse> mockWeatherResponse;

    @BeforeEach
    void setUp() {
        // Prepare mock response data
        WeatherRestResponse weatherResponse = new WeatherRestResponse();
        mockWeatherResponse = Arrays.asList(weatherResponse);
    }

    @Test
    public void testGetWeatherByCity() throws Exception {
        // Mock the service call
        when(weatherService.getWeatherByCity("London")).thenReturn(mockWeatherResponse);

        // Perform GET request and check for correct response
        mockMvc.perform(get("/api/v1/London")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that the service was called
        Mockito.verify(weatherService).getWeatherByCity("London");
    }

    @Test
    public void testGetWeatherByCity_NotFound() throws Exception {
        // Mock the service to return an empty list
        when(weatherService.getWeatherByCity("UnknownCity")).thenReturn(List.of());

        // Perform GET request and check for 200 status with an empty response
        mockMvc.perform(get("/api/v1/UnknownCity")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        // Verify that the service was called
        Mockito.verify(weatherService).getWeatherByCity("UnknownCity");
    }
}
