package com.cony;

import com.cony.web.EnableWeb;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by wangk-p on 2017/11/21.
 */
@EnableWeb
@SpringBootApplication
@MapperScan(basePackages = "com.cony.fss.*.mapper")
public class FssApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(FssApplication.class).web(true).run(args);
    }
}
