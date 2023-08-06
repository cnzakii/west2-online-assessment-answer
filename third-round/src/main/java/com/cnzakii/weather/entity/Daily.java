package com.cnzakii.weather.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 每日天气预报实体类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
@Data
public class Daily implements Serializable {

    @Serial
    private static final long serialVersionUID = 7303333095862487563L;

    @JsonIgnore
    private int id;

    @JsonIgnore
    private String locationId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fxDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime sunrise;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime sunset;

    @JsonFormat(pattern = "HH:mm")
    private String moonrise;

    @JsonFormat(pattern = "HH:mm")
    private String moonset;
    private String moonPhase;

    private int tempMax;
    private int tempMin;

    private String textDay;
    private String textNight;

    private int wind360Day;

    private String windDirDay;
    private String windScaleDay;

    private int windSpeedDay;

    private int wind360Night;

    private String windDirNight;
    private String windScaleNight;

    private int windSpeedNight;
    private int humidity;
    private double precip;
    private int pressure;
    private int vis;
    private int cloud;
    private int uvIndex;

    @Override
    public String toString() {
        return "预报日期：" + fxDate +
                "\n日出时间：" + sunrise +
                "\n日落时间：" + sunset +
                "\n当天月升时间：" + moonrise +
                "\n当天月落时间：" + moonset +
                "\n月相名称：" + moonPhase +
                "\n预报当天最高温度：" + tempMax +
                "\n预报当天最低温度：" + tempMin +
                "\n预报白天天气状况：" + textDay +
                "\n预报晚间天气状况：" + textNight +
                "\n预报白天风向360角度：" + wind360Day +
                "\n预报白天风向：" + windDirDay +
                "\n预报白天风力等级：" + windScaleDay +
                "\n预报白天风速，公里/小时：" + windSpeedDay +
                "\n预报夜间风向360角度：" + wind360Night +
                "\n预报夜间当天风向：" + windDirNight +
                "\n预报夜间风力等级：" + windScaleNight +
                "\n预报夜间风速，公里/小时：" + windSpeedNight +
                "\n预报当天总降水量，默认单位：毫米：" + precip +
                "\n紫外线强度指数：" + uvIndex +
                "\n相对湿度，百分比数值：" + humidity +
                "\n大气压强，默认单位：百帕：" + pressure +
                "\n能见度，默认单位：公里：" + vis +
                "\n云量，百分比数值：" + cloud;
    }
}
