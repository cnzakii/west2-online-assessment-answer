package com.cnzakii.task1;

import java.time.LocalDate;

/**
 * 顾客类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-01
 **/
public class Customer {
    /**
     * 顾客名字
     */
    private String name;

    /**
     * 到店次数
     */
    private int arrivalCount;

    /**
     * 最新到店时间
     */
    private LocalDate latestArrivalTime;


    public Customer(String name) {
        this.name = name;
        this.arrivalCount = 0;
        this.latestArrivalTime = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArrivalCount() {
        return arrivalCount;
    }

    public void setArrivalCount(int arrivalCount) {
        this.arrivalCount = arrivalCount;
    }

    public LocalDate getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public void setLatestArrivalTime(LocalDate latestArrivalTime) {
        this.latestArrivalTime = latestArrivalTime;
    }

    @Override
    public String toString() {
        return "顾客信息如下{" +
                "姓名='" + name + '\'' +
                ", 到店次数=" + arrivalCount +
                ", 最新到店时间=" + latestArrivalTime +
                '}';
    }
}
