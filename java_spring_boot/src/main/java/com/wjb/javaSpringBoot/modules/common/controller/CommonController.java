package com.wjb.javaSpringBoot.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ASUS on 2020/8/20 21:35
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    /**
     * 127.0.0.1/common/dashboard ---- get
     */
    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "index";
    }

    /**
     * 127.0.0.1/common/dashboard2 ---- get
     */
    @GetMapping("/dashboard2")
    public String dashboard2Page() {
        return "index";
    }
}