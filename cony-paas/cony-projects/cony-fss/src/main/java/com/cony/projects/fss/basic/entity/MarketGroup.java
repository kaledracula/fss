package com.cony.projects.fss.basic.entity;

import com.cony.data.jpa.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangk-p on 2017/12/4.
 */
@Entity
@Table(name = "market_group")
public class MarketGroup extends BaseEntity{

    private String name;

    private ActiveStatus status;

    private List<Driver> drivers = new ArrayList<>();

    private List<Salesman> salesmen  = new ArrayList<>();

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

    @JsonIgnoreProperties("marketGroup")
    @OneToMany(mappedBy = "marketGroup",fetch = FetchType.LAZY)
    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @JsonIgnoreProperties("marketGroup")
    @OneToMany(mappedBy = "marketGroup",fetch = FetchType.LAZY)
    public List<Salesman> getSalesmen() {
        return salesmen;
    }

    public void setSalesmen(List<Salesman> salesmen) {
        this.salesmen = salesmen;
    }

    @Column(columnDefinition="TEXT")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
