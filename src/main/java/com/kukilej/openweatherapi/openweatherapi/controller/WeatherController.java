package com.kukilej.openweatherapi.openweatherapi.controller;

import com.kukilej.openweatherapi.openweatherapi.model.dto.CityResponse;
import com.kukilej.openweatherapi.openweatherapi.model.projection.AvgTempView;
import com.kukilej.openweatherapi.openweatherapi.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api("Controller for exposing Cities and their average temperatures via REST endpoint.")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    @ApiOperation(value = "List all cities. Example request: http://localhost:8080/api ")
    public ResponseEntity<Collection<CityResponse>> getAll() {
        List<CityResponse> cities = weatherService.getAllCities();
        return ResponseEntity.status(HttpStatus.OK)
                .body(cities);
    }

    @GetMapping("/avg")
    @ApiOperation(value = "List average temperatures for given IDs of cities. If no id is given, it will fetch info for all cities. " +
            "Sorts by ascending or descending order  corresponding to provided parameter. If no parameter is provided, default sort is descending.    " +
            "Example requests:  http://localhost:8080/api/avg/?id=2643743,524901&fromDT=1608919200&toDT=1608951600&sort=desc     " +
            "http://localhost:8080/api/avg/?fromDT=1608919200&toDT=1608951600&sort=asc")
    public ResponseEntity<Collection<AvgTempView>> getAvgTemperatures(@RequestParam(required = false) List<Long> id, @RequestParam Long fromDT, @RequestParam Long toDT, @RequestParam(required = false, defaultValue = "desc") String sort) {
        List<AvgTempView> cities = weatherService.getAvgTemps(id, fromDT, toDT, sort);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cities);
    }

}
