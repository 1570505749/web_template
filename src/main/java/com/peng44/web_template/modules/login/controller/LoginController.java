package com.peng44.web_template.modules.login.controller;

import com.peng44.web_template.modules.login.service.LoginService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: web_template
 * @description: 用户登录控制层
 * @author: nile
 * @create: 2020-09-12 18:30
 **/

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

}
