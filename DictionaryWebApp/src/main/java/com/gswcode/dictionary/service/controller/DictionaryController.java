/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionary.service.controller;

import com.gswcode.dictionary.service.client.mapper.DictionaryMapper;
import com.gswcode.dictionary.service.client.mapper.ItemMapper;
import com.gswcode.dictionary.service.model.Dictionary;
import com.gswcode.dictionary.service.model.Item;
import com.gswcode.dictionary.service.service.DictionaryService;
import com.gswcode.dictionary.service.service.ItemService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author gwojcik
 */
@Controller
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;
    
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("dictionaryList", dictionaryService.getDictionaries());
        return "dictionary_list";
    }

    @GetMapping("/newDictionaryForm")
    public String showNewDictionaryForm(Model model) {
        Dictionary dictionary = new Dictionary();
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("dictionaries", DictionaryMapper.NAMES_MAP.values());
        return "new_dictionary";
    }

    @PostMapping("/saveDictionary")
    public String saveDictionary(@ModelAttribute("dictionary") Dictionary dictionary, Model model, RedirectAttributes redirAttrs) {
        System.out.println("Saving" + dictionary);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
        dictionary.setCreatedAt(LocalDateTime.now().format(formatter));
        dictionary.setStatus("Active");
        String message = dictionaryService.saveDictionary(dictionary);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Dictionary dictionary = dictionaryService.getDictionaryById(id);
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("dictionaries", DictionaryMapper.NAMES_MAP.values());
        return "update_dictionary";
    }

    @GetMapping("/deactivateDictionary/{id}")
    public String deactivateDictionary(@PathVariable(value = "id") long id, Model model, RedirectAttributes redirAttrs) {
        String message = dictionaryService.deactivateDictionary(id);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/";
    }

    @GetMapping("/activateDictionary/{id}")
    public String activateDictionary(@PathVariable(value = "id") long id, Model model, RedirectAttributes redirAttrs) {
        String message = dictionaryService.activateDictionary(id);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/";
    }

    @GetMapping("/deleteDictionary/{id}")
    public String deleteDictionary(@PathVariable(value = "id") long id, Model model, RedirectAttributes redirAttrs) {
        String message = dictionaryService.deleteDictionary(id);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/";
    }
    
    @GetMapping("/showItems/{dictionaryId}")
    public String showItems(@PathVariable(value = "dictionaryId") long dictionaryId, Model model, RedirectAttributes redirAttrs) {
        String headerInfo = "Items of dictionary " + DictionaryMapper.NAMES_MAP.get(dictionaryId).getName();
        model.addAttribute("itemList", itemService.getItems(dictionaryId));
        model.addAttribute("headerInfo", headerInfo);
        model.addAttribute("dictionaryId", dictionaryId);
        return "item_list";
    }
    
    @GetMapping("/newItemForm/{dictionaryId}")
    public String showNewItemForm(@PathVariable(value = "dictionaryId") long dictionaryId, Model model) {
        Item item = new Item();       
        item.setDictionaryId(dictionaryId);
        model.addAttribute("item", item);
        model.addAttribute("items", ItemMapper.ITEM_NAMES_MAP.values());
        return "new_item";
    }
    
    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute("item") Item item, Model model, RedirectAttributes redirAttrs) {
        System.out.println("Saving" + item);   
        String message = itemService.saveItem(item);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/showItems/" + item.getDictionaryId();
    }
    
    @GetMapping("/updateItemForm/{id}")
    public String updateItemForm(@PathVariable(value = "id") long id, Model model) {
        Item item = itemService.getItemById(id);
        model.addAttribute("id_dictionary", item.getDictionaryId());
        model.addAttribute("item", item);
        model.addAttribute("items", ItemMapper.ITEM_NAMES_MAP.values());
        return "update_item";
    }
    
    @GetMapping("/deactivateItem/{dictionaryId}/{itemId}")
    public String deactivateItem(@PathVariable(value = "dictionaryId") long dictionaryId, @PathVariable(value = "itemId") long itemId, Model model, RedirectAttributes redirAttrs) {
        String message = itemService.deactivateItem(itemId);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/showItems/" + dictionaryId;
    }

    @GetMapping("/activateItem/{dictionaryId}/{itemId}")
    public String activateItem(@PathVariable(value = "dictionaryId") long dictionaryId, @PathVariable(value = "itemId") long itemId, Model model, RedirectAttributes redirAttrs) {
        String message = itemService.activateItem(itemId);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/showItems/" + dictionaryId;
    }

    @GetMapping("/deleteItem/{dictionaryId}/{itemId}")
    public String deleteItem(@PathVariable(value = "dictionaryId") long dictionaryId, @PathVariable(value = "itemId") long itemId, Model model, RedirectAttributes redirAttrs) {
        String message = itemService.deleteItem(itemId);
        redirAttrs.addFlashAttribute("success", message);
        return "redirect:/showItems/" + dictionaryId;
    }

}
