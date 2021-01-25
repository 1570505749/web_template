package com.peng44.web_template.modules.article.mapper;

import com.peng44.web_template.commons.utils.Page;
import com.peng44.web_template.modules.article.entity.Article;
import com.peng44.web_template.modules.article.Vo.ArticleVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: web_template
 * @description: 文章数据访问接口
 * @author: nile
 * @create: 2020-09-23 13:14
 **/
@Mapper
@CacheNamespace
public interface ArticleMapper {

    /**
     *获取指定文章ID的文章
     * @param articleId article's id
     * @return articleVo
     */
    @Select("select a.*,category_name from user_article a  left join  user_category b on a.article_category = b.category_id " +
            "where a.article_id = #{articleId} and a.is_deleted = 0")
    ArticleVo getArticle(Long articleId);

    /**
     * 分页查询数据
     * @param page page object :  The format of the requested data
     * @return articles data
     */
    @Select("<script>" +
            "select LEFT(a.edit_content,200) as edit_content,a.*,category_name from user_article a  left join  user_category b on a.article_category = b.category_id \n" +
            "where  a.is_deleted = 0" +
            "<if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "order by ${sortColumn} ${sortMethod}\n" +
            "</if>\n" +
            "limit #{index}, #{pageSize}" +
            "</script>")
    List<ArticleVo> getByPage(Page<ArticleVo> page);

    /**
     * 获取文章总数
     * @return
     */
    @Select("select count(*) from user_article a  left join  user_category b on a.article_category = b.category_id  where  a.is_deleted = 0")
    int getCountByPage();

    /**
     * 增加文章
     * @param article
     */
    @Insert("insert into user_article(article_title,article_image,edit_content,article_category) values(#{articleTitle},#{articleImage},#{editContent},#{articleCategory})")
    void saveArticle(Article article);
}
