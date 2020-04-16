package com.bee.sample.ecs.utils;

import com.bee.sample.ecs.entity.CH2ThreadContext;
import com.bee.sample.ecs.entity.ThreadLocalConstant;

import javax.swing.plaf.PanelUI;

public class ThreadLocalUtil {
    // 请求路径
    public static void setRequestUri(String requestUri) {
        CH2ThreadContext.put(ThreadLocalConstant.REQUEST_URI, requestUri);
    }

    public static String getRequestUri() {
        return (String) CH2ThreadContext.get(ThreadLocalConstant.REQUEST_URI);
    }

    public static void setCookie(String cookie) {
        CH2ThreadContext.put(ThreadLocalConstant.COOKIE, cookie);
    }

    public static String getCookie(){
        return (String) CH2ThreadContext.get(ThreadLocalConstant.COOKIE);
    }




}
