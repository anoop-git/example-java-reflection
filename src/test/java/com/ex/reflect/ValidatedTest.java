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
    }

    abstract O getObjectToValidate();

}
