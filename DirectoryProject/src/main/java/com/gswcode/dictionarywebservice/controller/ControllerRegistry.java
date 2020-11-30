/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.controller;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author magda
 */
public class ControllerRegistry {
    
    private final static AtomicLong REQUEST_COUNTER = new AtomicLong();
    
    public static Long getNextRegisterId() {
        return REQUEST_COUNTER.incrementAndGet();
    } 
    
}
