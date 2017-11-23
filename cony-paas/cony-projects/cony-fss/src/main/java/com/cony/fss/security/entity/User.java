package com.cony.fss.security.entity;

import com.cony.data.jpa.entity.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by wangk-p on 2017/11/22.
 */
@Entity
public class User extends BaseEntity implements UserDetails {

    private String name;

    private String username;

    private String password;

    private Long mobilePhone;

    private Long fixedPhone;

    private List<Role> roles = new ArrayList<>();

    @NotNull(message = "姓名不能为空！")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "用户名不能为空！")
    @Override
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "密码不能为空！")
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Long mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Long getFixedPhone() {
        return fixedPhone;
    }

    public void setFixedPhone(Long fixedPhone) {
        this.fixedPhone = fixedPhone;
    }

    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(
            name="role_user_relation",
            joinColumns=@JoinColumn(name="user_id", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName="ID"))
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = this.getRoles();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
