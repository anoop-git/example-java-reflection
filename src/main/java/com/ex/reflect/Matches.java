package com.ex.reflect;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Matches {

    String value() default "";

}
