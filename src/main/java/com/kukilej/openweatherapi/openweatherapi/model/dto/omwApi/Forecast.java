package com.kukilej.openweatherapi.openweatherapi.model.dto.omwApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast implements Serializable {

    private List<WeatherDetails> list;
    private CityDetails city;
}
