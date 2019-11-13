package com.ex.reflect;

public class PojoHasRangeExample extends Validated {

    public PojoHasRangeExample() { super(); }

    private int notValidated;

    @HasRange(min = 0, max = 1)
    private int value0;

    @HasRange(min = -10, max = 10)
    private int value1;

    @HasRange(min = -20, max = 20)
    private int value2;

    @HasRange(min = -30, max = 30)
    private int value3;

    public int getNotValidated() { return this.notValidated; }

    public int getValue0() {
        return this.value0;
    }

    public int getValue1() {
        return this.value1;
    }

    public int getValue2() {
        return this.value2;
    }

    public int getValue3() {
        return this.value3;
    }

    public void setNotValidated(int notValidated) { this.notValidated = notValidated; }

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
