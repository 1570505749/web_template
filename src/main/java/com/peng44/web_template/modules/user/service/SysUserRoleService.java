package com.peng44.web_template.modules.user.service;

import com.peng44.web_template.modules.user.entity.SysUserRole;
import com.peng44.web_template.modules.user.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface SysUserRoleService {

    List<SysUserRole> listByUserId(Long userId);

    void setUserRole(Long userId);
}