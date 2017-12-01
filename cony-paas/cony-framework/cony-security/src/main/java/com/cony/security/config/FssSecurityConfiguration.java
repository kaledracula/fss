package com.cony.security.config;

import com.cony.context.utils.IgnoreDuringScan;
import com.cony.security.filter.FssValidateFilter;
import com.cony.security.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

/**
 * Created by wangk-p on 2017/11/30.
 */
@Configuration
@IgnoreDuringScan
public class FssSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //  http.authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/fonts/**", "/").permitAll()  // 都可以访问
                .antMatchers("/security/**", "/main/**", "/verifyCodeServlet").permitAll()  // 都可以访问
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(fssValidateFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/main/toLogin")
                .failureUrl("/main/login-error")
                .and()
                .exceptionHandling().accessDeniedPage("/main/403")
                .and()
                .logout().permitAll().logoutSuccessUrl("/main/toLogin")
                .and()
                .rememberMe()
                .tokenValiditySeconds(1209600)
                //指定记住登录信息所使用的数据源
                .tokenRepository(tokenRepository());
    }

    @Bean
    public JdbcTokenRepositoryImpl tokenRepository() {
        JdbcTokenRepositoryImpl j = new JdbcTokenRepositoryImpl();
        j.setDataSource(dataSource);
        return j;
    }

    /**
     * 用户信息服务
     */
    @Bean
    UserDetailsService userLoginService() {
        return new UserLoginService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginService());
    }

    @Bean
    FssValidateFilter fssValidateFilter() {
        return new FssValidateFilter();
    }
}
