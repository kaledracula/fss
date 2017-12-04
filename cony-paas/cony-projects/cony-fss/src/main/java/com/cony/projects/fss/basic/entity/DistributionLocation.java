package com.cony.projects.fss.basic.entity;

import com.cony.data.jpa.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by wangk-p on 2017/12/4.
 */
@Entity
@Table(name = "distribution_location")
public class DistributionLocation extends BaseEntity{

    private Warehouse warehouse;

    private String location;

    private MarketGroup marketGroup;

    private ActiveStatus status;

    private String remark;

    @NotNull(message = "仓库不能为空！")
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    public Warehouse getWarehouse() {
        return warehouse;
    }

    @NotNull(message = "货位不能为空！")
    @Length(max = 255)
    public String getLocation() {
        return location;
    }

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

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setMarketGroup(MarketGroup marketGroup) {
        this.marketGroup = marketGroup;
    }
}
