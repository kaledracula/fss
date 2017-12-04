package com.cony.projects.fss.basic.entity;

import com.cony.data.jpa.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * Created by wangk-p on 2017/12/4.
 */
@Entity
public class Warehouse extends BaseEntity {

    private String name;

    private ActiveStatus status;

    private String remark;

    @NotNull(message = "名称不能为空！")
    @Length(max = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated
    @NotNull(message = "状态不能为空！")
    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }

    @Column(columnDefinition="TEXT")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}


