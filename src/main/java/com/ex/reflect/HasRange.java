package com.ex.reflect;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface HasRange {

    int min() default -1_000_000_000;

    int max() default 1_000_000_000;

}
