package com.cnzakii.weather.mapper;

import com.cnzakii.weather.entity.Daily;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * 三日天气预测查询的mapper接口
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-06
 **/
public interface DailyMapper {
    void insertDaily(Daily daily);

    void deleteDailyByLocationId(@Param("LocationId") String LocationId, @Param("today") LocalDate today);

    List<Daily> getDailyListByLocationId(@Param("LocationId") String LocationId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
