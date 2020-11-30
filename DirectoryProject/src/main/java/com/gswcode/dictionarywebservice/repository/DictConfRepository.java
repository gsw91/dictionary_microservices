/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.repository;

import com.gswcode.dictionarywebservice.domain.DictConf;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author magda
 */
@Repository
public interface DictConfRepository extends JpaRepository<DictConf, Long> {
    
  @Query("select dc from DictConf dc where dc.dictName= ?1")
  Optional<DictConf> findByDictName(String name);
    
  @Query("select dc from DictConf dc where dc.isActive = true")
  List<DictConf> findAllActive();
  
}
