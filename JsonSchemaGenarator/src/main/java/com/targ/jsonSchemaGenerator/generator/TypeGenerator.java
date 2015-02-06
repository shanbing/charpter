package com.targ.jsonSchemaGenerator.generator;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by 单兵 on 15-1-30.
 */
public interface TypeGenerator {

    Map<String,Object> genarator(Field field,Class[] includeClass);

    Map<String,Object> genarator(Class clazz,Class[] includeClass);

    /**
     *返回一次生成的  是否为必须
     */
    boolean isRequired();

}
