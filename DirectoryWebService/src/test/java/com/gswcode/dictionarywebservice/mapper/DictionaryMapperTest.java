package com.gswcode.dictionarywebservice.mapper;

import com.google.gson.Gson;
import com.gswcode.dictionarywebservice.domain.DictConf;
import com.gswcode.dictionarywebservice.dto.DictionaryDto;
import com.gswcode.dictionarywebservice.service.ItemService;
import com.gswcode.dictionarywebservice.util.Common;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictionaryMapperTest {

    @MockBean
    private ItemService itemService;
    
    @Autowired
    private DictionaryMapper dictionaryMapper;
    
    @Test
    public void testMapToDomainUpdate() {
        //given
        DictionaryDto dto = new DictionaryDto(
                3,
                1,
                "Test7",
                "Test dictionary - mapper junit test",
                "gwojcik",
                "2020-01-27T20:01:00",
                true,
                20);
        //when
        DictConf dictConf = dictionaryMapper.mapToDomain(dto);
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
        DictionaryDto dto = new DictionaryDto(
                0,
                0,
                "First",
                "First dictionary of this application",
                "gwojcik",
                "2020-01-27T20:01:00",
                true,
                20);
        //when
        DictConf dictConf = dictionaryMapper.mapToDomain(dto);
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
        
        when(itemService.getItemsByDictionaryId(11)).thenReturn(new ArrayList<>());
        when(itemService.getItemsByDictionaryId(21)).thenReturn(new ArrayList<>());
        //when
        DictionaryDto dto = dictionaryMapper.mapToDto(dictConf);
        //then
        System.out.println(dto);

        Gson gson = new Gson();
        System.out.println(gson.toJson(dto));
    }
    
    @Test
    public void testFormatter() {
        //given
        LocalDateTime ldt = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(ldt);
        //when
        String formatted = checkDateTime(timestamp);
        //then
        System.out.println("Date time " + ldt);
        System.out.println("Timestamp " + timestamp);
        System.out.println("Formatted " + formatted);
//        Assert.assertEquals(formatted, this);
    }
    
    private String checkDateTime(Timestamp timestamp) {      
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().format(Common.LOCAL_DT_FORMATTER);
    }

}
