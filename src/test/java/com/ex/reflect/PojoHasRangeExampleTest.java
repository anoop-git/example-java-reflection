package com.ex.reflect;

public class PojoHasRangeExampleTest extends AbstractPojoHasRangeExampleTest<PojoHasRangeExample> {

    @Override
    PojoHasRangeExample getObjectToValidate() {
        return new PojoHasRangeExample();
    }

    @Override
    protected void childSetTarget(final PojoHasRangeExample ex, final int target, final int value) {
        // noop
    }

}
