package com.cony.web;

import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.annotation.*;

/**
 * Created by wangk-p on 2017/5/2.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableJpaAuditing
@EnableTransactionManagement
@Import(DefaultConfiguration.class)
public @interface EnableWeb {
}
