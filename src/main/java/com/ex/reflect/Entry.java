package com.ex.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Entry {

    private static final Logger log = LoggerFactory.getLogger(Entry.class);

    public static void main(String[] args) {
        log.info("Started...");

        log.info("Waiting for any key press to exit...");
        try (BufferedReader in = new BufferedReader(new InputStreamReader((System.in)))) {
            while (true) {
                if (null!=in.readLine()) {
                    // any teardown?
                    break;
                }
            }
        } catch (IOException ex) {
            log.error(ex.getLocalizedMessage());
            ex.printStackTrace();
        }

        System.exit(0);
    }
}
