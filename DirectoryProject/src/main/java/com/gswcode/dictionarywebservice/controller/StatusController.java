/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.controller;

import com.gswcode.dictionarywebservice.dto.ServiceStatusDto;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author magda
 */
@RestController
@RequestMapping("status")
public class StatusController {

    private final static Logger LOGGER = Logger.getLogger(StatusController.class);
    
    @GetMapping("/checkService")
    public ServiceStatusDto checkService() {       
        LOGGER.debug("Checking service status...");
        return new ServiceStatusDto(ControllerRegistry.getNextRegisterId());
    }

}
