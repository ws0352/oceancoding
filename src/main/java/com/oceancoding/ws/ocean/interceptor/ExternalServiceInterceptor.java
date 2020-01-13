package com.oceancoding.ws.ocean.interceptor;

import com.oceancoding.ws.ocean.annotations.RequireSignature;
import com.oceancoding.ws.ocean.exceptionHandler.BizException;
import com.oceancoding.ws.ocean.exceptionHandler.enums.CommonEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class ExternalServiceInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(getClass());
    //定义appid及密钥
    private static final String appid = "19827931182981731";
    private static final String appsecret = "sahqkwnkjaschailkdnakhsk.s;aakdkhahda";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Method method = ((HandlerMethod) handler).getMethod();
        if(AnnotatedElementUtils.isAnnotated(method, RequireSignature.class)){
            long now = System.currentTimeMillis();
            String timestamp = request.getParameter("timestamp");
            Long timestampGain = Long.parseLong("timestamp");
            if(Math.abs(now - timestampGain) > 3_000_000){
                logger.error("签名时间【" + timestampGain +"】已经过期，请重试！");
                throw new BizException(CommonEnum.SIGNATURE_EXPIRED);
            }
            String nonce_str = request.getParameter("nonce_str");
            String signGain = request.getParameter("sign");
            if (nonce_str.isEmpty() || signGain.isEmpty()){
                logger.error("签名不存在，请稍后再试");
                throw new BizException(CommonEnum.SIGNATURE_NOT_FOUND);
            }
            String sign = DigestUtils.sha256Hex(appid + "&" + timestamp + "&" + nonce_str + "&" + appsecret).toLowerCase();
            if(!signGain.equals(sign)){
                logger.error("签名【" + signGain +"】错误");
                throw new BizException(CommonEnum.SIGNATURE_NOT_MATCH);
            }
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
