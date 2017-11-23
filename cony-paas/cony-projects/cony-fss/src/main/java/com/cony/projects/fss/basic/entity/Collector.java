package com.cony.projects.fss.basic.entity;

import com.cony.data.jpa.entity.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by wangk-p on 2017/11/23.
 */
@Entity
public class Collector extends BaseEntity {

    private String name;

    private Long mobilePhone;

    @NotNull(message = "司机姓名不能为空！")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Long mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

}
