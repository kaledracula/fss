package com.cony.projects.fss.custom.entity;

import com.cony.data.jpa.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by wangk-p on 2017/11/27.
 */
@Entity
public class Department extends BaseEntity{

    private String name; //部门名称

    private String password;//密码

    private String linkman;//联系人

    private Long mobilePhone;//联系电话

    private boolean main = false;//是否主部门

    private DepartmentStatus status;//状态

    private Custom custom;//客户

    @NotNull(message = "部门名称不能为空！")
    public String getName() {
        return name;
    }
    @NotNull(message = "密码不能为空！")
    public String getPassword() {
        return password;
    }

    @NotNull(message = "联系人不能为空！")
    public String getLinkman() {
        return linkman;
    }
    @NotNull(message = "联系电话不能为空！")
    @Length(min = 11,max=11,message="联系电话长度为11！")
    public Long getMobilePhone() {
        return mobilePhone;
    }

    public boolean isMain() {
        return main;
    }

    @NotNull(message = "状态不能为空！")
    @Enumerated
    public DepartmentStatus getStatus() {
        return status;
    }

    @ManyToOne()
    @JoinColumn(name = "custom_id")
    public Custom getCustom() {
        return custom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public void setMobilePhone(Long mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public void setStatus(DepartmentStatus status) {
        this.status = status;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }
}
