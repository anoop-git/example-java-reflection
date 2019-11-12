package com.ex.reflect;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

final class PojoExampleTest extends ValidatedTest {

    @Override
    PojoExample getObjectToValidate() {
        return new PojoExample();
    }

    @Test
    public void testMinMaxValue0() {
        checkAllInRangePlusOneOutside(0, 1, 0);
    }

    @Ignore
    @Test
    public void testMinMaxValue1() {
        checkAllInRangePlusOneOutside(-10, 10, 1);
    }

    @Test
    public void testMinMaxValue2() {
        checkAllInRangePlusOneOutside(-20, 20, 2);
    }

    @Ignore
    @Test
    public void testMinMaxValue3() {
        checkAllInRangePlusOneOutside(-30, 30, 3);
    }

    protected void checkAllInRangePlusOneOutside(final int min, final int max, final int target) {
        PojoExample ex = new PojoExample();

        for (int i = min; i <= max; i++) {
            setTarget(ex, target, i);
            Assert.that(ex.isValid(), "In range should be valid");
        }

        setTarget(ex, target, min - 1);
        Assert.that(!ex.isValid(), "Below valid range should fail");

        setTarget(ex, target, max + 1);
        Assert.that(!ex.isValid(), "Above valid range should fail");

    }

    protected void setTarget(final PojoExample ex, final int target, final int value) {
        switch (target) {
            case 0:
                ex.setValue0(value);
                break;

            case 1:
                ex.setValue1(value);
                break;

            case 2:
                ex.setValue2(value);
                break;

            case 3:
                ex.setValue3(value);
                break;

            default:
                break;

        }
    }

}
