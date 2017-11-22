package com.cony.fss.security.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangk-p on 2017/11/21.
 */
@RestController
@Api(value = "MainController", description = "主入口")
public class MainController {

    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
