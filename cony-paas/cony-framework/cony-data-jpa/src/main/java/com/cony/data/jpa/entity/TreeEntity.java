package com.cony.data.jpa.entity;


import javax.persistence.MappedSuperclass;

/**
 * Created by luorh on 2017/3/10.
 */
@MappedSuperclass
public class TreeEntity extends BaseEntity {

    private Long parentId;

    private String treePath;

    private Long od;

    private String name;

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getOd() {
        return od;
    }

    public void setOd(Long od) {
        this.od = od;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
