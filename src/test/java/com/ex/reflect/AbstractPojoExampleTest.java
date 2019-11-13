package com.ex.reflect;

import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

public abstract class AbstractPojoExampleTest<E extends PojoExample> extends ValidatedTest<E> {

    @Test
    public void testMinMaxValue0() {
        checkAllInRangePlusOneOutside(0, 1, 0);
    }

    @Test
    public void testMinMaxValue1() {
        checkAllInRangePlusOneOutside(-10, 10, 1);
    }

    @Test
    public void testMinMaxValue2() {
        checkAllInRangePlusOneOutside(-20, 20, 2);
    }

    @Test
    public void testMinMaxValue3() {
        checkAllInRangePlusOneOutside(-30, 30, 3);
    }

    protected void checkAllInRangePlusOneOutside(final int min, final int max, final int target) {
        E ex = getObjectToValidate();

        for (int i = min; i <= max; i++) {
            setTarget(ex, target, i);
            Assert.that(ex.isValid(), "In range should be valid");
        }

        setTarget(ex, target, min - 1);
        Assert.that(!ex.isValid(), "Below valid range should fail");

        setTarget(ex, target, max + 1);
        Assert.that(!ex.isValid(), "Above valid range should fail");

    }

    protected void setTarget(final E ex, final int target, final int value) {
        if (0 == target) {
            ex.setValue0(value);
        } else if (1 == target) {
            ex.setValue1(value);
        } else if (2 == target) {
            ex.setValue2(value);
        } else if (3 == target) {
            ex.setValue3(value);
        } else {
            childSetTarget(ex, target, value);
        }
    }

    /**
     * Use to manage extended classes so we can utilize the if/else methods
     */
    protected abstract void childSetTarget(final E ex, final int target, final int value);
}
