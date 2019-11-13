package com.ex.reflect;

import org.junit.jupiter.api.Test;

final class ChildPojoExampleTest extends AbstractPojoExampleTest<ChildPojoExample> {

    @Override
    ChildPojoExample getObjectToValidate() {
        return new ChildPojoExample();
    }

    @Test
    public void testMinMaxValue4() {
        checkAllInRangePlusOneOutside(-40, 40, 4);
    }

    @Override
    protected void childSetTarget(final ChildPojoExample ex, final int target, final int value) {
        if (4 == target) {
            ex.setValue4(value);
        }
    }
}
