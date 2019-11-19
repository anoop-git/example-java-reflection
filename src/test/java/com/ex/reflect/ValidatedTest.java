package com.ex.reflect;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ValidatedTest<O extends Validated> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testIsValidByDefault() {
        assertTrue( getObjectToValidate().isValid(),"Why is object not valid??");
        assertNotNull(getObj(), "Was unable to get a newInstance - check logs for exception");
        assertTrue( getObj().isValid(),"Why is the newInstance object not valid??");
    }

    abstract O getObjectToValidate();

    abstract Class<O> getClazz();

    O getObj() {
        O obj = null;
        try {
            obj = getClazz().newInstance();
        } catch(InstantiationException | IllegalAccessException e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return obj;
    }

}
