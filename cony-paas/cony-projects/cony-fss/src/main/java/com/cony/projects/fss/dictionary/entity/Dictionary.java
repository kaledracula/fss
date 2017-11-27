package com.cony.projects.fss.dictionary.entity;

import com.cony.data.jpa.entity.TreeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import java.util.Collection;

/**
 * Created by houl-b on 2017/9/4.
 * 字典表实体
 */
@Entity
public class Dictionary extends TreeEntity {
    private String name; //字典名

    private String code; //字典代码

    private Boolean tree = true; //是否支持字典的树形结构

    private Long od;//序号

    private Collection<DictionaryItem> items; //字典明细对象

    @NotEmpty(message = "名称不能为空!")
    @Length(max = 30, message = "名称长度不能大于30！")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "代码不能为空!")
    @Length(max = 30, message = "代码长度不能大于30！")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "代码只允许数字和英文字母！")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonIgnore
    public Boolean getTree() {
        return tree;
    }

    public void setTree(Boolean tree) {
        this.tree = tree;
    }

    @OneToMany(mappedBy = "dictionaryId", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    public Collection<DictionaryItem> getItems() {
        return items;
    }

    public void setItems(Collection<DictionaryItem> items) {
        this.items = items;
    }

    @JsonIgnore
    public Long getOd() {
        return od;
    }

    public void setOd(Long od) {
        this.od = od;
    }

}
