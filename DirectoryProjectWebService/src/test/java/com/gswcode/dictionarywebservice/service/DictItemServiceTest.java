/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.service;

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
public class DictItemServiceTest {

    @Autowired
    private ItemService service;

    @Test
    public void test2() {
        //given && when && then
        service.getActiveItemsByDictionaryId(1).forEach(System.out::println);
    }

}
