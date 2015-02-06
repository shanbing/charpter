package com.targ.generator;

import com.targ.dto.User;
import junit.framework.TestCase;

import java.lang.reflect.Field;

/**
 * Created by 单兵 on 15-2-2.
 */
public class ValidatorTest extends TestCase {


    public void testValidator(){
        Field[] fields = User.class.getDeclaredFields();
        for(Field field : fields){
            System.out.println(field.getType());

        }
    }
}
