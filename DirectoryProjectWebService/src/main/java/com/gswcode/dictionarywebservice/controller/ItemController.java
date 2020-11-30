package com.gswcode.dictionarywebservice.controller;

import com.gswcode.dictionarywebservice.dto.ItemDto;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class ItemController {
    
    private final static Logger LOGGER = Logger.getLogger(ItemController.class);

    @GetMapping("/getItemsByDictionaryId")
    public List<ItemDto> getDictionaries(@RequestParam("id") long id) {
        LOGGER.debug("Items of dictionary (by id " + id + "): " + 0);
        return new ArrayList<>();
    }
    
}
