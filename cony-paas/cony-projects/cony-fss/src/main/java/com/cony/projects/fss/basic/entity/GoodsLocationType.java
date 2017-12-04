package com.cony.projects.fss.basic.entity;

import com.cony.data.jpa.entity.BaseEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by wangk-p on 2017/12/4.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  GoodsLocationType implements BaseEnum {

    STORAGE (0,"储货位"),
    TEMPORARY (1,"暂存位");

    private Integer key;
    private String value;

    GoodsLocationType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
