package com.cony.projects.fss.custom.entity;

import com.cony.data.jpa.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by wangk-p on 2017/12/5.
 */
@Entity
@Table(name = "account_period")
public class AccountPeriod extends BaseEntity{

    private Long customId;

    private String customName;

    private Boolean setup;

    private Date repaymentDate;

    private String remark;

    @NotNull(message = "客户ID不能为空！")
    public Long getCustomId() {
        return customId;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }
    @NotNull(message = "客户名称不能为空！")
    @Length(max = 255)
    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
    @NotNull(message = "是否设置账期不能为空！")
    @Length(max = 1)
    public Boolean getSetup() {
        return setup;
    }

    public void setSetup(Boolean setup) {
        this.setup = setup;
    }
    @NotNull(message = "还款日期不能为空！")
    public Date getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }
    @Column(columnDefinition="TEXT")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
