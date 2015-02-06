package com.targ.jsonSchemaGenerator.generator;

import com.targ.jsonSchemaGenerator.annotation.IsRequired;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 单兵 on 15-2-2.
 */
public class BooleanTypeGenerator implements TypeGenerator {

    /**
     * boolean类型的  默认 不是必须
     */
    boolean isRequired = false;

    @Override
    public Map<String, Object> genarator(Field field,Class[] includeClass) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("type","boolean");
        Annotation[] annotations = field.getDeclaredAnnotations();
        isRequired = false;
        for (Annotation annotation : annotations){
            if(annotation instanceof IsRequired){
                isRequired = ((IsRequired) annotation).value();
            }
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> genarator(Class clazz, Class[] includeClass) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("type","boolean");
        return resultMap;
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }
}
