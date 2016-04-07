package com.qfree.rocketlauncher.tools;


import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    private static AtomicInteger counter = new AtomicInteger(0);

    public static int getNext() {
        return counter.incrementAndGet();
    }

}
