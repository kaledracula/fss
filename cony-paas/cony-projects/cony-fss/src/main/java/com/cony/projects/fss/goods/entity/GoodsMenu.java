package com.cony.projects.fss.goods.entity;

import com.cony.data.jpa.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by wangk-p on 2017/11/27.
 */
@Entity
@Table(name = "goods_menu",uniqueConstraints = {@UniqueConstraint(columnNames={"department_id", "goods_id"})})
public class GoodsMenu extends BaseEntity {

    private Long departmentId;

    private Goods goods;

    private Category categoryLv1;

    private Category categoryLv2;

    @NotNull(message = "商品不能为空！")
    @ManyToOne()
    @JoinColumn(name = "goods_id")
    @JsonIgnoreProperties({"categoryLv1","categoryLv2"})
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @NotNull(message = "部门ID不能为空！")
    @Column(name = "department_id")
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @NotNull(message = "一级分类不能为空！")
    @ManyToOne
    @JoinColumn(name = "category_lv1_id")
    public Category getCategoryLv1() {
        return categoryLv1;
    }

    public void setCategoryLv1(Category categoryLv1) {
        this.categoryLv1 = categoryLv1;
    }
    @NotNull(message = "二级分类不能为空！")
    @ManyToOne
    @JoinColumn(name = "category_lv2_id")
    public Category getCategoryLv2() {
        return categoryLv2;
    }

    public void setCategoryLv2(Category categoryLv2) {
        this.categoryLv2 = categoryLv2;
    }
}
