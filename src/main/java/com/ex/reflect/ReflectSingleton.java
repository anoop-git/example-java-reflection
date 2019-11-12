package com.ex.reflect;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ReflectSingleton {

    private static final Logger log = LoggerFactory.getLogger(ReflectSingleton.class);

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
