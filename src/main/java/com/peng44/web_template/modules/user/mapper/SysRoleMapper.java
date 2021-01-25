package com.peng44.web_template.modules.user.mapper;

import com.peng44.web_template.modules.user.entity.SysRole;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Nile
 */
@Mapper
@CacheNamespace
public interface SysRoleMapper {

    @Select("SELECT * FROM sys_role WHERE role_id = #{id}")
    SysRole selectById(Integer id);

    @Select("SELECT * FROM sys_role WHERE role_name = #{name}")
    SysRole selectByName(String name);

}
