package com.peng44.web_template.modules.user.service.impl;

import com.peng44.web_template.modules.user.entity.SysUserRole;
import com.peng44.web_template.modules.user.mapper.SysUserRoleMapper;
import com.peng44.web_template.modules.user.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: web_template
 * @description: SysUserRoleServiceImpl
 * @author: nile
 * @create: 2020-09-13 18:51
 **/
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysUserRole> listByUserId(Long userId) {
        return userRoleMapper.listByUserId(userId);
    }

    @Override
    public void setUserRole(Long userId) {

    }
}
