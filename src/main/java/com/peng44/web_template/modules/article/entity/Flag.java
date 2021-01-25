package com.peng44.web_template.modules.article.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: web_template
 * @description: 计划
 * @author: nile
 * @create: 2020-10-07 18:59
 **/
@Data
public class Flag implements Serializable {

    private Long flagId;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 是否删除，0否1是 默认0
     */
    private Integer isDeleted;
}
