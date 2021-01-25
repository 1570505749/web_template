package com.peng44.web_template.security.customauths;

import com.alibaba.fastjson.JSONObject;
import com.peng44.web_template.modules.user.entity.SysRole;
import com.peng44.web_template.modules.user.entity.SysUser;
import com.peng44.web_template.modules.login.service.LoginService;
import com.peng44.web_template.modules.user.entity.SysUserRole;
import com.peng44.web_template.modules.user.service.SysRoleService;
import com.peng44.web_template.modules.user.service.SysUserRoleService;
import com.peng44.web_template.modules.user.service.SysUserService;
import com.peng44.web_template.security.oauth2client.bo.GithubBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: nile
 * @create: 2020-03-22 22:19
 *Security 注册用户类
 */

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Autowired
    private SysRoleService roleService;

    /**
     * 添加认证信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = loginService.getLoginInfo(username);
        return generateUserDetails(user);
    }

    /**
     * 添加认证信息
     */
    public UserDetails loadUserByGithubId(GithubBo githubBo) throws UsernameNotFoundException {
        SysUser user = loginService.selectByGithubId(githubBo.getId());
        if(user == null) {
            sysUserService.setUserByGithub(githubBo);
            user = loginService.selectByGithubId(githubBo.getId());
        }
        return generateUserDetails(user);
    }

    public UserDetails generateUserDetails(SysUser user){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 判断用户是否存在
        if(user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 添加权限
        List<SysUserRole> userRoles = userRoleService.listByUserId(user.getUserId());
        for (SysUserRole userRole : userRoles) {
            SysRole role = roleService.selectById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        // 返回
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }
}