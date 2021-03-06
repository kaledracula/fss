package com.cony.projects.fss.custom.entity;

import com.cony.data.jpa.entity.BaseEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by wangk-p on 2017/11/27.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PrintType implements BaseEnum {

    MERGE(0,"合并打印"),
    SINGLE(1,"分单打印");

    private Integer key;
    private String value;

    PrintType(Integer key,String value) {
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
