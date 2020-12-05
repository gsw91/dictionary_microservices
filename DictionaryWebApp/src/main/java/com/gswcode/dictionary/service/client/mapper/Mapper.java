/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionary.service.client.mapper;

import com.gswcode.dictionary.service.client.dto.DictionaryDto;
import com.gswcode.dictionary.service.model.Dictionary;
import com.gswcode.dictionary.service.model.DictionaryKey;
import com.gswcode.dictionary.service.model.Status;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author magda
 */
@Service
public class Mapper {

    public final static Map<Long, DictionaryKey> namesMap = new HashMap<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final DateTimeFormatter formatterLDT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    
    public List<Dictionary> mapToModelList(List<DictionaryDto> dtos) {    
        List<Dictionary> modelList = new ArrayList<>();
        namesMap.clear();
        namesMap.put(0l, new DictionaryKey(0, ""));
        dtos.forEach(dto -> namesMap.put(dto.getId(), new DictionaryKey(dto.getId(), dto.getName())));
        dtos.forEach(dto -> {
            String status = dto.isIsActive() ? Status.ACTIVE : Status.ARCHIVED;
            String masterDictionaryName = namesMap.get(dto.getMasterDictionaryId()).getName();
            modelList.add(new Dictionary(dto.getId(),
                    dto.getName(),
                    dto.getDesciption(),
                    dto.getAuthor(),
                    LocalDateTime.parse(dto.getCreationAt()).format(formatter),
                    status,
                    dto.getMasterDictionaryId(),
                    masterDictionaryName,
                    dto.getTotalItems()));
        });
        return modelList;
    }

    public Dictionary mapToModel(DictionaryDto dto) {
        String status = dto.isIsActive() ? Status.ACTIVE : Status.ARCHIVED;
        String masterDictionaryName = namesMap.get(dto.getMasterDictionaryId()).getName();
        return new Dictionary(
                dto.getId(),
                dto.getName(),
                dto.getDesciption(),
                dto.getAuthor(),
                LocalDateTime.parse(dto.getCreationAt()).format(formatter),
                status,
                dto.getMasterDictionaryId(),
                masterDictionaryName,
                dto.getTotalItems());
    }
    
        public DictionaryDto mapToDto(Dictionary model) {
        boolean status = model.getStatus().equalsIgnoreCase(Status.ACTIVE);
        long masterDictionaryId = getMasterId(model.getMasterDictionaryName());
        return new DictionaryDto(
                masterDictionaryId,
                model.getName(),
                model.getDescription(),
                model.getAuthor(),
                Timestamp.valueOf(model.getCreatedAt()).toLocalDateTime().format(formatterLDT),              
                status,
                model.getItems());
    }
    
    public long getMasterId(String masterDictionaryName) {
        Optional<DictionaryKey> opt = namesMap.values().stream()
                .filter(t -> t.getName().equalsIgnoreCase(masterDictionaryName))
                .findFirst();
        if (opt.isPresent())
            return opt.get().getId();
        return 0;
    }

}
