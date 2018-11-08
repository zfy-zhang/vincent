package com.vincent.personal.controller;

import com.vincent.personal.exception.TipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/7
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = TipException.class)
    public String tipException(Exception e){
        logger.error("find exception:e+{}",e.getMessage());
        return "comm/error_500";
    }

    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e){
        logger.error("find exception:e+{}",e.getMessage());
        return "comm/error_404";
    }

}
