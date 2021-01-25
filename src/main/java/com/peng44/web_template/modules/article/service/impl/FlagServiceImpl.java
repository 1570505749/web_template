package com.peng44.web_template.modules.article.service.impl;

import com.peng44.web_template.modules.article.entity.Flag;
import com.peng44.web_template.modules.article.mapper.FlagMapper;
import com.peng44.web_template.modules.article.service.FlagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: web_template
 * @description: Flag业务逻辑实现类
 * @author: nile
 * @create: 2020-10-07 19:09
 **/
@Service
public class FlagServiceImpl implements FlagService {

    @Resource
    private FlagMapper flagMapper;

    @Override
    public List<Flag> getFlagList() {
        return flagMapper.getFlagList();
    }

    @Override
    public void setFlag(String content) {
        flagMapper.setFlag(content);
    }
}
