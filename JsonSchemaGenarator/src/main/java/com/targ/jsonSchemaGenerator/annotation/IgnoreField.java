package com.targ.jsonSchemaGenerator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 单兵 on 15-2-3.
 * 标记此注解,生成时忽略此属性
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IgnoreField {
    boolean value() default true;
}
