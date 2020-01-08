package com.ytooo.bean;

import lombok.Data;

/**
 * Created by Youdmeng on 2020/1/8 0008.
 */
@Data
public class Sensor {

    private String description;

    private Double temp;

    public Sensor() {
    }

    public Sensor(String description, Double temp) {
        this.description = description;
        this.temp = temp;
    }
}
