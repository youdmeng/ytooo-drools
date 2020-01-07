package com.ytooo.bean;
import lombok.Data;

/**
 * Created by Youdmeng on 2020/1/7 0007.
 */
@Data
public class People {

    private int sex;

    private String name;

    public People(int sex, String name) {
        this.sex = sex;
        this.name = name;
    }

    public People() {
    }
}