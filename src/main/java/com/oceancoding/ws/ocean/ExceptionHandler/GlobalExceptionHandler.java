package com.oceancoding.ws.ocean.ExceptionHandler;

import com.oceancoding.ws.ocean.ExceptionHandler.Enum.CommonEnum;
import com.oceancoding.ws.ocean.ResponseData.GlobalResultBody;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger =  LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*
    * 处理自定义的业务异常
    * */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public GlobalResultBody bizExceptionHandler(HttpServletRequest request, BizException e){
        logger.error("发生业务异常！原因是：{}", e.getErrorMsg());
        return GlobalResultBody.error(e.getErrorCode(), e.getErrorMsg());
    }

    /*
     * 处理空指针异常
     * */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public GlobalResultBody exceptionHandler(HttpServletRequest request, NullPointerException e){
        logger.error("发生空指针异常！原因是：", e);
        return GlobalResultBody.error(CommonEnum.BODY_NOT_MATCH);

    }
    /*
     * 处理空指针异常
     * */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public GlobalResultBody exceptionHandler(HttpServletRequest request, Exception e){
        logger.error("未知异常！原因是：", e);
        return GlobalResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);

    }
}
