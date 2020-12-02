package com.gswcode.dictionarywebservice.service;

import com.gswcode.dictionarywebservice.domain.DictConf;
import com.gswcode.dictionarywebservice.dto.DictionaryDto;
import com.gswcode.dictionarywebservice.mapper.DictionaryMapper;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictConfServiceTest {

    @Autowired
    @Qualifier("dictConfService")
    private DictionaryService service;

    @Autowired
    private DictionaryMapper mapper;

    @Ignore
    @Test
    public void testUpdateDictionaryWithMapperV2() {
        //given
        DictionaryDto dto = new DictionaryDto(
                3,
                1,
                "Test7",
                "Test dictionary - mapper junit test",
                "gwojcik",
                "2020-01-27T20:01:00");
        //when&&then
        DictConf dictConf = mapper.mapToDomain(dto);
        service.update(dictConf);
    }

    @Ignore
    @Test
    public void testUpdateDictionaryWithMapper() {
        //given
        DictionaryDto dto = new DictionaryDto(
                3,
                0,
                "Test7",
                "Test dictionary - mapper junit test",
                "gwojcik",
                "2020-01-27T20:01:00");
        //when&&then
        DictConf dictConf = mapper.mapToDomain(dto);
        service.update(dictConf);
    }

    @Ignore
    @Test
    public void testUpdateDictionary() {
        //given
        DictConf dictConf = new DictConf(3l, "First3");
        dictConf.setDictDescription("First dictionary of this application");
        dictConf.setAuthor("gwojcik");
        dictConf.setIsActive(true);
        dictConf.setCreationTime(Timestamp.valueOf(LocalDateTime.parse("2020-11-29T22:38:17")));
        //when&&then
        service.save(dictConf);
    }

    @Ignore
    @Test
    public void testFindActiveDirectories() {
        //given
        List<DictConf> list = service.getActiveDictionaryList();
        //when&&then
        list.forEach(System.out::println);
    }

}
