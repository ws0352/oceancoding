package com.oceancoding.ws.ocean.ExceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e){
        System.out.println("异常原因是："+ e);
        return e.getMessage();
    }
}
