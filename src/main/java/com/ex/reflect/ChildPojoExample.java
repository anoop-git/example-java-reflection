package com.ex.reflect;

final class ChildPojoExample extends PojoExample {

    @HasRange(min = -40, max = 40)
    private int value4;

    public int getValue4() {
        return this.value4;
    }

    public void setValue4(int value4) {
        this.value4 = value4;
    }

}
