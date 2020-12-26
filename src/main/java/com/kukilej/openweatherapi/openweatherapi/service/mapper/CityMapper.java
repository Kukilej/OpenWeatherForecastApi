package com.kukilej.openweatherapi.openweatherapi.service.mapper;


import com.kukilej.openweatherapi.openweatherapi.model.dto.CityResponse;
import com.kukilej.openweatherapi.openweatherapi.model.entities.City;
import com.kukilej.openweatherapi.openweatherapi.model.dto.omwApi.CityDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TemperatureMapper.class)
public interface CityMapper {

    CityResponse toDto(City city);


    @Mapping(target = "temperatures", ignore = true)
    City fromOwmDto(CityDetails dto);
}