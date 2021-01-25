package com.peng44.web_template.modules.user.service.impl;

import com.peng44.web_template.modules.user.entity.SysUser;
import com.peng44.web_template.modules.user.mapper.SysUserMapper;
import com.peng44.web_template.modules.user.mapper.SysUserRoleMapper;
import com.peng44.web_template.modules.user.service.SysUserService;
import com.peng44.web_template.security.oauth2client.bo.GithubBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: web_template
 * @description: SysUserServiceImpl
 * @author: nile
 * @create: 2020-09-13 18:52
 **/
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper userMapper;

    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Override
    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public List<SysUser> getUserInfo(){
        return  userMapper.getUserInfo();
    }

    @Override
    public SysUser selectByMobile(String mobile){return  userMapper.selectByMobile(mobile);}

    @Override
    public void setUserByGithub(GithubBo githubBo) {
        userMapper.setUserByGithub(githubBo);
        userRoleMapper.setUserRole(githubBo.getUserId());
    }


}
