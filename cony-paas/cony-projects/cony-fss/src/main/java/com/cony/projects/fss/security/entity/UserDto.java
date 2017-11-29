package com.cony.projects.fss.security.entity;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangk-p on 2017/11/29.
 */
public class UserDto {

    private String name;

    private String username;

    private String password;

    private Long mobilePhone;

    private Long fixedPhone;

    private String validateCode;

    private List<Role> roles = new ArrayList<>();

    @NotNull(message = "姓名不能为空！")
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "用户名不能为空！")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotNull(message = "密码不能为空！")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotNull(message = "手机号码不能为空！")
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @NotNull(message = "手机验证码不能为空！")
    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
