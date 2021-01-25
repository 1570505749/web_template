package com.peng44.web_template.modules.article.service;

import com.peng44.web_template.modules.article.entity.Flag;

import java.util.List;

/**
 * @program: web_template
 * @description: 业务逻辑接口
 * @author: nile
 * @create: 2020-10-07 19:09
 **/
public interface FlagService {

    /**
     * 获取所有计划
     * @return
     */
    List<Flag> getFlagList();

    /**
     * 写入计划
     * @param content
     */
    void setFlag(String content);
}
