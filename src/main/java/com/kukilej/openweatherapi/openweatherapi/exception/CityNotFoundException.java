package com.kukilej.openweatherapi.openweatherapi.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(Long cityId) {
        super("Cannot find city with id:" + cityId);
    }
}