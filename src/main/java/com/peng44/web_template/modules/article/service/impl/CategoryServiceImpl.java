package com.peng44.web_template.modules.article.service.impl;

import com.peng44.web_template.modules.article.entity.Category;
import com.peng44.web_template.modules.article.mapper.CategoryMapper;
import com.peng44.web_template.modules.article.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: web_template
 * @description: 文章类别业务逻辑实现类
 * @author: nile
 * @create: 2020-09-25 17:09
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Category[] getAllCategory() {
        return categoryMapper.getAllCategory();
    }

}
