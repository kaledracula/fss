package com.cony.projects.fss.goods.entity;

import com.cony.data.jpa.entity.TreeEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wangk-p on 2017/5/15.
 */
@Entity
public class Category extends TreeEntity {

    private String name;

    private String code;

    @NotEmpty(message="名称不能为空!")
    @Length(max=30,message="名称长度不能大于30！")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(max=30,message="代码长度不能大于30！")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
