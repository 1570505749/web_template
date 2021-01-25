package com.peng44.web_template.modules.user.service.impl;

import com.peng44.web_template.modules.user.entity.SysRole;
import com.peng44.web_template.modules.user.mapper.SysRoleMapper;
import com.peng44.web_template.modules.user.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: web_template
 * @description: SysRoleService
 * @author: nile
 * @create: 2020-09-13 18:51
 **/
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public SysRole selectById(Integer id){
        return roleMapper.selectById(id);
    }

    @Override
    public SysRole selectByName(String name) {
        return roleMapper.selectByName(name);
    }
}
