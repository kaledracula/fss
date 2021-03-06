package com.cony.security.entity;

import com.cony.data.jpa.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @Length(max = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "用户名不能为空！")
    @Length(min = 11,max = 11)
    @Pattern(regexp = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$",message = "手机号格式不对，请重新输入！")
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "密码不能为空！")
    @Length(max = 255)
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "手机号码不能为空！")
    @Length(min=11,max = 11)
    @Column(unique = true)
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

    @JsonIgnoreProperties("users")
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

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
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
