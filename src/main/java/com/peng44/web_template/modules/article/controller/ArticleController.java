package com.peng44.web_template.modules.article.controller;

import com.peng44.web_template.commons.enums.ResultEnum;
import com.peng44.web_template.commons.utils.Page;
import com.peng44.web_template.commons.utils.Result;
import com.peng44.web_template.modules.article.entity.Article;
import com.peng44.web_template.modules.article.Vo.ArticleVo;
import com.peng44.web_template.modules.article.service.ArticleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: web_template
 * @description: 文章控制层
 * @author: nile
 * @create: 2020-09-23 13:12
 **/
@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * PreAuthorize("hasPermission('article:list','r')")
     */
    @GetMapping("/visitor/getArticle/{id}")
    public Result<ArticleVo> getArticle(@PathVariable Long id){
        ArticleVo articleVo = articleService.getArticle(id);
        return new Result<>(articleVo);
    }

    @PostMapping("/visitor/getByPage")
    public Result<Page<ArticleVo>> getByPage(@RequestBody Page<ArticleVo> page){
        page = articleService.getByPage(page);
        return new Result<>(page);
    }

    @PostMapping("/article/saveArticle")
    @PreAuthorize("hasPermission('article:list','c')")
    public Result<String> saveArticle(@RequestBody Article article){
        articleService.saveArticle(article);
        return new Result<>(ResultEnum.POST_SUCCESS);
    }

}
