package com.cnzakii.weather.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 城市实体类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
@Data
public class Location implements Serializable {
    @Serial
    private static final long serialVersionUID = -7641552612400749377L;

    private String id;

    private String name;

    private double lat;

    private double lon;

    @Override
    public String toString() {
        return "查询城市信息：" +
                "\n编号" + id +
                "\n名称：" + name +
                "\n纬度：" + lat +
                "\n经度" + lon;
    }
}
