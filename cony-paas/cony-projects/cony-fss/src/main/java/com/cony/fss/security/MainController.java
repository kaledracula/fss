package com.cony.fss.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangk-p on 2017/11/21.
 */
@Controller
public class MainController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
