package com.gswcode.dictionarywebservice.controller;

import java.util.concurrent.atomic.AtomicLong;

public class ControllerRegistry {
    
    private final static AtomicLong REQUEST_COUNTER = new AtomicLong();
    
    public static Long getNextRegisterId() {
        return REQUEST_COUNTER.incrementAndGet();
    } 
    
}
