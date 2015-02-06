package com.targ.jsonSchemaGenerator.annotation.numeric;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 单兵 on 15-2-2.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Maximum {

    double value();

    /**
     * 是否不包括最小值 默认包含
     */
    boolean exclusiveMaximum() default false;
}
