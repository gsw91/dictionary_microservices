package com.gswcode.dictionarywebservice.controller;

import com.gswcode.dictionarywebservice.dto.ItemDto;
import com.gswcode.dictionarywebservice.dto.ServiceStatusDto;
import com.gswcode.dictionarywebservice.mapper.ItemMapper;
import com.gswcode.dictionarywebservice.service.ItemService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class ItemController {

    private final static Logger LOGGER = Logger.getLogger(ItemController.class);
    
    @Autowired
    private ItemService service;
    
    @Autowired 
    private ItemMapper mapper;

    @GetMapping("/getList")
    public List<ItemDto> getList(@RequestParam("dictionaryId") long id) {
        LOGGER.debug("Loading items of diconary id: " + id);
        return mapper.mapToListDto(service.getActiveItemsByDictionaryId(id));
    }

    @PostMapping("/add")
    public ServiceStatusDto addItem(@RequestBody ItemDto item) {
        LOGGER.debug("Adding item: " + item);
        return new ServiceStatusDto();
    }

    @PutMapping("/update")
    public ServiceStatusDto updateItem(@RequestBody ItemDto item) {
        LOGGER.debug("Updating item: " + item);
        return new ServiceStatusDto();
    }
    
      @PutMapping("/deactivate")
    public ServiceStatusDto deactivateItem(@RequestBody ItemDto item) {
        LOGGER.debug("Deactivating item: " + item);
        return new ServiceStatusDto();
    }
    
     @DeleteMapping("/delete")
    public ServiceStatusDto deactivateItem(@RequestParam("itemId") long itemId) {
        LOGGER.debug("Deactivating item: " + itemId);
        return new ServiceStatusDto();
    }

}
