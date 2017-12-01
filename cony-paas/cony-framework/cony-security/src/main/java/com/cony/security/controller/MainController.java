package com.cony.security.controller;

import com.aliyuncs.exceptions.ClientException;
import com.cony.context.exception.BusinessException;
import com.cony.data.common.mapper.MapUtils;
import com.cony.security.cache.PhoneCache;
import com.cony.security.entity.User;
import com.cony.security.entity.UserDto;
import com.cony.security.service.IUserService;
import com.cony.security.util.ValidateCodeUtil;
import com.cony.sms.SMSClient;
import com.cony.web.common.result.DefaultResult;
import com.cony.web.common.result.Result;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.IOException;

/**
 * Created by wangk-p on 2017/11/21.
 */

@RestController()
@RequestMapping("/main")
@Api(value = "MainController", description = "Main API")
public class MainController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PhoneCache phoneCache;

    // 重定向策略
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @GetMapping(value = "")
    public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, "/index.html");
    }

    @GetMapping(value = "/toLogin")
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
    public Result register(@RequestBody @Valid UserDto userDto, HttpServletRequest request) {
        if(ValidateCodeUtil.checkPicCode(userDto.getPicCode(),request)) {
            throw new BusinessException("图片验证码错误！");
        }
        if(ValidateCodeUtil.checkMsgCode(userDto.getUsername(),userDto.getMsgCode(),phoneCache)) {
            throw new BusinessException("手机验证码不符，请重新输入！");
        }
        User user = MapUtils.map(userDto, User.class);
        user.setMobilePhone(Long.parseLong(userDto.getUsername()));
        return new DefaultResult<User>().setData(userService.add(user));
    }

    @GetMapping(value = "/sendMsgCode/{username}")
    public Result sendMsgCode(@PathVariable  @Length(min = 11,max = 11)
                                  @Pattern(regexp = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$",message = "手机号格式不对，请重新输入！")
                                          String username,@RequestParam String picCode,HttpServletRequest request) throws ClientException {
        if(ValidateCodeUtil.checkPicCode(picCode,request)) {
            throw new BusinessException("图片验证码错误！");
        }
        String code = Integer.toString((int) ((Math.random() * 9 + 1) * 100000));
        SMSClient.sendSms(username,code);
        phoneCache.putUsernameAndMsgCode(username,code);
        return new DefaultResult();
    }


}
