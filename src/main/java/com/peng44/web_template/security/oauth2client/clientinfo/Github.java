package com.peng44.web_template.security.oauth2client.clientinfo;

import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * @program: report
 * @description: GithubApi
 * @author: nile
 * @create: 2020-11-02 18:31
 **/
@Data
@Component
public class Github {

    private String clientId ="bb1df7760ca020a0aa4a";

    private String clientSecret ="a6baaf59ca7b57456e07fa95d0a9aa40fddc7b70";

    private String authorizeUrl="https://github.com/login/oauth/authorize";

    private String redirectUrl ="http://www.peng55.xyz/login/github";

    private String accessTokenUrl ="https://github.com/login/oauth/access_token";

    private String userInfoUrl="https://api.github.com/user";


}
