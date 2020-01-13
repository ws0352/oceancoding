package com.oceancoding.ws.ocean.annotations;

import java.lang.annotation.*;


/*
* 需要签名
* 使用方法 @RequireSignature
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireSignature{

}
