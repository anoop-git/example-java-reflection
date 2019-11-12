package com.ex.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public abstract class Validated {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public boolean isValid() {
        // reflect over all items and ensure validation\
        return Arrays.asList(this.getClass().getDeclaredFields()).stream().noneMatch(f -> {
            boolean acc = f.isAccessible();
            f.setAccessible(true);
            log.trace("checking field: " + f);
            boolean isInvalid = true;
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
        });
    }
}
