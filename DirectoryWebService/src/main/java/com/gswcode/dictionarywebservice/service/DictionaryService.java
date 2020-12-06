package com.gswcode.dictionarywebservice.service;

import com.gswcode.dictionarywebservice.domain.DictConf;
import com.gswcode.dictionarywebservice.dto.ServiceStatusDto;
import com.gswcode.dictionarywebservice.repository.DictConfRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DictionaryService {

    private final static Logger LOGGER = Logger.getLogger(DictionaryService.class);

    @Autowired
    private DictConfRepository repo;

    public List<DictConf> getDictionaryList() {
        return repo.findAll();
    }

    public DictConf getDictionaryById(long id) {
        Optional<DictConf> opt = repo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            return new DictConf();
        }
    }

    public ServiceStatusDto save(DictConf dictConf) {
        LOGGER.debug("Saving object: " + dictConf);
        Optional<DictConf> optDict = repo.findByDictName(dictConf.getDictName());
        ServiceStatusDto status = new ServiceStatusDto();
        if (optDict.isPresent()) {
            String msg = "Dictionary with name " + dictConf.getDictName() + " already exists! Please set other name";
            LOGGER.debug(msg);
            status.setMessage(msg);
            status.setSuccess(false);
        } else {
            dictConf.setIsActive(true);
            if (dictConf.getCreationTime() == null) {
                dictConf.setCreationTime(Timestamp.valueOf(LocalDateTime.now()));
            }
            repo.save(dictConf);
            boolean isObjectSaved = dictConf.getId() != null;
            LOGGER.debug("is object saved: " + isObjectSaved);
            if (isObjectSaved) {
                status.setMessage("Object added");
                status.setSuccess(true);
            } else {
                status.setMessage("We can not add new dictionary at this moment, please try later");
                status.setSuccess(false);
            }
        }
        return status;
    }

    public ServiceStatusDto update(DictConf dictConf) {
        LOGGER.debug("Updating object: " + dictConf);
        ServiceStatusDto status = new ServiceStatusDto();

        Optional<DictConf> checkName = repo.findByDictName(dictConf.getDictName());
        if (checkName.isPresent() && checkName.get().getId().longValue() != dictConf.getId().longValue()) {
            String msg = "Dictionary with name " + dictConf.getDictName() + " already exists! Please set other name";
            LOGGER.debug(msg);
            status.setMessage(msg);
            status.setSuccess(false);
            return status;
        }
        Optional<DictConf> optDict = repo.findById(dictConf.getId());
        if (optDict.isPresent()) {
            DictConf domain = optDict.get();
            domain.setDictName(dictConf.getDictName());
            domain.setDictDescription(dictConf.getDictDescription());
            domain.setAuthor(dictConf.getAuthor());
            domain.setMasterDictId(dictConf.getMasterDictId());
            repo.save(domain);
            status.setMessage("Dictionary updated");
            status.setSuccess(true);
        } else {
            status.setMessage("We can not update thew dictionary at this moment, please try later");
            status.setSuccess(false);
        }
        return status;
    }

    public ServiceStatusDto deactivate(long id) {
        LOGGER.debug("Deactivating dictionary: " + id);
        ServiceStatusDto status = new ServiceStatusDto();
        Optional<DictConf> optDict = repo.findById(id);
        if (optDict.isPresent()) {
            DictConf domain = optDict.get();
            if (domain.getIsActive()) {
                domain.setIsActive(false);
                domain.setDeactivationTime(Timestamp.valueOf(LocalDateTime.now()));
                domain.getDictItemList().forEach(t -> t.setTermActive(false));
                repo.save(domain);
                status.setMessage("Dictionary with items deactivated: " + domain.getDictName());
                status.setSuccess(true);
            } else {
                status.setMessage("Dictionary " + domain.getDictName() + " is already archived!");
                status.setSuccess(false);
            }
        } else {
            status.setMessage("We can not update thew dictionary at this moment, please try later");
            status.setSuccess(false);
        }
        return status;
    }
    
    public ServiceStatusDto activate(long id) {
        LOGGER.debug("Activating dictionary: " + id);
        ServiceStatusDto status = new ServiceStatusDto();
        Optional<DictConf> optDict = repo.findById(id);
        if (optDict.isPresent()) {
            DictConf domain = optDict.get();
            if (!domain.getIsActive()) {
                domain.setIsActive(true);  
                domain.getDictItemList().forEach(t -> t.setTermActive(true));
                repo.save(domain);
                status.setMessage("Dictionary with items activated: " + domain.getDictName());
                status.setSuccess(true);
            } else {
                status.setMessage("Dictionary " + domain.getDictName() + " is already active!");
                status.setSuccess(false);
            }
        } else {
            status.setMessage("We can not update thew dictionary at this moment, please try later");
            status.setSuccess(false);
        }
        return status;
    }
    
    public ServiceStatusDto delete(long id) {
        boolean exists = repo.findById(id).isPresent();
        ServiceStatusDto status = new ServiceStatusDto();
        if (exists) {
            repo.deleteById(id);
            status.setMessage("Dictionary has been deleted");
            status.setSuccess(true);
        } else {
            status.setMessage("That dictionary doesn't exist");
            status.setSuccess(false);
        }   
        return status;
    }

}
