package com.peng44.web_template.security.customauths;

import com.alibaba.fastjson.JSON;
import com.peng44.web_template.commons.enums.ResultEnum;
import com.peng44.web_template.commons.utils.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: web_template
 * @description: 处理用户未登录
 * @author: nile
 * @create: 2020-10-17 16:34
 **/

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException{
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(ResultEnum.NOT_LOGIN.getCode());
            response.getWriter().write(JSON.toJSONString(new Result<>(ResultEnum.NOT_LOGIN)));
    }
}

