package com.cony.projects.fss.goods.entity;

import com.cony.data.jpa.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by wangk-p on 2017/11/27.
 */
@Entity
public class Goods extends BaseEntity{

    private String code;
    private String name;
    private String standard;
    private String unit;
    private Category categoryLv1;
    private Category categoryLv2;

    @NotNull(message = "商品编号不能为空！")
    @Length(max = 255)
    public String getCode() {
        return code;
    }

    @NotNull(message = "商品名称不能为空！")
    @Length(max = 255)
    public String getName() {
        return name;
    }

    @NotNull(message = "商品规格不能为空！")
    @Length(max = 255)
    public String getStandard() {
        return standard;
    }

    @NotNull(message = "单位不能为空！")
    @Length(max = 255)
    public String getUnit() {
        return unit;
    }
    @NotNull(message = "一级分类不能为空！")
    @ManyToOne
    @JoinColumn(name = "category_lv1_id")
    public Category getCategoryLv1() {
        return categoryLv1;
    }

    @NotNull(message = "二级分类不能为空！")
    @JoinColumn(name = "category_lv2_id")
    public Category getCategoryLv2() {
        return categoryLv2;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCategoryLv1(Category categoryLv1) {
        this.categoryLv1 = categoryLv1;
    }

    public void setCategoryLv2(Category categoryLv2) {
        this.categoryLv2 = categoryLv2;
    }
}
