package com.targ.jsonSchemaGenerator.generator;


import com.targ.jsonSchemaGenerator.annotation.IgnoreField;
import com.targ.jsonSchemaGenerator.annotation.IsRequired;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by 单兵 on 15-2-2.
 */
public class ObjectGenerator implements TypeGenerator {

    private Map<Class, TypeGenerator> typeGenaratorMap = new HashMap<Class, TypeGenerator>();

    private boolean isRequired = false;


    @Override
    public Map<String, Object> genarator(Field field, Class[] includeClasses) {

        isRequired = false;

        Class clazz = field.getType();
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof IsRequired) {
                isRequired = ((IsRequired) annotation).value();
            }
        }

        Map<String, Object> resultMap = genarator(clazz, includeClasses);
        return resultMap;
    }

    @Override
    public Map<String, Object> genarator(Class clazz, Class[] includeClasses) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("type", "object");
        Map<String, Object> propertiesMap = new LinkedHashMap<String, Object>();
        Field[] fields = clazz.getDeclaredFields();
        List<String> required = new ArrayList<String>();
        for (Field fieldItem : fields) {
            //如果属性标注了 忽略属性的注解,则continue;
            if(fieldItem.isAnnotationPresent(IgnoreField.class) && fieldItem.getAnnotation(IgnoreField.class).value()){
                continue;
            }
            Class typeClazz = fieldItem.getType();
            TypeGenerator typeGenarator = typeGenaratorMap.get(typeClazz);
            if (typeGenarator == null) {
                if (typeGenarator == null) {
                    //从includeClass中匹配
                    if (includeClasses != null) {
                        for (Class includeClass : includeClasses) {
                            if (includeClass == typeClazz) {
                                typeGenarator = typeGenaratorMap.get(JsonSchemaGenerator.class);
                            }
                        }
                    }
                }
            }
            if (typeGenarator == null) {
                continue;
            }
            Map<String, Object> typeMap = typeGenarator.genarator(fieldItem, includeClasses);
            propertiesMap.put(fieldItem.getName(), typeMap);
            if (typeGenarator.isRequired()) {
                required.add(fieldItem.getName());
            }
        }
        resultMap.put("properties", propertiesMap);
        //TODO:requered字段  如何???
        resultMap.put("required", required);

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

