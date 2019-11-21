package com.ex.aspect.sample;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoAround {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public DemoAround() { super(); }

    public void myMethod() { log.info("DemoAround#myMethod() inside"); }

    public String ackValue(String foo) { return foo; }

}
