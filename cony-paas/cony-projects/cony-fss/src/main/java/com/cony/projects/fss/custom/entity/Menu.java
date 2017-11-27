package com.cony.projects.fss.custom.entity;

import com.cony.data.jpa.entity.BaseEntity;
import com.cony.projects.fss.goods.entity.Goods;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by wangk-p on 2017/11/27.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"department_id", "goods_id"})})
public class Menu extends BaseEntity {

    private Department department;

    private Goods goods;

    @NotNull(message = "商品不能为空！")
    @ManyToOne
    @JoinColumn(name = "goods_id")
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @NotNull(message = "部门不能为空！")
    @ManyToOne
    @JoinColumn(name = "department_id")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
