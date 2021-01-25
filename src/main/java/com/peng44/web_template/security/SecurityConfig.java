package com.peng44.web_template.security;

import com.peng44.web_template.security.customauths.CustomAuthenticationEntryPoint;
import com.peng44.web_template.security.customauths.CustomUserDetailsServiceImpl;
import com.peng44.web_template.security.handles.CustomAccessDeniedHandler;
import com.peng44.web_template.security.handles.CustomAuthenticationFailureHandler;
import com.peng44.web_template.security.handles.CustomAuthenticationSuccessHandler;
import com.peng44.web_template.security.handles.CustomLogoutSuccessHandler;
import com.peng44.web_template.security.jwt.CustomAuthenticationFilter;
import com.peng44.web_template.security.jwt.JwtAuthTokenFilter;
import com.peng44.web_template.security.oauth2client.GiteeFilter;
import com.peng44.web_template.security.oauth2client.GithubFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

import javax.annotation.Resource;

/**
 * @program: web_template
 * @description: Spring Security 配置类
 * @author: nile
 * @create: 2020-09-12 18:54
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Resource
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Resource
    private CustomUserDetailsServiceImpl userDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private CustomLogoutSuccessHandler logoutSuccessHandler;


    /**
     * 设置密码加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 配置地址栏不能识别 // 的情况
     * @return
     */
    @Bean
    public DefaultHttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

    /**
     * 注入jwt
     * @return
     */
    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    /**
     * 注入ValidateCodeFilter
     * @return
     */
    @Bean
    public GithubFilter authenticationGithubFilterFilter() {
        GithubFilter validateCodeFilter = new GithubFilter();
        validateCodeFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        validateCodeFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        validateCodeFilter.setUserDetailsService(userDetailsService);
        return validateCodeFilter;
    }

    /**
     * 注入ValidateCodeFilter
     * @return
     */
    @Bean
    public GiteeFilter authenticationGiteeFilterFilter() {
        GiteeFilter validateCodeFilter = new GiteeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        validateCodeFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        validateCodeFilter.setUserDetailsService(userDetailsService);
        return validateCodeFilter;
    }


    /**
     * 注入CustomAuthenticationFilter
     * @return 返回
     */
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        filter.setFilterProcessesUrl("/login");
        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    /**
     * 注入AccessDeniedHandler
     * @return
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    /**
     * @return share AuthenticationManager for web and oauth
     * @throws Exception 异常抛出
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 自定义DaoAuthenticationProvider
     * @return
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers("/visitor/**","/accounts/**","/login/github").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                //自定义无权限处理器
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                //把自定义过滤器加到security过滤器链中
                .addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticationGithubFilterFilter(),LogoutFilter.class)
                .addFilterAfter(authenticationJwtTokenFilter(), LogoutFilter.class)
                .addFilterBefore(authenticationGiteeFilterFilter(),UsernamePasswordAuthenticationFilter.class)
                //禁止security自己创建session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.logout()
                //指定退出登录url
                .logoutUrl("/logout")
                //清除cookies
                //.deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler);

        // 关闭CSRF跨域,开发和测试时较方便，上线时需要开启
        http.cors().and().csrf().disable();

    }

    @Override
    public void configure(WebSecurity web){
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**,/static/**","/templates/**","/img/**");
    }
}
