package com.ytooo.bean;
import lombok.Data;

/**
 * Created by Youdmeng on 2020/1/7 0007.
 */
@Data
public class People {

    private int sex;

    private String name;

    private String drlType;

    public People(int sex, String name, String drlType) {
        this.sex = sex;
        this.name = name;
        this.drlType = drlType;
    }

    public People() {
    }
}