package com.ex.reflect;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class PojoNotEmptyExampleTest extends ValidatedTest<PojoNotEmptyExample> {

    @Override
    PojoNotEmptyExample getObjectToValidate() {
        return new PojoNotEmptyExample();
    }

    @Override
    Class<PojoNotEmptyExample> getClazz() {
        return PojoNotEmptyExample.class;
    }

    @Test
    public void testWillNotBeValidWhenEmpty() {
        PojoNotEmptyExample ex = getObjectToValidate();

        ex.setValidation(StringUtils.EMPTY);
        assertFalse(ex.isValid(),"Empty should be invalid");

        ex.setValidation("      ");
        assertFalse(ex.isValid(),"Lots of spaces should be invalid");

        ex.setValidation("1");
        assertTrue(ex.isValid(),"Single number should be valid (as a string)");

        ex.setValidation("a");
        assertTrue(ex.isValid(),"Single letter should be valid");

        ex.setValidation("    a    ");
        assertTrue(ex.isValid(),"Single letter surrounded by lots of spaces should be valid (as a string)");
    }
}
