package com.gswcode.dictionarywebservice.controller;

import com.gswcode.dictionarywebservice.dto.ServiceStatusDto;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("status")
public class StatusController {

    private final static Logger LOGGER = Logger.getLogger(StatusController.class);
    
    @GetMapping("/checkService")
    public ServiceStatusDto checkService() {       
        LOGGER.debug("Checking service status...");
        ServiceStatusDto status = new ServiceStatusDto(ControllerRegistry.getNextRegisterId());
        status.setSuccess(true);
        return status;
    }

}
