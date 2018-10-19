package org.dubbo.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.dubbo.pojo.base.BaseResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 健全拦截器
 * Created by jiangbin on 2018/7/9.
 */

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(AuthInterceptor.class);




    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("into preHandle method");

        return true;
    }



    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}