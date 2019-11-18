package com.ex.reflect;

final class ChildPojoHasRangeExample extends PojoHasRangeExample<ChildPojoHasRangeExample> {

    public ChildPojoHasRangeExample() { super(); }

    @HasRange(min = -40, max = 40)
    private int value4;

    public ChildPojoHasRangeExample withValue4(int value4) { setValue4(value4); return me(); }

    public int getValue4() {
        return this.value4;
    }

    public void setValue4(int value4) {
        this.value4 = value4;
    }

}
