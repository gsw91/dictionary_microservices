/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.mapper;

import com.gswcode.dictionarywebservice.domain.DictItem;
import com.gswcode.dictionarywebservice.dto.ItemDto;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author magda
 */
@Component
public class ItemMapper {
    
    private final static Logger LOGGER = Logger.getLogger(ItemMapper.class);
    
     public List<ItemDto> mapToListDto(List<DictItem> list) {
        List<ItemDto> dtos = new ArrayList<>();
        list.forEach(dictionary -> {
            dtos.add(mapToDto(dictionary));
        });
                
        LOGGER.debug("Objects mapped successfully, quantity: " + dtos.size());
        return dtos;
    }

    public ItemDto mapToDto(DictItem domain) {
        LOGGER.debug("Mapping item domain to dto: " + domain);
        long idDictConf = 0;
        if (domain.getIdDictConf() != null)
            idDictConf = domain.getIdDictConf().getId();       
        long idMasterItem = 0;
        if (domain.getAliasId() != null)
            idMasterItem = domain.getAliasId().getId();       
        return new ItemDto(
                domain.getId(),
                idDictConf,
                domain.getTermName(),
                domain.getTermDescription(),
                idMasterItem);    
    }
    
}
