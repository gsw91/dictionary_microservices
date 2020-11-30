/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.mapper;

import com.google.gson.Gson;
import com.gswcode.dictionarywebservice.domain.DictConf;
import com.gswcode.dictionarywebservice.dto.DictionaryDto;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author magda
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MapperTest {

    @Test
    public void testMapToDomainUpdate() {
        //given
        DictionaryMapper mapper = new DictionaryMapper();
        DictionaryDto dto = new DictionaryDto(
                3,
                1,
                "Test7",
                "Test dictionary - mapper junit test",
                "gwojcik",
                "2020-01-27T20:01:00");
        //when
        DictConf dictConf = mapper.mapToDomain(dto);
        //then
        Assert.assertEquals(Long.valueOf(3), dictConf.getId());
        Assert.assertEquals("Test7", dictConf.getDictName());
        Assert.assertEquals("Test dictionary - mapper junit test", dictConf.getDictDescription());
        Assert.assertEquals("gwojcik", dictConf.getAuthor());
        Assert.assertEquals(Timestamp.valueOf("2020-01-27 20:01:00"), dictConf.getCreationTime());
        System.out.println(dictConf);
    }

    @Test
    public void testMapToDomain() {
        //given
        DictionaryMapper mapper = new DictionaryMapper();
        DictionaryDto dto = new DictionaryDto(
                0,
                0,
                "First",
                "First dictionary of this application",
                "gwojcik",
                "2020-01-27T20:01:00");
        //when
        DictConf dictConf = mapper.mapToDomain(dto);
        //then
        Assert.assertEquals(Long.valueOf(0), dictConf.getId());
        Assert.assertNull(dictConf.getMasterDictId());
        Assert.assertEquals("First", dictConf.getDictName());
        Assert.assertEquals("First dictionary of this application", dictConf.getDictDescription());
        Assert.assertEquals("gwojcik", dictConf.getAuthor());
        Assert.assertEquals(Timestamp.valueOf("2020-01-27 20:01:00"), dictConf.getCreationTime());
        System.out.println(dictConf);
    }

    @Test
    public void testMapToDto() {
        //given
        DictionaryMapper mapper = new DictionaryMapper();
        DictConf dictConf = new DictConf();
        dictConf.setId(1l);
        dictConf.setDictName("First");
        dictConf.setDictDescription("First dictionary of this application");
        dictConf.setIsActive(true);
        dictConf.setCreationTime(Timestamp.valueOf("2020-11-27 20:01:00.0"));
        dictConf.setAuthor("gwojcik");

        DictConf subDictConf = new DictConf();
        subDictConf.setId(2l);
        subDictConf.setDictName("Second");
        subDictConf.setDictDescription("Second directory of this app");
        subDictConf.setIsActive(true);
        subDictConf.setCreationTime(Timestamp.valueOf("2020-11-27 23:12:21.0"));
        subDictConf.setAuthor("gwojcik");

        List<DictConf> subDictConfs = new ArrayList<>();
        subDictConfs.add(subDictConf);
        dictConf.setDictConfList(subDictConfs);
        //when
        DictionaryDto dto = mapper.mapToDto(dictConf);
        //then
        System.out.println(dto);

        Gson gson = new Gson();
        System.out.println(gson.toJson(dto));
    }

}
