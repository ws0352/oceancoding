package com.oceancoding.ws.ocean.exceptionHandler;

import com.oceancoding.ws.ocean.exceptionHandler.enums.CommonEnum;
import com.oceancoding.ws.ocean.responseData.GlobalResultBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
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
     * 操作数据库出现异常:名称重复，外键关联
     * */
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    public GlobalResultBody exceptionHandler(HttpServletRequest request, DataIntegrityViolationException e){
        logger.error("发生数据库异常！原因是：", e);
        return GlobalResultBody.error(CommonEnum.SQL_HANDLE_ERROR);

    }
    /*
     * 其他异常
     * */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public GlobalResultBody exceptionHandler(HttpServletRequest request, Exception e){
        logger.error("未知异常！原因是：", e);
        return GlobalResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);

    }
}
