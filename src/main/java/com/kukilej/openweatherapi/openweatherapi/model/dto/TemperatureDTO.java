package com.kukilej.openweatherapi.openweatherapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor
public class TemperatureDTO implements Serializable {

    private Long city;
    private Long dt;

    private double temp;

}
