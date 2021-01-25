package com.peng44.web_template.security.oauth2client.clientinfo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @program: web_template
 * @description: Gitee
 * @author: nile
 * @create: 2020-11-04 20:22
 **/
@Data
@Component
public class Gitee {

    private String clientId ="025a90eb89adef5b8f2081bf7088ebbed8aa8c8f64eb417517be0a0c0d4e63a0";

    private String clientSecret ="f58912861238821e48ddcf97ada4df53c9ce457db87d386032b1a5c8f1372058";

    private String authorizeUrl="https://gitee.com/oauth/authorize";

    private String redirectUrl ="http://localhost:8045/login/gitee";

    private String accessTokenUrl ="https://gitee.com/oauth/token";

    private String userInfoUrl="https://gitee.com/api/v5/user";
}
