package com.cony.file.entity;


import com.cony.data.jpa.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wangk-p on 2017/5/19.
 */
@Entity
@Table(name = "simple_file")
public class SimpleFile extends BaseEntity {

    private String fileName;

    private String md5;

    private String module;

    private String foreignKey;

    private String type;

    private Long size;

    public SimpleFile() {

    }
    public SimpleFile(String fileName, String md5, String module, String foreignKey,String type,Long size) {
        this.fileName = fileName;
        this.md5 = md5;
        this.module = module;
        this.foreignKey = foreignKey;
        this.type = type;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }
}
