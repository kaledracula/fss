package com.cony.projects.fss.basic.entity;

import com.cony.data.jpa.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by wangk-p on 2017/11/23.
 */
@Entity
public class Collector extends BaseEntity {

    private Integer code;

    private String name;

    private Long mobilePhone;

    private ActiveStatus status;

    private String remark;

    @Column(columnDefinition = "int(7) UNSIGNED ZEROFILL NULL DEFAULT NULL")
    public Integer getCode() {
        return code;
    }

    @NotNull(message = "业务员姓名不能为空！")
    @Length(max = 255)
    public String getName() {
        return name;
    }

    @NotNull(message = "业务员电话不能为空！")
    @Length(min = 11,max = 11)
    @Pattern(regexp = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$",message = "手机号格式不对，请重新输入！")
    public Long getMobilePhone() {
        return mobilePhone;
    }

    @Enumerated
    @NotNull(message = "状态不能为空！")
    public ActiveStatus getStatus() {
        return status;
    }

    @Column(columnDefinition="TEXT")
    public String getRemark() {
        return remark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobilePhone(Long mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
