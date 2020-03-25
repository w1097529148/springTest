package com.Annotation.annotation.security;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/25 10:48
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface security {
    String value() default "";
}
