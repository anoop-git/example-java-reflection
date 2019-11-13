package com.ex.reflect;

import org.junit.jupiter.api.Test;

final class ChildPojoHasRangeExampleTest extends AbstractPojoHasRangeExampleTest<ChildPojoHasRangeExample> {

    @Override
    ChildPojoHasRangeExample getObjectToValidate() {
        return new ChildPojoHasRangeExample();
    }

    @Test
    public void testMinMaxValue4() {
        checkAllInRangePlusOneOutside(-40, 40, 4);
    }

    @Override
    protected void childSetTarget(final ChildPojoHasRangeExample ex, final int target, final int value) {
        if (4 == target) {
            ex.setValue4(value);
        }
    }
}
