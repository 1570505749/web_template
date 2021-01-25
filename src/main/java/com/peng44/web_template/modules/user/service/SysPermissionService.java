package com.peng44.web_template.modules.user.service;

import com.peng44.web_template.modules.user.Vo.SysPermissionVo;
import com.peng44.web_template.modules.user.entity.SysPermission;

import java.util.List;

public interface SysPermissionService {

    /**
     * 获取指定角色所有权限
     */
    List<SysPermissionVo> listByRoleId(Long roleId);
}