package com.cony.fss.security.entity;

import com.cony.data.jpa.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangk-p on 2017/11/22.
 */
@Entity
public class Role extends BaseEntity{

    private String name;

    private Set<User> userSet = new HashSet<>();

    @NotNull(message = "角色名称不能为空！")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy="roleSet")
    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
