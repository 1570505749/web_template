package com.peng44.web_template.modules.article.controller;

import com.peng44.web_template.commons.utils.Result;
import com.peng44.web_template.modules.article.entity.Category;
import com.peng44.web_template.modules.article.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: web_template
 * @description: 文章类别访问控制类
 * @author: nile
 * @create: 2020-09-25 17:15
 **/
@RestController
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/visitor/getAllCategory")
    public Result<Category[]> getAllCategory(){
        Category[] categories = categoryService.getAllCategory();
        return new Result<>(categories);
    }
}
