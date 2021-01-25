package com.peng44.web_template.security.handles;

import com.alibaba.fastjson.JSON;
import com.peng44.web_template.commons.utils.Result;
import com.peng44.web_template.modules.user.entity.SysUser;
import com.peng44.web_template.modules.user.mapper.SysUserMapper;
import com.peng44.web_template.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author: nile
 * @create: 2020-03-22 22:19
 *自定义 CustomAuthenticationSuccessHandler 类来实现 AuthenticationSuccessHandler 接口，用来处理认证成功后逻辑：
 *onAuthenticationSuccess() 方法返回认证成功信息。
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtProvider jwtProvider;

    @Resource
    private SysUserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String jwtToken = jwtProvider.generateJwtToken(authentication);
        response.getWriter().write(JSON.toJSONString(new Result<Object>(jwtToken,authentication.getName())));
    }
}