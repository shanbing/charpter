package com.targ.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.targ.dto.Dept;
import com.targ.dto.Post;
import com.targ.dto.User;
import com.targ.jsonSchemaGenerator.generator.JsonSchemaGenerator;
import junit.framework.TestCase;

import java.io.IOException;

/**
 * Created by 单兵 on 15-1-30.
 */
public class JsonSchemaGeneratorTest extends TestCase {


    public void testGenarator(){
        JsonSchemaGenerator genarator = new JsonSchemaGenerator();
        try {
//            String out = genarator.genarator(User.class,new Class[]{Dept.class, Post.class});
            String out = genarator.genarator(Dept.class,null);
            System.out.println(out);

//            String schemaStr = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"string\",\"maxLength\":50,\"minLength\":10,\"pattern\":\"\\\\d+\"},\"name\":{\"type\":\"string\"},\"age\":{\"type\":\"integer\",\"minimum\":0.0,\"maximum\":100.0,\"exclusiveMaximum\":true},\"money\":{\"type\":\"number\",\"minimum\":0.0,\"exclusiveMinimum\":true},\"dept\":{\"type\":\"object\",\"properties\":{\"deptId\":{\"type\":\"string\"},\"deptName\":{\"type\":\"string\"}},\"required\":[\"deptId\",\"deptName\"]},\"posts\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"properties\":{\"postId\":{\"type\":\"string\"},\"postName\":{\"type\":\"string\"}},\"required\":[\"postId\",\"postName\"]},\"minItems\":1},\"email\":{\"type\":\"string\",\"format\":\"email\"}},\"required\":[\"id\",\"name\",\"age\",\"dept\",\"email\"]}";
/*            JsonNode jsonShemaNode = null;
            try {
                JsonNode jsonNode = JsonLoader.fromResource("/user.json");

                jsonShemaNode = JsonLoader.fromString(out);

                final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

                final JsonSchema schema = factory.getJsonSchema(jsonShemaNode);

                ProcessingReport report = schema.validate(jsonNode);

                System.out.println(report);

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (ProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }*/
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
