package com.peng44.web_template.security.oauth2client.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: web_template
 * @description: github返回用户业务实体
 * @author: nile
 * @create: 2020-11-03 21:36
 **/
@Getter
@Setter
public class GithubBo implements Serializable {

    private Long userId;

    private String login;

    private Long id;

    private String avatarUrl;

    private String name;

    private String blog;

    private String location;

    private String bio;

}
