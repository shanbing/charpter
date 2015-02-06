package com.targ.jsonSchemaGenerator.annotation.string;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 单兵 on 15-2-3.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Format {
    FormatType value();

    public enum FormatType{
        Date_time("date-time"),
        Email("email"),
        Ipv4("ipv4"),
        Ipv6("ipv6"),
        Uri("uri");
        private String value;
        private FormatType(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }
}
