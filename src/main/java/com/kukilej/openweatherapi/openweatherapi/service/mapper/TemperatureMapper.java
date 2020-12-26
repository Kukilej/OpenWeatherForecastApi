package com.kukilej.openweatherapi.openweatherapi.service.mapper;

import com.kukilej.openweatherapi.openweatherapi.model.dto.TemperatureDTO;
import com.kukilej.openweatherapi.openweatherapi.model.entities.City;
import com.kukilej.openweatherapi.openweatherapi.model.entities.Temperature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TemperatureMapper {

    @Mapping(source = "city.id", target = "city")
    TemperatureDTO toDto(Temperature temperature);

    @Mapping(source = "city", target = "city")
    Temperature fromDto(TemperatureDTO temperatureDto);

    City findById(Long id);
}
