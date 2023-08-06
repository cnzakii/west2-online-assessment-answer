package com.cnzakii.weather.json;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 三日天气查询中“daily”数组中元素的映射
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
@Data
public class DailyJson implements Serializable {

    @Serial
    private static final long serialVersionUID = -4812079172199038941L;


    private String fxDate;
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String moonPhase;
    private String moonPhaseIcon;
    private String tempMax;
    private String tempMin;
    private String iconDay;
    private String textDay;
    private String iconNight;
    private String textNight;
    private String wind360Day;
    private String windDirDay;
    private String windScaleDay;
    private String windSpeedDay;
    private String wind360Night;
    private String windDirNight;
    private String windScaleNight;
    private String windSpeedNight;
    private String humidity;
    private String precip;
    private String pressure;
    private String vis;
    private String cloud;
    private String uvIndex;
}
