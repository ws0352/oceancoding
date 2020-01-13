package com.oceancoding.ws.ocean.interceptor;

import cn.hutool.core.date.DateUtil;
import com.oceancoding.ws.ocean.annotations.RequireSignature;
import com.oceancoding.ws.ocean.exceptionHandler.BizException;
import com.oceancoding.ws.ocean.exceptionHandler.enums.CommonEnum;
import com.oceancoding.ws.ocean.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class ExternalServiceInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String ASSESS_TOKEN = "assessToken";
    private static final String EXCEPTION_MSG = "signature does not match locally computed signature,error code:";
    

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        // 有 @RequireSignature 注解，需要认证
        if(AnnotatedElementUtils.isAnnotated(method, RequireSignature.class)){
            // 判断是否存在令牌信息，如果存在，则允许登录
            String accessToken = request.getParameter(ASSESS_TOKEN);
            if(StringUtils.isBlank(ASSESS_TOKEN)){
                throw new BizException("签名不存在");
            }
            Claims claims = null;
            try {
                claims = JwtUtils.parseJWT(accessToken);
            }catch (Exception e){
                throw new BizException("签名生成错误");
            }
            //签名格式错误
            String[] firstParam = claims.getId().split("=");
            if(ObjectUtils.isEmpty(firstParam)){
                throw new BizException("签名格式错误");
            }
            // 签名被篡改
            String parameter = request.getParameter(firstParam[0]);
            if(!firstParam[1].equals(parameter)){
                throw new BizException("签名被篡改");
            }
            boolean validation = false;
            //获取签名生成的时间，签名有效时间十分钟
            try{
                long timeInMills = DateUtil.calendar(Long.parseLong(claims.get("exp") + "")).getTimeInMillis();
                validation = DateUtil.calendar(System.currentTimeMillis()).getTimeInMillis() < (timeInMills + 10 * 60 *1000);

            }catch (Exception e){
                throw new BizException(e + "");
            }

            //过期
            if(validation){
                throw new BizException(CommonEnum.SIGNATURE_EXPIRED);
            }
        }
        return super.preHandle(request, response, handler);

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
