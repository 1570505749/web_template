package com.peng44.web_template.modules.login.mapper;

import com.peng44.web_template.modules.user.entity.SysUser;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @program: web_template
 * @description: 用户登录mapper
 * @author: nile
 * @create: 2020-09-12 18:27
 **/
@Mapper
public interface LoginMapper {

    /**
     * 获取用户登录信息
     * @return
     */
    @Select("select * from sys_user where username = #{userName}")
    SysUser getLoginInfo(String userName);

    @Select("SELECT * FROM sys_user WHERE github_id = #{id}")
    SysUser selectByGithubId(Long id);

}
