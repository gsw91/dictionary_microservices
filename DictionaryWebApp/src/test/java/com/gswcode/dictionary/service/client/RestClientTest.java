/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionary.service.client;

import com.gswcode.dictionary.service.client.dto.DictionaryDto;
import java.util.Arrays;
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
public class RestClientTest {

    @Autowired
    private RestClient restClient;

    @Test
    public void testEmpty() {
        //given && when && then
    }

    @Test
    public void testGetDictionaries() {
        //given && when
        DictionaryDto[] array = restClient.getDictionaries();
        // then
        Arrays.stream(array).forEach(System.out::println);
    }

}
