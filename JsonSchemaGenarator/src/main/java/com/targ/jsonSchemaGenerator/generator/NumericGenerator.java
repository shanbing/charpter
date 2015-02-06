package com.targ.jsonSchemaGenerator.generator;

import com.targ.jsonSchemaGenerator.annotation.IsRequired;
import com.targ.jsonSchemaGenerator.annotation.numeric.Maximum;
import com.targ.jsonSchemaGenerator.annotation.numeric.Minimum;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 单兵 on 15-2-2.
 */
public class NumericGenerator implements TypeGenerator {

    private String typeName = "number";

    private boolean isRequired = false;

    @Override
    public Map<String, Object> genarator(Field field,Class[] includeClass) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("type",typeName);
        Annotation[] annotations = field.getDeclaredAnnotations();
        isRequired = false;
        for(Annotation annotation : annotations){
            if(annotation instanceof Maximum){
                Maximum maximum = (Maximum) annotation;
                resultMap.put("maximum", maximum.value());
                if(maximum.exclusiveMaximum()==true) {
                    resultMap.put("exclusiveMaximum",true);
                }
            }else if(annotation instanceof Minimum){
                Minimum minimum = (Minimum)annotation;
                resultMap.put("minimum",minimum.value());
                if(minimum.exclusiveMinimum()==true) {
                    resultMap.put("exclusiveMinimum",true);
                }
            }else if(annotation instanceof IsRequired){
                isRequired = ((IsRequired) annotation).value();
            }
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> genarator(Class clazz, Class[] includeClass) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("type",typeName);
        return resultMap;
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }

    public void setTypeName(String typeName){
        this.typeName = typeName;
    }
}
