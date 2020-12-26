package com.kukilej.openweatherapi.openweatherapi.model.dto.omwApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {

    private double temp;
}
