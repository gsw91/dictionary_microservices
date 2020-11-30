/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.dto.response;

/**
 *
 * @author magda
 */
public class UpdateStatus {
    
    private final boolean result;
    private final String message;

    public UpdateStatus(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
    
}
