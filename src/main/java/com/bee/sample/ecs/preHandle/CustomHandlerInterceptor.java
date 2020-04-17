package com.bee.sample.ecs.preHandle;

import com.bee.sample.ecs.entity.EcsConstant;
import com.bee.sample.ecs.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class CustomHandlerInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        log.info("cookies : {}", cookies);
        if(cookies!=null && cookies.length!=0){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(EcsConstant.TOKEN_NAME)){
                    ThreadLocalUtil.setCookie(cookie.getValue());
                    break;
                }
            }
        }
        log.info("cookie : {}", ThreadLocalUtil.getCookie());
        return true;
    }

    //controller执行之后，且页面渲染之前调用

    //页面渲染之后调用，一般用于资源清理操作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("---------afterCompletion--------");
    }


}
