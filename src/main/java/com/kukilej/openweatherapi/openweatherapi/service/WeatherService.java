package com.kukilej.openweatherapi.openweatherapi.service;

import com.kukilej.openweatherapi.openweatherapi.model.dto.CityResponse;
import com.kukilej.openweatherapi.openweatherapi.model.dto.TemperatureDTO;
import com.kukilej.openweatherapi.openweatherapi.model.projection.AvgTempView;
import com.kukilej.openweatherapi.openweatherapi.model.entities.City;
import com.kukilej.openweatherapi.openweatherapi.repository.CityRepository;
import com.kukilej.openweatherapi.openweatherapi.repository.TemperatureRepository;
import com.kukilej.openweatherapi.openweatherapi.service.mapper.CityMapper;
import com.kukilej.openweatherapi.openweatherapi.service.mapper.TemperatureMapper;
import com.kukilej.openweatherapi.openweatherapi.model.dto.omwApi.CityDetails;
import com.kukilej.openweatherapi.openweatherapi.model.dto.omwApi.Forecast;
import com.kukilej.openweatherapi.openweatherapi.model.dto.omwApi.WeatherDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    @Value("${openweathermap.appId}")
    private String API_ID;

    //TODO: Probably better to fetch cities from app.properties compared to persisting them to db from init.sql script

    @Value("${openweathermap.url}")
    private String URI;

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final TemperatureRepository temperatureRepository;
    private final RestTemplate restTemplate;
    private final TemperatureMapper temperatureMapper;


    public WeatherService(CityRepository cityRepository, CityMapper cityMapper, TemperatureRepository temperatureRepository, RestTemplateBuilder restTemplateBuilder, TemperatureMapper temperatureMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.temperatureRepository = temperatureRepository;
        this.restTemplate = restTemplateBuilder.build();
        this.temperatureMapper = temperatureMapper;
    }

    @PostConstruct
    public void init() {

        String[] values = {"London", "Kyiv", "Moscow"};

        for (String city : values) {

            Forecast forecast = this.restTemplate.getForObject(this.url(city), Forecast.class);
            CityDetails cityDetails = forecast.getCity();

            for (WeatherDetails weatherDetails : forecast.getList()) {
                saveTemperature(cityDetails, weatherDetails);
            }
        }
    }
    @Transactional
    private void saveTemperature(CityDetails cityDetails, WeatherDetails weatherDetails) {
        TemperatureDTO temperatureDTO = new TemperatureDTO();
        temperatureDTO.setCity(cityDetails.getId());
        temperatureDTO.setDt(weatherDetails.getDt());
        temperatureDTO.setTemp(weatherDetails.getMain().getTemp());
        temperatureRepository.save(temperatureMapper.fromDto(temperatureDTO));
    }

    @Transactional(readOnly = true)
    public List<CityResponse> getAllCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream().map(city -> cityMapper.toDto(city)).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<AvgTempView> getAvgTemps(List<Long> id, Long startDateTime, Long endDateTime, String sort) {
        List<AvgTempView> avgTempViewList = temperatureRepository.avgTemp(id, startDateTime, endDateTime);

        //TODO: Format avg temp to less decimals. Currently some problems due to using Jpa interface projection
      /*  DecimalFormat df = new DecimalFormat("##.##");
       avgTempViewList =  avgTempViewList.stream().map(avgTempView  -> df.format(avgTempView.getAverageTemp())).collect(Collectors.toList());*/
        if (sort.isEmpty() || sort.equalsIgnoreCase("desc")) {
            avgTempViewList.sort(Comparator.comparing(AvgTempView::getAverageTemp).reversed());
        } else {
            avgTempViewList.sort(Comparator.comparing(AvgTempView::getAverageTemp));
        }
        return avgTempViewList;
    }

    private String url(String city) {
        return String.format(URI.concat("?q=%s").concat("&appid=%s").concat("&units=metric"), city, API_ID);
    }

}

