/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.controller;

import com.gswcode.dictionarywebservice.fileprocessor.FileUpdateService;
import com.gswcode.dictionarywebservice.dto.WebhookRequest;
import com.gswcode.dictionarywebservice.dto.WebhookResponse;
import com.gswcode.dictionarywebservice.fileprocessor.TerminationStatus;
import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("update")
public class WebhookController {

    @Autowired
    private FileUpdateService service;

    @PostMapping("/setWebhook")
    @ResponseStatus(HttpStatus.OK)
    public WebhookResponse add(@RequestBody WebhookRequest request) {
        if (!request.getWebhookUrl().contains("http")) {
            throw new IncorrectUrlException();
        }
        LOGGER.debug("Received request to update from file, searching new files...");
        long filesQuantity = service.runUpdate(request.getWebhookUrl());
        return new WebhookResponse(filesQuantity);
    }
    
    @GetMapping("/terminate")
    @ResponseStatus(HttpStatus.OK)
    public TerminationStatus terminateUpdating() {       
        LOGGER.debug("Update process termination...");
        TerminationStatus status = service.stopProcessing();
        if (status.getTeminatedFileName().isEmpty()) {
            throw new NoProcessingException();
        } else {
            return status;
        }
    }
    

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Incorect webhook url, it must start with *http*")
    public class IncorrectUrlException extends RuntimeException {
        public IncorrectUrlException() {
            super(null, null, false, false);
        }             
    }

    @ResponseStatus(code = HttpStatus.OK, reason = "At this moment there is no updating")
    public class NoProcessingException extends RuntimeException {
        public NoProcessingException() {
            super(null, null, false, false);
        }              
    }
    
}
