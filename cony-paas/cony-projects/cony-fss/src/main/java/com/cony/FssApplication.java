package com.cony;

import com.cony.context.utils.IgnoreDuringScan;
import com.cony.security.config.FssSecurityConfiguration;
import com.cony.security.filter.FssValidateFilter;
import com.cony.web.EnableWeb;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by wangk-p on 2017/11/21.
 */
@EnableWeb
@EnableSecurity
@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "com.cony.projects.fss.*.mapper")
@ServletComponentScan
public class FssApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(FssApplication.class).web(true).run(args);
    }
}
