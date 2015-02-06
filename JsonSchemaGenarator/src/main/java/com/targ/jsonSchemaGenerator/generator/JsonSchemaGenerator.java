package com.targ.jsonSchemaGenerator.generator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by 单兵 on 15-1-30.
 * 生成schema的入口
 */
public class JsonSchemaGenerator {

    //定义配置信息,用于生成默认信息

    private Map<Class,TypeGenerator> typeGenaratorMap = new HashMap<Class,TypeGenerator>();

    public JsonSchemaGenerator(){
        //TODO:注入处理类型的typeGenartor
        //字符串处理
        typeGenaratorMap.put(String.class,new StringTypeGenerator());

        //浮点型数据处理
        NumericGenerator numericGenarator = new NumericGenerator();
        typeGenaratorMap.put(Number.class,numericGenarator);
        typeGenaratorMap.put(Double.class,numericGenarator);
        typeGenaratorMap.put(double.class,numericGenarator);
        typeGenaratorMap.put(Float.class,numericGenarator);
        typeGenaratorMap.put(float.class,numericGenarator);
        typeGenaratorMap.put(BigDecimal.class,numericGenarator);

        //整型数据处理
        NumericGenerator integerGenarator = new NumericGenerator();
        integerGenarator.setTypeName("integer");
        typeGenaratorMap.put(Integer.class,integerGenarator);
        typeGenaratorMap.put(int.class,integerGenarator);

        ObjectGenerator objectGenarator = new ObjectGenerator();
        typeGenaratorMap.put(this.getClass(),objectGenarator);

        BooleanTypeGenerator booleanTypeGenerator = new BooleanTypeGenerator();
        typeGenaratorMap.put(Boolean.class, booleanTypeGenerator);
        typeGenaratorMap.put(boolean.class, booleanTypeGenerator);

        ArrayTypeGenerator arrayTypeGenerator = new ArrayTypeGenerator();
        typeGenaratorMap.put(List.class, arrayTypeGenerator);

        MapGenerator mapGenerator = new MapGenerator();
        typeGenaratorMap.put(Map.class, mapGenerator);

        //需置于最后
        objectGenarator.setTypeGenaratorMap(typeGenaratorMap);
        arrayTypeGenerator.setTypeGenaratorMap(typeGenaratorMap);
    }

    /**
     * 根据类型,生成jsonSchema字符串
     * @param clazz 要生成jsonSchema类的class
     * @param includeClasses 如果类中包含其它dto,需在此处赋值其它类型
     *                       若此处包含类中其它的dto 则会调用 ObjectGenarator类生成其中的属性
     *                       否则  忽略此对象
     *
     */
    public String genarator(Class clazz,Class[] includeClasses) throws IOException {
        Map<String,Object> genaratorMap =typeGenaratorMap.get(this.getClass()).genarator(clazz,includeClasses);

        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("$schema","http://json-schema.org/draft-04/schema#");
        resultMap.putAll(genaratorMap);

        ObjectMapper objectMapper = new ObjectMapper();
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/generate.properties"));
        String dir = properties.getProperty("directory");
        File dirFile = new File(dir);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        File file = new File(dirFile,clazz.getSimpleName()+"_schema.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,resultMap);
        String result =  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultMap);
        return result;
    }

    /**
     *注册类别的处理类型
     * @param clazz 处理的java对象类型
     * @param typeGenarator 处理的类
     */
    public void registorTypeGenarator(Class clazz,TypeGenerator typeGenarator){
        typeGenaratorMap.put(clazz,typeGenarator);
    }

    public void removeTypeGenarator(Class clazz){
        typeGenaratorMap.remove(clazz);
    }

    public Map<Class,TypeGenerator> getTypeGenaratorMap(){
        return typeGenaratorMap;
    }

}
