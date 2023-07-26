package com.Java6.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping({"/", "home/index"})
    public String home(){
        return "redirect:/product/list";
    }

    @PreAuthorize("hasAuthority('DIRE')")
    @RequestMapping({"/admin","/admin/home/index"})
    public String admin(){
        return "redirect:/assets/admin/index.html";
        // src\main\resources\static\assets\admin\index.html
    }
}
