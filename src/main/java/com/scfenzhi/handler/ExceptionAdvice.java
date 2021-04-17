package com.scfenzhi.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author DaiYi
 * @Date 2021/4/14 10:32
 */
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public String defaultExceptionAdvice(Exception exception) {
        exception.printStackTrace();
        return "error";
    }

}
