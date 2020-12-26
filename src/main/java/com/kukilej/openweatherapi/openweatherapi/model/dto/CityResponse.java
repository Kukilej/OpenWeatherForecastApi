package com.kukilej.openweatherapi.openweatherapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityResponse implements Serializable {

    private Long id;
    private String name;

}