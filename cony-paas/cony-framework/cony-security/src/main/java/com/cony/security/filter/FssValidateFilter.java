package com.cony.security.filter;

import com.cony.context.exception.BusinessException;
import com.cony.security.cache.PhoneCache;
import com.cony.security.util.ValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangk-p on 2017/12/1.
 */
@Component
public class FssValidateFilter extends OncePerRequestFilter {
    @Autowired
    private PhoneCache phoneCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getMethod().equals("POST")  &&  (request.getRequestURI() == "/login"  || request.getRequestURI() == "/main/register")) {
            String picCode = request.getParameter("picCode") == null ? "" : request.getParameter("picCode");
            if (!ValidateCodeUtil.checkPicCode(picCode, request)) {
                throw new BusinessException("图片验证码错误！");
            }
            String username = request.getParameter("username") == null ? "" : request.getParameter("username");
            String msgCode = request.getParameter("msgCode") == null ? "" : request.getParameter("msgCode");
            String password = request.getParameter("password") == null ? "" : request.getParameter("password");
            if (StringUtils.isEmpty(username)) {
                throw new BusinessException("登录名不能为空！");
            }
            if (!ValidateCodeUtil.checkPhoneNumber(username)) {
                throw new BusinessException("手机号格式不符合，请重新输入！");
            }
//            if(!ValidateCodeUtil.checkMsgCode(username,msgCode,phoneCache)) {
//                throw new BusinessException("手机验证码错误！");
//            }
        }
        filterChain.doFilter(request, response);
    }
}
