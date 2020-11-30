/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gswcode.dictionarywebservice.dto;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author magda
 */
public class DictionaryDto {

    private long id;
    private long dictionaryMasterId;
    private String name;
    private String desciption;
    private String author;
    private String creationAt;

    public DictionaryDto() {
    }

    public DictionaryDto(long id, long dictionaryMasterId, String name, String desciption, String author, String creationAt) {
        this.id = id;
        this.dictionaryMasterId = dictionaryMasterId;
        this.name = name;
        this.desciption = desciption;
        this.author = author;
        this.creationAt = creationAt;
    }

    public long getId() {
        return id;
    }

    public long getDictionaryMasterId() {
        return dictionaryMasterId;
    }

    public String getName() {
        return name;
    }

    public String getDesciption() {
        return desciption;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreationAt() {
        return creationAt;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DictionaryDto other = (DictionaryDto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DictionaryDto{" + "id=" + id + ", dictionaryMasterId=" + dictionaryMasterId + ", name=" + name + ", desciption=" + desciption + ", author=" + author + ", creationAt=" + creationAt + '}';
    }

}
