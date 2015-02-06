package com.targ.jsonSchemaGenerator.generator;


import com.targ.jsonSchemaGenerator.annotation.IsRequired;
import com.targ.jsonSchemaGenerator.annotation.string.Format;
import com.targ.jsonSchemaGenerator.annotation.string.MaxLength;
import com.targ.jsonSchemaGenerator.annotation.string.MinLength;
import com.targ.jsonSchemaGenerator.annotation.string.Pattern;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by 单兵 on 15-1-30.
 */
public class StringTypeGenerator implements TypeGenerator {

    //5.2.1.  maxLength
    //5.2.2.  minLength
   // 5.2.3.  pattern

    private Integer minLength = null;

    boolean isRequired = true;

    @Override
    public Map<String, Object> genarator(Field field,Class[] includeClass) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("type","string");
        Annotation[] annotations = field.getDeclaredAnnotations();
        isRequired = true;
        for(Annotation annotation : annotations){
            if(annotation instanceof MinLength){
                resultMap.put("minLength",((MinLength) annotation).value());
            }else if (annotation instanceof MaxLength){
                resultMap.put("maxLength",((MaxLength) annotation).value());
            }else if(annotation instanceof Pattern){
                resultMap.put("pattern",((Pattern) annotation).value());
            }else if(annotation instanceof IsRequired){
                isRequired = ((IsRequired) annotation).value();
            }else if(annotation instanceof Format){
                Format.FormatType formatType = ((Format) annotation).value();
                resultMap.put("format",formatType.getValue());
            }
        }
        if(resultMap.get("minLength")==null && minLength!=null){
            resultMap.put("minLength",minLength);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> genarator(Class clazz, Class[] includeClass) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("type","string");
        return resultMap;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }
}
