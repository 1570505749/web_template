package com.peng44.web_template.security.handles;

import com.peng44.web_template.commons.enums.ResultEnum;
import com.peng44.web_template.commons.utils.Result;
import com.peng44.web_template.configs.UserDefinedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: web_template
 * @description: Spring mvc全局异常处理器
 * @author: nile
 * @create: 2020-10-18 14:37
 **/
@RestControllerAdvice
public class GlobalExceptionHandler<T> {

    @ResponseStatus(HttpStatus.OK)
    public Result<Object> sendSuccessResponse(){
        return new Result<>(ResultEnum.GET_SUCCESS);
    }

    @ResponseStatus(HttpStatus.OK)
    public Result<Object> sendSuccessResponse(T data) {
        return new Result<>(data);
    }

    @ExceptionHandler(UserDefinedException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public Result<Object>  sendErrorResponseUserDefined(Exception exception){
        return new Result<>(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result<Object>  sendErrorResponseSystem(Exception exception){
        if (exception instanceof HttpRequestMethodNotSupportedException) {
            return new Result<>(ResultEnum.METHOD_NOT_ALLOWED,exception.getMessage());
        }
        return new Result<>(ResultEnum.UNKNOWN_MISTAKE,exception.getMessage());
    }

}
