package com.wjb.javaSpringBoot.modules.account.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ASUS on 2020/8/17 15:32
 */
@Controller
@RequestMapping("/account")
public class AccoutntController {

    /**
     * 127.0.0.1/account/login ---- get
     */
    @GetMapping("/login")
    public String loginPage() {
        return "indexSimple";
    }

    /**
     * 127.0.0.1/account/register ---- get
     */
    @GetMapping("/register")
    public String registerPage() {
        return "indexSimple";
    }

    /**测试拦截器自动生成映射地址template
     * 127.0.0.1/account/users -----get
     */
    @GetMapping("/users")
    public String usersPage(ModelMap modelMap){
        modelMap.addAttribute("head",
                "/upload/hhh.jpg");
        return "index";
    }
}
