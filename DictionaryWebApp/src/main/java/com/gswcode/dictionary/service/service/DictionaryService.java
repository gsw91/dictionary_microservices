/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionary.service.service;

import com.gswcode.dictionary.service.client.RestClient;
import com.gswcode.dictionary.service.client.dto.DictionaryDto;
import com.gswcode.dictionary.service.client.mapper.Mapper;
import com.gswcode.dictionary.service.model.Dictionary;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gwojcik
 */
@Service
public class DictionaryService {

    @Autowired
    private RestClient restClient;

    @Autowired
    private Mapper mapper;

    private final List<Dictionary> dictionaries = new ArrayList<>();

    public List<Dictionary> getDictionaries() {
        return mapper.mapToModelList(Arrays.asList(restClient.getDictionaries()));
    }

    public String saveDictionary(Dictionary model) {
        if (model.getId() > 0) { //update
            DictionaryDto dto = restClient.getDictionaryById(model.getId());
            dto.setName(model.getName());
            dto.setDesciption(model.getDescription());
            dto.setAuthor(model.getAuthor());
            dto.setMasterDictionaryId(mapper.getMasterId(model.getMasterDictionaryName()));
            return restClient.updateDictionary(dto).getMessage();
        } else { //insert
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            model.setCreatedAt(LocalDateTime.now().format(formatter));
            DictionaryDto dto = mapper.mapToDto(model);
            return restClient.addDictionary(dto).getMessage();
        }
    }

    public Dictionary getDictionaryById(long id) {
        return mapper.mapToModel(restClient.getDictionaryById(id));
    }

    public String deactivateDictionary(long id) {
        return restClient.deactivateDictionary(id).getMessage();
    }

    public String activateDictionary(long id) {
        return restClient.activateDictionary(id).getMessage();
    }

    public void deleteDictionary(long id) {
        dictionaries.removeIf(t -> t.getId() == id);
    }

}
