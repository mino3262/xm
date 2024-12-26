package com.app.xm.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.xm.common.Result;

@ControllerAdvice("com.app.xm.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody  //返回json
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error();
    } 

    @ExceptionHandler(CustomException.class)
    @ResponseBody  //返回json
    public Result error(CustomException e){
        return Result.error(e.getCode(),e.getMsg());
    } 
}
