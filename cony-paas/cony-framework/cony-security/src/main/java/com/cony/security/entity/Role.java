package com.cony.security.entity;

import com.cony.data.jpa.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangk-p on 2017/11/22.
 */
@Entity
public class Role extends BaseEntity {

    private String name;

    private List<User> users = new ArrayList<>();

    @NotNull(message = "角色名称不能为空！")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnoreProperties("roles")
    @ManyToMany(mappedBy="roles")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
