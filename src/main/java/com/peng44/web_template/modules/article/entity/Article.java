package com.peng44.web_template.modules.article.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Version;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: web_template
 * @description: 文章实体类
 * @author: nile
 * @create: 2020-09-23 13:14
 **/
@Data
public class Article implements Serializable {

    static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 文章标题
     */
    private String articleTitle;


    /**
     * 文章封面
     */
    private String articleImage;


    /**
     * 编辑内容 == 源码
     */
    private String editContent;


    /**
     * 文章类型 类型id
     */
    private Long articleCategory;

    /**
     * 阅读量
     */
    private Integer readCount;

    /**
     * 评论量
     */
    private Integer commentCount;

    /**
     * 文章来源
     */
    private String articleSource;

    /**
     * 文章创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

    /**
     * 文章修改时间
     */
    private String updateTime;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;

    /**
     * 是否启用，0否1是 默认1
     */
    private Integer isEnabled;

    /**
     * 是否删除，0否1是 默认0
     */
    private Integer isDeleted;


}
