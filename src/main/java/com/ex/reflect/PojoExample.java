package com.ex.reflect;

final class PojoExample extends Validated {

    @HasRange(min = 0, max = 1)
    private int value0;

    @HasRange(min = -10, max = 10)
    private int value1;

    @HasRange(min = -20, max = 20)
    private int value2;

    @HasRange(min = -30, max = 30)
    private int value3;

    public int getValue0() {
        return value0;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public int getValue3() {
        return value3;
    }

    public void setValue0(int val) {
        this.value0 = val;
    }

    public void setValue1(int val) {
        this.value1 = val;
    }

    public void setValue2(int val) {
        this.value2 = val;
    }

    public void setValue3(int val) {
        this.value3 = val;
    }


}
