package com.bee.sample.ecs;

import com.bee.sample.ecs.entity.Role;
import com.bee.sample.ecs.entity.AuthInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

@Configuration
@Aspect
public class AOPConfig {
    @Around("@within(org.springframework.stereotype.Controller)")
    public Object simpleAop(final ProceedingJoinPoint pjp) throws Throwable{
        try{
            Object args = pjp.getArgs();
            System.out.println("args:" + Arrays.asList(args));
            Object o = pjp.proceed();
            System.out.println("return :" + o);
            return o;
        }catch (Throwable e){
            throw e;
        }
    }


    @Around("@within(com.bee.sample.ecs.entity.AuthInfo)")
    public Object authAop(final ProceedingJoinPoint pjp) throws Throwable{
        try{
            Object args = pjp.getArgs();
            System.out.println("args:" + Arrays.asList(args));
            Role role = getAnnoContext(pjp);
            Integer authLevel = role.getCode();
            // todo 从本地线程缓存中读取token  获取用户权限  判断是否可以访问
            Object o = pjp.proceed();
            System.out.println("return :" + o);
            return o;
        }catch (Throwable e){
            throw e;
        }
    }


//
//    @Around("@within(com.bee.sample.ch2.preHandle.Check)")
//    public Object checkAop(final ProceedingJoinPoint pjp) throws Throwable{
//        try{
//            Object args = pjp.getArgs();
//            String msg = doCheck(pjp);
//            if(!StringUtils.isEmpty(msg)){
//                throw new IllegalArgumentException(msg);
//            }
//            System.out.println("args:" + Arrays.asList(args));
//            Object o = pjp.proceed();
//            System.out.println("return :" + o);
//            return o;
//        } catch (Throwable e){
//            throw e;
//        }
//    }

    private Role getAnnoContext(ProceedingJoinPoint pjp){
        Object[] args = pjp.getArgs();
        Method method = getMethod(pjp);
        String methodInfo = StringUtils.isEmpty(method.getName()) ? "" : " while calling " + method.getName();
        if(isAuth(method)){
            // 获取注释里的内容
            AuthInfo annotation = method.getAnnotation(AuthInfo.class);
            Role authContent = annotation.value();
            return authContent;

        }
        return null;
    }



    /**
     * 获取方法
     *
     * @param joinPoint ProceedingJoinPoint
     * @return 方法
     */
    private Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint
                        .getTarget()
                        .getClass()
                        .getDeclaredMethod(joinPoint.getSignature().getName(),
                                method.getParameterTypes());
            } catch (SecurityException | NoSuchMethodException e) {
                System.out.println("" + e);
            }
        }
        return method;
    }


    private Boolean isAuth(Method method) {
        Boolean isCheck = Boolean.TRUE;
        // 只允许有一个参数
        if (!method.isAnnotationPresent(AuthInfo.class)) {
            isCheck = Boolean.FALSE;
        }
        return isCheck;
    }

}
