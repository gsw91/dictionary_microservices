package com.gswcode.dictionarywebservice.controller;

import com.gswcode.dictionarywebservice.dto.ItemDto;
import com.gswcode.dictionarywebservice.dto.ServiceStatusDto;
import com.gswcode.dictionarywebservice.mapper.ItemMapper;
import com.gswcode.dictionarywebservice.service.ItemService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("item")
public class ItemController {

    private final static Logger LOGGER = Logger.getLogger(ItemController.class);

    @Autowired
    private ItemService service;

    @Autowired
    private ItemMapper mapper;

    @GetMapping("/getList")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getList(@RequestParam("dictionaryId") long id) {
        LOGGER.debug("Loading items of diconary id: " + id);
        ControllerRegistry.getNextRegisterId();
        return mapper.mapToListDto(service.getActiveItemsByDictionaryId(id));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceStatusDto addItem(@RequestBody ItemDto item) {
        try {
            LOGGER.debug("Adding item: " + item);
            ServiceStatusDto status = service.addItem(mapper.mapToDomain(item));
            status.setRequestId(ControllerRegistry.getNextRegisterId());
            return status;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid request", ex);
        }
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ServiceStatusDto updateItem(@RequestBody ItemDto item) {
        try {
            LOGGER.debug("Updating item: " + item);
            ServiceStatusDto status = service.updateItem(mapper.mapToDomain(item));
            status.setRequestId(ControllerRegistry.getNextRegisterId());
            return status;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid request", ex);
        }
    }

    @PutMapping("/deactivate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ServiceStatusDto deactivateItem(@RequestParam("itemId") long itemId) {
        LOGGER.debug("Deactivating item: " + itemId);
        ServiceStatusDto status = service.deactivate(itemId);
        status.setRequestId(ControllerRegistry.getNextRegisterId());
        return status;
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ServiceStatusDto deleteItem(@RequestParam("itemId") long itemId) {
        LOGGER.debug("Deleting item: " + itemId);
        ServiceStatusDto status = service.delete(itemId);
        status.setRequestId(ControllerRegistry.getNextRegisterId());
        return status;
    }

}
