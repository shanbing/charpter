package com.targ.jsonSchemaGenerator.generator;

import com.targ.jsonSchemaGenerator.annotation.IsRequired;
import com.targ.jsonSchemaGenerator.annotation.array.ListClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 单兵 on 15-2-2.
 */
public class ArrayTypeGenerator implements TypeGenerator {

    private boolean isRequired = false;

    private Map<Class,TypeGenerator> typeGenaratorMap = new HashMap<Class,TypeGenerator>();

    @Override
    public Map<String, Object> genarator(Field field,Class[] includeClasses) {
        isRequired = false;
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("type","array");

        Annotation[] annotations =field.getDeclaredAnnotations();
        for (Annotation annotation :annotations){
            if(annotation instanceof IsRequired){
                isRequired = ((IsRequired) annotation).value();
            }else if(annotation instanceof ListClass){
                Class[] clazzes = ((ListClass) annotation).value();
                Map<String,Object> addMap = resultMap;
                for(Class clazz : clazzes){
                    TypeGenerator typeGenarator = typeGenaratorMap.get(clazz);
                    if(typeGenarator==null){
                        for(Class clazzItem : includeClasses){
                            if(clazzItem==clazz){
                                typeGenarator = typeGenaratorMap.get(JsonSchemaGenerator.class);
                            }
                        }
                    }
                    if(typeGenarator!=null){
                        Map<String,Object> itemMap = typeGenarator.genarator(clazz,includeClasses);
                        addMap.put("items",itemMap);
                        addMap.put("minItems",1);
                        addMap = itemMap;
                    }else{
                        break;
                    }
                }
            }
        }

        return resultMap;
    }

    @Override
    public Map<String, Object> genarator(Class clazz, Class[] includeClass) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("type","array");

        return resultMap;
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }

    public void setTypeGenaratorMap(Map<Class, TypeGenerator> typeGenaratorMap) {
        this.typeGenaratorMap = typeGenaratorMap;
    }
}
