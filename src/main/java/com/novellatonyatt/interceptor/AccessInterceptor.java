package com.novellatonyatt.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 17:03
 * @Description:
 */
@Slf4j
public class AccessInterceptor implements HandlerInterceptor {

    /**
     * 到达Controller前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("【AccessInterceptor】preHandle");
        return true;
    }

    /**
     * Controller返回后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("【AccessInterceptor】postHandle");
    }

    /**
     * 成功执行时返回
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("【AccessInterceptor】afterCompletion");
    }
}
