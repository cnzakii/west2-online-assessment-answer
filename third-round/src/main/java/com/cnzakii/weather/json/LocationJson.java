package com.cnzakii.weather.json;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 城市查询响应请求中"location"数组中元素的映射
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
@Data
public class LocationJson implements Serializable {
    @Serial
    private static final long serialVersionUID = -7641552612400749377L;

    private String name;

    private int id;

    private double lat;

    private double lon;

    private String adm2;

    private String adm1;

    private String country;

    private String tz;

    private String utcOffset;

    private int isDst;

    private String type;

    private int rank;

    private String fxLink;
}
