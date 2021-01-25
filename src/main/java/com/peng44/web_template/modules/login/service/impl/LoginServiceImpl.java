package com.peng44.web_template.modules.login.service.impl;

import com.peng44.web_template.modules.user.entity.SysUser;
import com.peng44.web_template.modules.login.mapper.LoginMapper;
import com.peng44.web_template.modules.login.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: web_template
 * @description: 用户登录service 实现类
 * @author: nile
 * @create: 2020-09-12 18:28
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Override
    public SysUser getLoginInfo(String userName) {
        return loginMapper.getLoginInfo(userName);
    }

    @Override
    public SysUser selectByGithubId(Long id) {
        return loginMapper.selectByGithubId(id);
    }
}
