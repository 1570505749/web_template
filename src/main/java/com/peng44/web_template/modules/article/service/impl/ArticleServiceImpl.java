package com.peng44.web_template.modules.article.service.impl;

import com.peng44.web_template.commons.utils.Page;
import com.peng44.web_template.modules.article.entity.Article;
import com.peng44.web_template.modules.article.Vo.ArticleVo;
import com.peng44.web_template.modules.article.mapper.ArticleMapper;
import com.peng44.web_template.modules.article.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: web_template
 * @description: 文章业务实现类
 * @author: nile
 * @create: 2020-09-23 13:15
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public ArticleVo getArticle(Long articleId) {
        return articleMapper.getArticle(articleId);
    }

    @Override
    public Page<ArticleVo> getByPage(Page<ArticleVo> page) {
        page.setList(articleMapper.getByPage(page));
        page.setTotalCount(articleMapper.getCountByPage());
        return page;
    }

    @Override
    public void saveArticle(Article article) {
        articleMapper.saveArticle(article);
    }

}
