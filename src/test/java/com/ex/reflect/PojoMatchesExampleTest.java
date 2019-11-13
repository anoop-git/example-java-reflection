package com.ex.reflect;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class PojoMatchesExampleTest extends ValidatedTest<PojoMatchesExample> {

    @Override
    PojoMatchesExample getObjectToValidate() {
        return new PojoMatchesExample();
    }

    @Test
    public void testRegExPatternMatching() {
        assertFalse(StringUtils.EMPTY.matches("[0-9]{3}"), "Simple digit (non)matcher to empty string");
        assertTrue("123".matches("[0-9]{3}"), "Simple digit matcher");
    }

    @Test
    public void testMatchesActuallyCompute() {
        PojoMatchesExample ex = getObjectToValidate();

        ex.setDoMatch(StringUtils.EMPTY);
        assertFalse(ex.isValid(), "Empty should be invalid");

        ex.setDoMatch("      ");
        assertFalse(ex.isValid(), "Lots of spaces should be invalid");

        ex.setDoMatch("1");
        assertFalse(ex.isValid(), "Single number should be invalid");

        ex.setDoMatch("a");
        assertFalse(ex.isValid(), "Single letter (lowercase) should be invalid");

        ex.setDoMatch("A");
        assertFalse(ex.isValid(), "Single letter (uppercase) should be invalid");

        ex.setDoMatch("ABC");
        assertFalse(ex.isValid(), "Triple letter (uppercase) should be invalid");

        ex.setDoMatch("123");
        assertTrue(ex.isValid(), "Triple number should be valid");

        ex.setDoMatch("    123    ");
        assertTrue(ex.isValid(), "Triple number surrounded by lots of spaces should be valid (as a string)");
    }
}
