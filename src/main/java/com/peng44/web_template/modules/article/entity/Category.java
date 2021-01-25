package com.peng44.web_template.modules.article.entity;

import lombok.Data;
import org.springframework.data.annotation.Version;

import java.io.Serializable;

/**
 * @program: web_template
 * @description: 文章类型实体类
 * @author: nile
 * @create: 2020-09-25 17:02
 **/
@Data
public class Category implements Serializable {

    /**
     * 分类id 编号
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类文章数
     */
    private Integer articleCount;


    /**
     * 分类创建时间
     */
    private String createdTime;

    /**
     * 分类修改时间
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
