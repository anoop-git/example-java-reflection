package com.ex.reflect;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.jvm.hotspot.utilities.Assert;

public abstract class ValidatedTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testIsValidByDefault() {
        Assert.that(getObjectToValidate().isValid(),"Why is object not valid??");
    }

    abstract <O extends Validated> O getObjectToValidate();

}
