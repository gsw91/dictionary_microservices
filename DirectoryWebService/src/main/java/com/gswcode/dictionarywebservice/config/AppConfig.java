/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.config;

import com.gswcode.dictionarywebservice.service.DictionaryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class AppConfig {
    
    @Bean(name="dictConfService")
    public DictionaryService dictConfService() {
        return new DictionaryService();
    }
    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.gswcode.dictionarywebservice.controller"))              
          .paths(PathSelectors.any())                          
          .build();                                           
    }

}
