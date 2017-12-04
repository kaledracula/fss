package com.cony.projects.fss.basic.entity;

import com.cony.data.jpa.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by wangk-p on 2017/12/4.
 */
@Entity
@Table(name = "goods_location")
public class GoodsLocation extends BaseEntity{

    private Warehouse warehouse;

    private String location;

    private GoodsLocationType type;

    private ActiveStatus status;

    private Collector collector;

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

    @Enumerated
    @NotNull(message = "类型不能为空！")
    public GoodsLocationType getType() {
        return type;
    }

    @Enumerated
    @NotNull(message = "状态不能为空！")
    public ActiveStatus getStatus() {
        return status;
    }

    @ManyToOne
    @JoinColumn(name = "collector_id")
    public Collector getCollector() {
        return collector;
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

    public void setType(GoodsLocationType type) {
        this.type = type;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
