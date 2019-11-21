package com.ex.aspect.sample;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DemoAroundTest {

    DemoAround da = new DemoAround();

    @Test
    public void modifyAckValue() {
        assertEquals("bar", da.ackValue("foo"), "Should have transformed foo to bar");
    }
}
