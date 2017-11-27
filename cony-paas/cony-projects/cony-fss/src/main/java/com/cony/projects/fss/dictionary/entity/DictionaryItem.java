package com.cony.projects.fss.dictionary.entity;
import com.cony.data.jpa.entity.TreeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;

/**
 * Created by houl-b on 2017/9/4.
 * 字典明细表实体
 */
@Entity
public class DictionaryItem extends TreeEntity {

    private String name; //字典明细值

    private String code; //字典明细代码

    private Boolean sysDef; //是否系统定义

    private Long od; //序号

    private Long dictionaryId; //字典表外键

    @NotEmpty(message="名称不能为空!")
    @Length(max=30,message="名称长度不能大于30！")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonIgnore
    public Boolean getSysDef() {
        return sysDef;
    }

    public void setSysDef(Boolean sysDef) {
        this.sysDef = sysDef;
    }

    public Long getOd() {
        return od;
    }

    public void setOd(Long od) {
        this.od = od;
    }

    public Long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

}
