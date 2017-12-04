package com.cony.projects.fss.basic.entity;

import com.cony.context.exception.BusinessException;
import com.cony.data.jpa.entity.BaseEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by wangk-p on 2017/12/4.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ActiveStatus implements BaseEnum {

    ACTIVE(0,"启用"),
    DISABLE(1,"停用");

    private Integer key;
    private String value;

    ActiveStatus(Integer key, String value) {
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
