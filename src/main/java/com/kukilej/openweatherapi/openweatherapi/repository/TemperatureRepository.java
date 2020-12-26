package com.kukilej.openweatherapi.openweatherapi.repository;

import com.kukilej.openweatherapi.openweatherapi.model.projection.AvgTempView;
import com.kukilej.openweatherapi.openweatherapi.model.entities.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    @Query(value = "select  avg(t.temp)  as averageTemp,c.name as city " +
            "from Temperature t INNER JOIN City c ON t.city.id = c.id " +
            "where (dt between :startDateTime and :endDateTime ) and ( t.city.id in :cities or :cities IS NULL ) " +
            "group by c.name")
    List<AvgTempView> avgTemp(@Param("cities")  List<Long> cities  , @Param("startDateTime") Long startDateTime, @Param("endDateTime") Long endDateTime);

}
