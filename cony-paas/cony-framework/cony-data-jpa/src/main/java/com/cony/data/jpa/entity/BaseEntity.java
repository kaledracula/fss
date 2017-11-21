package com.cony.data.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by luorh on 2017/3/10.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class BaseEntity implements Serializable {

    private Long id;

    private int version;

    private Date createTime;

    private Date updateTime;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @Version
    public int getVersion() {
        return version;
    }

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JsonIgnore
    public Date getCreateTime() {
        return createTime;
    }

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
