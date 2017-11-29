package com.cony.es;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by wangk-p on 2017/11/9.
 */
@SpringBootApplication
public class EsSampleApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EsSampleApplication.class).web(true).run(args);
    }
}
