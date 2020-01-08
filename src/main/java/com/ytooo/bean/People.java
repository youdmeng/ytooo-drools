package com.ytooo.bean;
import lombok.Data;

/**
 * Created by Youdmeng on 2020/1/7 0007.
 */
@Data
public class People {

    private int sex;

    private String name;

    private Integer age;

    private String drlType;

    public People(int sex, String name, Integer age, String drlType) {
        this.sex = sex;
        this.name = name;
        this.age = age;
        this.drlType = drlType;
    }

    public People(int sex, String name, String drlType) {
        this.sex = sex;
        this.name = name;
        this.drlType = drlType;
    }

    public People() {
    }
}