package com.peng44.web_template.modules.article.service;

import com.peng44.web_template.modules.article.entity.Category;
import org.apache.ibatis.annotations.Select;

/**
 * @program: web_template
 * @description: 文章类别业务层接口
 * @author: nile
 * @create: 2020-09-25 17:08
 **/
public interface CategoryService {

    /**
     * 获取所有可用文章类别
     * @return
     */
    Category[] getAllCategory();
}
