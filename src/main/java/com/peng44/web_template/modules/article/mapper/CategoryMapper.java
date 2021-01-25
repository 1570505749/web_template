package com.peng44.web_template.modules.article.mapper;

import com.peng44.web_template.modules.article.entity.Category;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @program: web_template
 * @description: 文章类别数据访问层
 * @author: nile
 * @create: 2020-09-25 17:07
 **/
@Mapper
@CacheNamespace
public interface CategoryMapper {

    /**
     * 获取所有可用文章类别
     * @return article's arrays of category
     */
    @Select("select * from user_category where is_deleted = 0")
    Category[] getAllCategory();

}
