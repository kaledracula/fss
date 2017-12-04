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
public class Driver extends BaseEntity{

    private String code;

    private String name;

    private Long mobilePhone;

    private String carNumber;

    private String carType;

    private MarketGroup marketGroup;

    private ActiveStatus status;

    private String remark;

    public String getCode() {
        return code;
    }

    @NotNull(message = "司机姓名不能为空！")
    @Length(max = 255)
    public String getName() {
        return name;
    }

    @NotNull(message = "司机电话不能为空！")
    @Length(min = 11,max = 11)
    @Pattern(regexp = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$",message = "手机号格式不对，请重新输入！")
    public Long getMobilePhone() {
        return mobilePhone;
    }

    @Length(max = 255)
    public String getCarNumber() {
        return carNumber;
    }

    @Length(max = 255)
    public String getCarType() {
        return carType;
    }

    @JsonIgnoreProperties("drivers")
    @NotNull(message = "市场组不能为空！")
    @ManyToOne
    @JoinColumn(name = "market_group_id")
    public MarketGroup getMarketGroup() {
        return marketGroup;
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

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public void setMarketGroup(MarketGroup marketGroup) {
        this.marketGroup = marketGroup;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
