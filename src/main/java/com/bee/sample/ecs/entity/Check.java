package com.bee.sample.ecs.entity;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Check {
    // 字段校验规则：字段名+校验规则+冒号+错误信息
    String[] value();
}
