package com.cony.projects.fss.security.controller;

import com.aliyuncs.exceptions.ClientException;
import com.cony.context.exception.BusinessException;
import com.cony.data.common.mapper.MapUtils;
import com.cony.projects.fss.security.cache.PhoneCache;
import com.cony.projects.fss.security.entity.User;
import com.cony.projects.fss.security.entity.UserDto;
import com.cony.projects.fss.security.service.IUserService;
import com.cony.projects.fss.sms.SMSClient;
import com.cony.web.common.result.DefaultResult;
import com.cony.web.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by wangk-p on 2017/11/21.
 */

@RestController
@Api(value = "MainController", description = "Main API")
public class MainController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PhoneCache phoneCache;

    // 重定向策略
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @GetMapping(value = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, "/security/login.html");
    }

    @GetMapping(value = "/toRegister")
    public void toRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, "/security/register.html");
    }

    @GetMapping(value = "/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "登陆失败，用户名或者密码错误！");
        return "/security/login.html";
    }

    @GetMapping(value = "/403")
    public void accessDenied(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, "/security/403.html");
    }

    @PostMapping(value = "/register")
    public Result register(@RequestBody @Valid UserDto userDto) {
        if(userDto.getValidateCode() != phoneCache.getVCodeByPhoneNumber(userDto.getMobilePhone())) {
            throw new BusinessException("手机验证码不符，请重新输入！");
        }
        User user = MapUtils.map(userDto, User.class);
        return new DefaultResult<User>().setData(userService.add(user));
    }

    @GetMapping(value = "/sendVCode/{phoneNumber}")
    public Result sendVCode(@PathVariable Long phoneNumber) throws ClientException {
        String code = Integer.toString((int) ((Math.random() * 9 + 1) * 100000));
        SMSClient.sendSms(Long.toString(phoneNumber),code);
        phoneCache.putPhoneNumberAndVCode(phoneNumber,code);
        return new DefaultResult();
    }

}
