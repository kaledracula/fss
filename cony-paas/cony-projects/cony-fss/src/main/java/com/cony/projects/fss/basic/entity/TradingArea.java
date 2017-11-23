package com.cony.projects.fss.basic.entity;

import com.cony.data.jpa.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by wangk-p on 2017/11/23.
 */
@Entity
@Table(name = "trading_area")
public class TradingArea extends BaseEntity {

    private String name;

    private String remark;

    @NotNull(message = "司机姓名不能为空！")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length=2000)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
