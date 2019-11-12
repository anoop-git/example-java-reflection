package com.ex.reflect;


public final class ReflectSingleton {

    private final static ReflectSingleton instance = new ReflectSingleton();

    public static ReflectSingleton instance() {
        return instance;
    }

    /**
     * Hide default constructor - should use the #instance() method
     */
    private ReflectSingleton(){
        super();
    }

    // TODO
}
