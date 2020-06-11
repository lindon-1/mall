package com.lindl.mall.aop.aspect;

import com.lindl.mall.common.exception.ExceptionFactory;
import com.lindl.mall.common.exception.ExceptionMsg;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description：用户token校验
 * @Author: ldl
 * @CreateDate: 2020/6/11 16:15
 */
@Order(2)
@Aspect
@Component
public class TokenCheck {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private ExceptionFactory exceptionFactory;

    @Before("execution(* com.lindl..*Controller.*(..)) && !execution(* com.lindl..LoginController.*(..))")
    public void before(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");

        if(StringUtils.isEmpty(token)) {
            throw exceptionFactory.create(ExceptionMsg.ERR_AUTHENCATION_ERROR, ExceptionMsg.msg.get(ExceptionMsg.ERR_AUTHENCATION_ERROR));
        }
        String userInfoString = redisTemplate.opsForValue().get(token);
        if (StringUtils.isEmpty(userInfoString)) {
            throw exceptionFactory.create(ExceptionMsg.ERR_AUTHENCATION_ISUNVALIDATE, ExceptionMsg.msg.get(ExceptionMsg.ERR_AUTHENCATION_ISUNVALIDATE));
        }


    }
}
