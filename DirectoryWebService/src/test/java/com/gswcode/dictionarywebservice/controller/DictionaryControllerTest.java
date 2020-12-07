/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author magda
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DictionaryControllerTest {
    
    @Autowired
    private DictionaryController dictionaryController;
    
    @Test
    public void testGetList(){
        //given && when && then
        dictionaryController.getList();
    }    
}
