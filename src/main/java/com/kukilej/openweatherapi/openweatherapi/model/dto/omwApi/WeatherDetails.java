package com.kukilej.openweatherapi.openweatherapi.model.dto.omwApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDetails {

    private Long dt;
    private Main main;
}
