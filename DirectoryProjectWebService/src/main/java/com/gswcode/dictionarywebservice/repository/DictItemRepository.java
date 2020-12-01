/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.repository;

import com.gswcode.dictionarywebservice.domain.DictItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author magda
 */
@Repository
public interface DictItemRepository extends JpaRepository<DictItem, Long> {
    
    @Query("SELECT d FROM DictItem d where d.idDictConf.id in (?1) and d.termActive = true")
    public List<DictItem> getActiveItemsByDictionariesId(List<Long> id);
    
}
