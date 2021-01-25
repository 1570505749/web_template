package com.peng44.web_template.modules.user.service;

import com.peng44.web_template.modules.user.Vo.SysMenuVo;
import com.peng44.web_template.modules.user.entity.SysMenu;

import java.util.List;

/**
 * @program: web_template
 * @description: 菜单service
 * @author: nile
 * @create: 2020-09-13 18:49
 **/
public interface UserMenuService {

     /**
      * 根据ID获取菜单路径
      * @param menuId
      * @return
      */
     String getMenuName(Long menuId);

     /**
      * 根据RoleId获取菜单list
      * @return
      */
     List<SysMenuVo> getRoleMenu();
}
