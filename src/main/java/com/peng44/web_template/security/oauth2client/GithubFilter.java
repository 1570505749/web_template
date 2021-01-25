package com.peng44.web_template.security.oauth2client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.peng44.web_template.security.customauths.CustomUserDetailsServiceImpl;
import com.peng44.web_template.security.oauth2client.bo.GithubBo;
import com.peng44.web_template.security.oauth2client.clientinfo.Github;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: web_template
 * @description: github认证器
 * @author: nile
 * @create: 2020-11-03 21:04
 **/
public class GithubFilter extends OncePerRequestFilter {

    @Resource
    private Github github;

    private AuthenticationFailureHandler authenticationFailureHandler;

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    private CustomUserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(GithubFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean result = StringUtils.equals("/login/github", httpServletRequest.getRequestURI());
        if(result && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "get")){
            try {
                    String code = httpServletRequest.getParameter("code");
                    String accessToken = validateGithubCode(code);
                    String userInfo = getGithubUserInfo(accessToken);
                    GithubBo githubBo = JSON.parseObject(userInfo,GithubBo.class);
                    UserDetails user = userDetailsService.loadUserByGithubId(githubBo);
                    if(user == null){
                        throw new BadCredentialsException("用户不存在！！");
                    }
                    Oauth2AuthenticationToken auth2AuthenticationToken = new Oauth2AuthenticationToken(user.getUsername(),user.getAuthorities());
                    authenticationSuccessHandler.onAuthenticationSuccess(httpServletRequest,httpServletResponse,auth2AuthenticationToken);
                    return;
            }catch (Exception e) {
                logger.error("GithubFilter error:{}",e.getMessage());
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,new BadCredentialsException(e.getMessage()));
                return;
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private String validateGithubCode(String code) {
        String url = github.getAccessTokenUrl() +
                "?client_id=" + github.getClientId() +
                "&client_secret=" + github.getClientSecret() +
                "&code=" + code +
                "&grant_type=authorization_code";
        logger .info("getAccessToken url:{}", url);
        // 构建请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        // 指定响应返回json格式
        requestHeaders.add("accept", "application/json");
        // 构建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        // post 请求方式
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        String responseStr = response.getBody();
        logger .info("responseStr={}", responseStr);
        // 解析响应json字符串
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        String accessToken = jsonObject.getString("access_token");
        logger .info("accessToken={}", accessToken);
        return accessToken;
    }

    private String getGithubUserInfo(String accessToken) {
        String url = github.getUserInfoUrl();
        logger.info("getUserInfo url:{}", url);
        // 构建请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        // 指定响应返回json格式
        requestHeaders.add("accept", "application/json");
        // AccessToken放在请求头中
        requestHeaders.add("Authorization", "token " + accessToken);
        // 构建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        // get请求方式
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String userInfo = response.getBody();
        logger.info("userInfo={}", userInfo);
        return userInfo;
    }

    public void setUserDetailsService(CustomUserDetailsServiceImpl customUserDetailsService){
        this.userDetailsService = customUserDetailsService;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler){
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler authenticationSuccessHandler){
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }
}
