package com.cnzakii.weather.mapper;

import com.cnzakii.weather.entity.Location;

/**
 * 城市查询的map接口
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-06
 **/
public interface LocationMapper {
    void insertLocation(Location location);
    void deleteLocationById(String id);

    Location getLocationByName(String name);
}
