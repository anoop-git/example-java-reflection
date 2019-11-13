package com.ex.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class Validated {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // reflect over all items and ensure validation
    public boolean isValid() {
        // the below only checks the primary class
        //
        // return Arrays.asList(this.getClass().getDeclaredFields()).stream().noneMatch(this::fieldIsValid);
        //
        // vvvvv this uses recursion to check the full parent/child tree vvvvv
        return objectIsValid(this.getClass());
    }

    private boolean objectIsValid(Class<?> clazz) {
        return (null == clazz) ? true : Arrays.asList(clazz.getDeclaredFields()).stream().noneMatch(this::fieldIsValid) && objectIsValid(clazz.getSuperclass());
    }

    private boolean fieldIsValid(Field f) {
        boolean acc = f.isAccessible();
        f.setAccessible(true);
        log.trace("checking field: " + f);
        boolean isInvalid = false;
        HasRange ann = f.getAnnotation(HasRange.class);
        if (null != ann) {
            log.debug("annotation: " + ann);
            try {
                int val = f.getInt(this);
                isInvalid = (val < ann.min() || val > ann.max());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        f.setAccessible(acc);
        return isInvalid;
    }
}
