package com.targ.jsonSchemaGenerator.generator;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 单兵 on 15-2-3.
 */
public class MapGenerator implements TypeGenerator {

    private boolean isRequired = false;

    @Override
    public Map<String, Object> genarator(Field field, Class[] includeClass) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("type","object");

        return resultMap;
    }

    @Override
    public Map<String, Object> genarator(Class clazz, Class[] includeClass) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("type","object");

        return resultMap;
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }
}
