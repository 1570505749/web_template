package com.peng44.web_template.modules.article.controller;

import com.peng44.web_template.commons.enums.ResultEnum;
import com.peng44.web_template.commons.utils.Result;
import com.peng44.web_template.modules.article.entity.Flag;
import com.peng44.web_template.modules.article.service.FlagService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: web_template
 * @description: flag控制类
 * @author: nile
 * @create: 2020-10-07 19:07
 **/
@RestController
@RequestMapping("/flag")
public class FlagController {

    @Resource
    private FlagService flagService;

    @GetMapping("/getFlagList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result<List<Flag>> getFlagList(){
        List<Flag> flags = flagService.getFlagList();
        return new Result<>(flags);
    }

    @PostMapping("/setFlag")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result<Object> setFlag(@RequestBody Flag flag){
        flagService.setFlag(flag.getContent());
        return new Result<>(ResultEnum.POST_SUCCESS);
    }
}
