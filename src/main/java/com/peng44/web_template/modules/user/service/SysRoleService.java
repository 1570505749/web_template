package com.peng44.web_template.modules.user.service;

import com.peng44.web_template.modules.user.entity.SysRole;

public interface SysRoleService {

    SysRole selectById(Integer id);

    SysRole selectByName(String name);
}