package com.peng44.web_template.modules.log.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Nile
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Log {
    /**
     * 日志id
     */
    private Integer logId;

    /**
     * 请求路径
     */
    private String logUrl;

    /**
     * 参数
     */
    private String logParams;

    /**
     * 访问状态，1正常0异常
     */
    private Integer logStatus;

    /**
     * 异常信息
     */
    private String logMessage;

    /**
     * 请求方式，get、post等
     */
    private String logMethod;

    /**
     * 响应时间
     */
    private Long logTime;

    /**
     * 返回值
     */
    private String logResult;

    /**
     * 请求ip
     */
    private String logIp;

    /**
     * 创建时间
     */
    private String createdTime;
}
