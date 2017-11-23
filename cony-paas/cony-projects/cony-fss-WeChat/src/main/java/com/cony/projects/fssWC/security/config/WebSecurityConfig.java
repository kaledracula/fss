package com.cony.projects.fssWC.security.config;


import com.cony.projects.fssWC.security.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by wangk-p on 2017/11/21.
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定义配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests()
//                .antMatchers("/css/**", "/js/**", "/fonts/**").permitAll()  // 都可以访问
//                .antMatchers( "/index").permitAll()  // 都可以访问
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").successForwardUrl("/index").failureUrl("/login-error")
//                .and()
//                .exceptionHandling().accessDeniedPage("/403")
//                .and()
//                .logout().permitAll().logoutSuccessUrl("/login");
    }

    /**
     * 用户信息服务
     */
    @Bean
    UserDetailsService userLoginService() {
        return new UserLoginService();
    }

    /**
     * 认证信息管理
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginService());
    }
}