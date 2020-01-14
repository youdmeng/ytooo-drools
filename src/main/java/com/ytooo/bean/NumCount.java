package com.ytooo.bean;

import lombok.Data;

/**
 * Created by Youdmeng on 2020/1/14 0014.
 */
@Data
public class NumCount {

    private int count;

    public void plus() {
        count = count + 1;
    }



}
