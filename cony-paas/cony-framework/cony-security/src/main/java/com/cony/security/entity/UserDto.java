package com.cony.security.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangk-p on 2017/11/29.
 */
public class UserDto {

    private String name;

    private String username;

    private String password;

    private Long fixedPhone;

    private String msgCode;

    private String picCode;


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
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotNull(message = "密码不能为空！")
    @Length(max = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    @NotNull(message = "图片验证码不能为空！")
    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }
}
