package com.peng44.web_template.modules.article.mapper;

import com.peng44.web_template.modules.article.entity.Flag;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: web_template
 * @description: 计划dao
 * @author: nile
 * @create: 2020-10-07 19:02
 **/
@Mapper
@CacheNamespace
public interface FlagMapper {

    /**
     * 获取所有计划
     * @return plan’s list
     */
    @Select("select * from user_flag where is_deleted = 0 order by created_time desc")
    List<Flag> getFlagList();

    /**
     * 写入计划
     * @param content plan's content
     */
    @Insert("insert into user_flag(content) values(#{content})")
    void setFlag(String content);
}
