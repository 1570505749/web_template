package com.peng44.web_template.modules.user.controller;

import com.peng44.web_template.commons.utils.Result;
import com.peng44.web_template.modules.user.Vo.SysMenuVo;
import com.peng44.web_template.modules.user.service.UserMenuService;
import com.peng44.web_template.security.oauth2client.clientinfo.Gitee;
import com.peng44.web_template.security.oauth2client.clientinfo.Github;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: web_template
 * @description: 前台菜单控制类
 * @author: nile
 * @create: 2020-09-30 18:08
 **/
@Slf4j
@RestController
public class UserMenuController {

    @Resource
    private UserMenuService userMenuService;

    private final Github github;

    private final Gitee gitee;

    private static final Logger logger = LoggerFactory.getLogger(UserMenuController.class);


    public UserMenuController(Github github, Gitee gitee) {
        this.github = github;
        this.gitee = gitee;
    }


    /**
     * 根据RoleId获取菜单list
     * @return 菜单list
     */
    @GetMapping("/getRoleMenu")
    public Result<List<SysMenuVo>> getRoleMenu(){
        List<SysMenuVo> sysMenuVos = userMenuService.getRoleMenu();
        return new Result<>(sysMenuVos);
    }

    /**
     * 让用户跳转到 GitHub
     * 这里不能加@ResponseBody，因为这里是要跳转而不是返回响应
     * 另外LoginController也不能用@RestController修饰
     *
     * @return 跳转url
     */
    @GetMapping("/accounts/github/login")
    public Result<String> authorize(){
        String url = github.getAuthorizeUrl() +
                "?client_id=" + github.getClientId() +
                "&redirect_uri=" + github.getRedirectUrl();
        log.info("授权url:{}", url);
        return new Result<>(url);
    }

    /**
     * 让用户跳转到 GitHub
     * 这里不能加@ResponseBody，因为这里是要跳转而不是返回响应
     * 另外LoginController也不能用@RestController修饰
     *
     * @return 跳转url
     */
    @GetMapping("/accounts/gitee/login")
    public Result<String> authorizeGitee(){
        String url = gitee.getAuthorizeUrl() +
                "?client_id=" + gitee.getClientId() +
                "&redirect_uri=" + gitee.getRedirectUrl()+
                "&response_type=code";
        log.info("授权url:{}", url);
        return new Result<>(url);
    }


}
