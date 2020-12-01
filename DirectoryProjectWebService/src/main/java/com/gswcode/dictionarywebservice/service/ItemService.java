/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.service;

import com.gswcode.dictionarywebservice.domain.DictConf;
import com.gswcode.dictionarywebservice.domain.DictItem;
import com.gswcode.dictionarywebservice.repository.DictConfRepository;
import com.gswcode.dictionarywebservice.repository.DictItemRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author magda
 */
@Service
@Transactional
public class ItemService {

    @Autowired
    private DictConfRepository dictionaryRepo;

    @Autowired
    private DictItemRepository itemRepo;

    public List<DictItem> getActiveItemsByDictionaryId(long id) {
        Optional<DictConf> opt = dictionaryRepo.findById(id);
        List<Long> ids = new ArrayList<>();
        if (opt.isPresent()) {
            DictConf dictionary = opt.get();
            if (!dictionary.getIsActive()) {
                throw new RuntimeException();
            }
            ids.add(id);
            completeDictIdRecursively(ids, dictionary);
            return itemRepo.getActiveItemsByDictionariesId(ids);
        }
        return new ArrayList<>();
    }

    private void completeDictIdRecursively(List<Long> ids, DictConf dictConf) {
        System.out.println("checking recursive");
        dictConf.getDictConfList().forEach(subDict -> {
            System.out.println("found sub item");
            if (subDict.getIsActive()) {
                ids.add(subDict.getId());
                if (!subDict.getDictConfList().isEmpty()) {
                    completeDictIdRecursively(ids, subDict);
                }
            }
        });
    }

}
