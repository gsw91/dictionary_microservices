/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.config;

import com.gswcode.dictionarywebservice.service.DictConfService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author magda
 */
@Configuration
public class AppConfig {
    
    @Bean(name="dictConfService")
    public DictConfService dictConfService() {
        return new DictConfService();
    }

}
