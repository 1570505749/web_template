package com.peng44.web_template.configs;

import com.peng44.web_template.commons.enums.ResultEnum;
import org.springframework.security.core.AuthenticationException;

/**
 * @program: web_template
 * @description: 自定义异常
 * @author: nile
 * @create: 2020-10-18 14:39
 **/
public class UserDefinedException extends RuntimeException {

        private ResultEnum exception;

        public UserDefinedException(ResultEnum exception){
            this.exception = exception;
        }

        public ResultEnum getException() {
            return exception;
        }

        public void setException(ResultEnum exception) {
            this.exception = exception;
        }
}
