package com.peng44.web_template.modules.user.service;

import com.peng44.web_template.modules.user.entity.SysUser;
import com.peng44.web_template.security.oauth2client.bo.GithubBo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SysUserService {

     SysUser selectById(Integer id);

     SysUser selectByName(String name);

     List<SysUser> getUserInfo();

     SysUser selectByMobile(String mobile);

     void setUserByGithub(GithubBo githubBo);

}