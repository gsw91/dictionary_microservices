/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionary.service.client;

import com.google.gson.Gson;
import com.gswcode.dictionary.service.client.dto.DictionaryDto;
import com.gswcode.dictionary.service.client.dto.ItemDto;
import com.gswcode.dictionary.service.client.dto.ServiceStatusDto;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RestClient {

    @Autowired
    private RestTemplate restTemplate;

    private final Gson gson = new Gson();

    public DictionaryDto[] getDictionaries() {
        return restTemplate.getForObject("http://localhost:8080/DictionaryWebService/dictionary/getList", DictionaryDto[].class);
    }

    public DictionaryDto getDictionaryById(long id) {
        return restTemplate.getForObject("http://localhost:8080/DictionaryWebService/dictionary/getById?dictionaryId=" + id, DictionaryDto.class);
    }

    public ServiceStatusDto updateDictionary(DictionaryDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> requestUpdate = new HttpEntity<>(gson.toJson(dto), headers);
        ResponseEntity<ServiceStatusDto> res = restTemplate.exchange("http://localhost:8080/DictionaryWebService/dictionary/update", HttpMethod.PUT, requestUpdate, ServiceStatusDto.class);
        return res.getBody();
    }

    public ServiceStatusDto addDictionary(DictionaryDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> requestAdd = new HttpEntity<>(gson.toJson(dto), headers);
        ResponseEntity<ServiceStatusDto> res = restTemplate.exchange("http://localhost:8080/DictionaryWebService/dictionary/add", HttpMethod.POST, requestAdd, ServiceStatusDto.class);
        return res.getBody();
    }

    public ServiceStatusDto deactivateDictionary(Long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/DictionaryWebService/dictionary/deactivate")
                .queryParam("dictionaryId", id);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ServiceStatusDto> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.PUT, entity, ServiceStatusDto.class);
        return res.getBody();
    }

    public ServiceStatusDto activateDictionary(Long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/DictionaryWebService/dictionary/activate")
                .queryParam("dictionaryId", id);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ServiceStatusDto> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.PUT, entity, ServiceStatusDto.class);
        return res.getBody();
    }

    public ServiceStatusDto deleteDictionary(Long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/DictionaryWebService/dictionary/delete")
                .queryParam("dictionaryId", id);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ServiceStatusDto> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.DELETE, entity, ServiceStatusDto.class);
        return res.getBody();
    }

    public List<ItemDto> getItemsByDictionaryId(long dictionaryId) {
        return Arrays.asList(restTemplate.getForObject("http://localhost:8080/DictionaryWebService/item/getList?dictionaryId=" + dictionaryId, ItemDto[].class));
    }

    public ServiceStatusDto addItem(ItemDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> requestAdd = new HttpEntity<>(gson.toJson(dto), headers);
        ResponseEntity<ServiceStatusDto> res = restTemplate.exchange("http://localhost:8080/DictionaryWebService/item/add", HttpMethod.POST, requestAdd, ServiceStatusDto.class);
        return res.getBody();
    }

}
