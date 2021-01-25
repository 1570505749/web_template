package com.peng44.web_template.modules.user.mapper;

import com.peng44.web_template.modules.user.entity.SysUser;
import com.peng44.web_template.security.oauth2client.bo.GithubBo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
@CacheNamespace
public interface SysUserMapper {
    @Select("SELECT * FROM sys_user WHERE user_id = #{id}")
    SysUser selectById(Integer id);

    @Select("SELECT * FROM sys_user WHERE username = #{name}")
    SysUser selectByName(String name);

    @Select("SELECT * FROM sys_user WHERE phone= #{mobile}")
    SysUser selectByMobile(String mobile);

    @Select("SELECT * FROM sys_user")
    List<SysUser> getUserInfo();

    /**
     * 注册github用户
     * @param githubBo
     */
    @Insert("insert into sys_user(username,password,header,github_id,description,address) values (#{login},'$2a$10$pwUFaYUOBy61xI/iBN5HM.AEwnqWKwOJvV3RfuCoGhzHCmqo6cC16',#{avatarUrl},#{id}," +
            "#{bio},#{location})")
    @Options(useGeneratedKeys=true, keyProperty="userId", keyColumn="user_id")
    void setUserByGithub(GithubBo githubBo);

}