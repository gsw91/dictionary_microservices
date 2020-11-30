package com.gswcode.dictionarywebservice.dto;

import java.util.List;
import java.util.Objects;

public class ItemDto {
    
    private Long id;
    private Long dictionaryId;
    private String termName;
    private String termDescription;
    private boolean termActive;
    private List<Long> aliases;
    private Long masterItemId;

    public ItemDto(Long id, Long dictionaryId, String termName, String termDescription, boolean termActive, List<Long> aliases, Long masterItemId) {
        this.id = id;
        this.dictionaryId = dictionaryId;
        this.termName = termName;
        this.termDescription = termDescription;
        this.termActive = termActive;
        this.aliases = aliases;
        this.masterItemId = masterItemId;
    }
    
    public ItemDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }
    
    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermDescription() {
        return termDescription;
    }

    public void setTermDescription(String termDescription) {
        this.termDescription = termDescription;
    }

    public boolean isTermActive() {
        return termActive;
    }

    public void setTermActive(boolean termActive) {
        this.termActive = termActive;
    }

    public List<Long> getAliases() {
        return aliases;
    }

    public void setAliases(List<Long> aliases) {
        this.aliases = aliases;
    }

    public Long getMasterItemId() {
        return masterItemId;
    }

    public void setMasterItemId(Long masterItemId) {
        this.masterItemId = masterItemId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final ItemDto other = (ItemDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemDto{" + "id=" + id + ", dictionaryId=" + dictionaryId + ", termName=" + termName + ", termDescription=" + termDescription + ", termActive=" + termActive + ", aliases=" + aliases + ", masterItemId=" + masterItemId + '}';
    }

}
