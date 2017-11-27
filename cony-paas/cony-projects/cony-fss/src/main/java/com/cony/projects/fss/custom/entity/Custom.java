package com.cony.projects.fss.custom.entity;

import com.cony.data.jpa.entity.BaseEntity;
import com.cony.projects.fss.dictionary.entity.DictionaryItem;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by wangk-p on 2017/11/27.
 */
@Entity
public class Custom extends BaseEntity {

    private String name; //名称

    private String username; //用户名

    private String password; //密码

    private Long mobilePhone; //联系电话

    private String linkman;//联系人

    private String address;//地址

    private Date deliveryBeginTime;//送货开始时间

    private Date deliveryEndTime;//送货结束时间

    private CustomStatus status;//状态

    private DictionaryItem type;//类型

    private PrintType printType;//打印类型

    private PackageTime packageTime;//打包时间

    private String remark;//备注

    private List<Department> departments;//部门

    @NotNull(message = "客户名称不能为空！")
    public String getName() {
        return name;
    }

    @NotNull(message = "用户名不能为空！")
    public String getUsername() {
        return username;
    }

    @NotNull(message = "密码不能为空！")
    @Length(min = 6)
    public String getPassword() {
        return password;
    }
    @NotNull(message = "联系电话不能为空！")
    public Long getMobilePhone() {
        return mobilePhone;
    }
    @NotNull(message = "联系人不能为空！")
    public String getLinkman() {
        return linkman;
    }
    @NotNull(message = "地址不能为空！")
    public String getAddress() {
        return address;
    }
    @NotNull(message = "送货开始时间不能为空！")
    public Date getDeliveryBeginTime() {
        return deliveryBeginTime;
    }
    @NotNull(message = "送货结束时间不能为空！")
    public Date getDeliveryEndTime() {
        return deliveryEndTime;
    }
    @NotNull(message = "状态不能为空！")
    public CustomStatus getStatus() {
        return status;
    }
    @NotNull(message = "类型不能为空！")
    @ManyToOne
    @JoinColumn(name = "type_dic_item_id")
    public DictionaryItem getType() {
        return type;
    }
    @NotNull(message = "打印类型不能为空！")
    public PrintType getPrintType() {
        return printType;
    }
    @NotNull(message = "打包时间不能为空！")
    public PackageTime getPackageTime() {
        return packageTime;
    }

    public String getRemark() {
        return remark;
    }

    @OneToMany(mappedBy = "custom", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    public List<Department> getDepartments() {
        if(departments != null) {
            for(Department department : departments) {
                department.setCustom(this);
            }
        }
        return departments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobilePhone(Long mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDeliveryBeginTime(Date deliveryBeginTime) {
        this.deliveryBeginTime = deliveryBeginTime;
    }

    public void setDeliveryEndTime(Date deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }

    public void setStatus(CustomStatus status) {
        this.status = status;
    }

    public void setType(DictionaryItem type) {
        this.type = type;
    }

    public void setPrintType(PrintType printType) {
        this.printType = printType;
    }

    public void setPackageTime(PackageTime packageTime) {
        this.packageTime = packageTime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
