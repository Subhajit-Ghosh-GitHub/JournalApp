package com.springEngineerSubha.jurnalApp.Service;

import com.springEngineerSubha.jurnalApp.Entry.User;
import com.springEngineerSubha.jurnalApp.Entry.journlEntry;
import com.springEngineerSubha.jurnalApp.Repository.JournalentryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class journalentryService {
    @Autowired
    private JournalentryRepository journalrepoRepo;


    @Autowired
    private UserService userService;
    @Transactional
    public void saveentry(journlEntry jentry, String username){
       try {
           User user = userService.findByUsername(username);
           jentry.setDate(LocalDateTime.now());
           journlEntry saved = journalrepoRepo.save(jentry);
           user.getJournalEntries().add(saved);
           //user.setUsername(null); //exception come
           userService.saveentry(user);
       }catch(Exception e){
           System.out.println(e);
           throw new RuntimeException("An eror while saving the entry ");
       }
    }
    public void saveentry(journlEntry jentry){

        journalrepoRepo.save(jentry);
    }
    public List<journlEntry> getall(){
        return journalrepoRepo.findAll();
    }
    public Optional<journlEntry> findid (ObjectId id){
        return journalrepoRepo.findById(id);
    }
    @Transactional
    public boolean deletebyid(ObjectId id, String username){
        boolean removed=false;
        try {
            User user = userService.findByUsername(username); // find user

             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));// it is importent to add currect time
            if (removed) {
                userService.saveentry(user); //user save
                journalrepoRepo.deleteById(id);
            }
        }catch(Exception e){
            log.error("error",e);
            throw new RuntimeException("An error occured while deleting the entry ", e);
        }
        return removed;

    }

//    public List<journlEntry> findByUsername(String username){
//
//    }

}
