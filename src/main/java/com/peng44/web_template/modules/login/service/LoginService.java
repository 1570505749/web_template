package com.peng44.web_template.modules.login.service;

import com.peng44.web_template.modules.user.entity.SysUser;

/**
 * @program: web_template
 * @description: 用户登录Service
 * @author: nile
 * @create: 2020-09-12 18:28
 **/
public interface LoginService {

    /**
     * 获取用户登录信息
     * @return 返回用户对象
     */
    SysUser getLoginInfo(String userName);

    SysUser selectByGithubId(Long id);
}
