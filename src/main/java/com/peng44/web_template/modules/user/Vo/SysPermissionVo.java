package com.peng44.web_template.modules.user.Vo;

import com.peng44.web_template.modules.user.entity.SysPermission;
import lombok.Data;

/**
 * @program: web_template
 * @description: 权限Vo
 * @author: nile
 * @create: 2020-10-06 15:34
 **/
@Data
public class SysPermissionVo extends SysPermission {

    private String name;

    private String mark;

}
