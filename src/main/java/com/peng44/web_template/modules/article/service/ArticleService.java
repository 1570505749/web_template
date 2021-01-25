package com.peng44.web_template.modules.article.service;

import com.peng44.web_template.commons.utils.Page;
import com.peng44.web_template.modules.article.entity.Article;
import com.peng44.web_template.modules.article.Vo.ArticleVo;

/**
 * @program: web_template
 * @description: 文章业务层接口
 * @author: nile
 * @create: 2020-09-23 13:15
 **/
public interface ArticleService {

    /**
     *获取指定文章ID的文章
     * @param articleId
     * @return
     */
    ArticleVo getArticle(Long articleId);

    /**
     *分页查询数据
     *@param page
     *@return
     */
    Page<ArticleVo> getByPage(Page<ArticleVo> page);

    /**
     * 增加文章
     * @param article
     */
    void saveArticle(Article article);

}
