package com.peng44.web_template.modules.article.Vo;

import com.peng44.web_template.modules.article.entity.Article;
import lombok.Data;

/**
 * @program: web_template
 * @description: 文章视图模型
 * @author: nile
 * @create: 2020-09-23 17:15
 **/
@Data
public class ArticleVo extends Article {

    /**
     * 文章类型名
     */
    private String categoryName;

}
