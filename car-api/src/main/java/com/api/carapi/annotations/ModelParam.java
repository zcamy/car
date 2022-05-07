package com.api.carapi.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModelParam {

    String value() default "";

    String desc() default "";

    boolean object() default false;
}
