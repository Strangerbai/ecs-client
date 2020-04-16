package com.bee.sample.ecs.utils;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>java获取request中的参数、java解析URL问号后的参数<p>
 * @version 1.0
 * @author li_hao
 * @date 2016年12月21日
 */
public class RequestParametersUtil {
    /**
     *  获取request中参数
     * @param request 页面请求
     */
    public static Map<String, Object> getRequestParameters(HttpServletRequest request) {

        Map<String, Object> paramsMap = new HashMap<>();
        try{
            Enumeration<String> paramNames = request.getParameterNames();//获取所有的参数名
            while (paramNames.hasMoreElements()) {
                String name = paramNames.nextElement();//得到参数名
                String value = request.getParameter(name);//通过参数名获取对应的值
                paramsMap.put(name, value);
            }
            return paramsMap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * multipart/form-data或application/json  类型采用getReader来获取参数
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String,Object> getReqBody(HttpServletRequest request) throws Exception{
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null){
                sb.append(str);
            }
            br.close();
            return JSON.parseObject(sb+"", Map.class);
        }catch (Exception err){
            err.printStackTrace();
        }finally {
            if(null != br){
                br.close();
            }
        }
        return null;
    }

    public static Map<String, Object> getHeaders(HttpServletRequest request){

        Map<String, Object> paramsMap = new HashMap<>();
        try{
            Enumeration<String> enumeration =  request.getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();//得到参数名
                String value = request.getHeader(name);//通过参数名获取对应的值
                paramsMap.put(name, value);
            }
            return paramsMap;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
