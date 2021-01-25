package com.peng44.web_template.security.handles;

import com.alibaba.fastjson.JSON;
import com.peng44.web_template.commons.enums.ResultEnum;
import com.peng44.web_template.commons.utils.Result;
import com.peng44.web_template.configs.UserDefinedException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: web_template
 * @description: 自定义无权限处理器
 * @author: nile
 * @create: 2020-09-13 17:15
 **/
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(ResultEnum.NOT_AUTHORITY.getCode());
        response.getWriter().write(JSON.toJSONString(new Result<>(ResultEnum.NOT_AUTHORITY)));
    }

}
