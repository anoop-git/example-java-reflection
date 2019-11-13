package com.ex.reflect;

public class PojoExampleTest extends AbstractPojoExampleTest<PojoExample> {

    @Override
    PojoExample getObjectToValidate() {
        return new PojoExample();
    }

    @Override
    protected void childSetTarget(final PojoExample ex, final int target, final int value) {
        // noop
    }

}
