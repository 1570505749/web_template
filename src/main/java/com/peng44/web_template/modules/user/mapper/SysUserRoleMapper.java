package com.peng44.web_template.modules.user.mapper;

import com.peng44.web_template.modules.user.entity.SysUserRole;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@CacheNamespace
public interface SysUserRoleMapper {
    @Select("SELECT * FROM sys_user_role WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(Long userId);

    @Insert("insert into sys_user_role(user_id,role_id) values(#{userId},2)")
    void setUserRole(Long userId);
}