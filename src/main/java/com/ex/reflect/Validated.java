package com.ex.reflect;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.BiFunction;

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
        boolean isInvalid = validHasRange(f) || validNotEmpty(f) || validMatches(f);
        f.setAccessible(acc);
        return isInvalid;
    }

    private boolean validNotEmpty(Field f) {
        return validityWrapper(f, NotEmpty.class, String.class, (ann, val) -> StringUtils.trimToEmpty(val).isEmpty());
    }

    private boolean validHasRange(Field f) {
        return validityWrapper(f, HasRange.class, Integer.class, (ann, val) -> val < ann.min() || val > ann.max());
    }

    private boolean validMatches(Field f) {
        return validityWrapper(f, Matches.class, String.class, (ann, val) -> !StringUtils.trimToEmpty(val).matches(ann.value()));
    }

    private <E extends Annotation, T> boolean validityWrapper(Field f, Class<E> clazz, Class<T> validFieldType, BiFunction<E, T, Boolean> fnCheck) {
        boolean isInvalid = false;
        E ann = f.getAnnotation(clazz);
        if (null != ann) {
            log.debug("annotation: " + ann);
            try {
                Object objVal = f.get(this);
                if (null != objVal && validFieldType.isAssignableFrom(objVal.getClass())) {
                    isInvalid = fnCheck.apply(ann, validFieldType.cast(objVal));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return isInvalid;
    }
}
