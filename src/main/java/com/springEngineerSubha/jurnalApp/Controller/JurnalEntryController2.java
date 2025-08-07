package com.springEngineerSubha.jurnalApp.Controller;

import com.springEngineerSubha.jurnalApp.Entry.User;
import com.springEngineerSubha.jurnalApp.Entry.journlEntry;
import com.springEngineerSubha.jurnalApp.Service.UserService;
import com.springEngineerSubha.jurnalApp.Service.journalentryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")   // add mapping on class
public class JurnalEntryController2 {

    @Autowired
    private journalentryService jservice;

    @Autowired
    private UserService userservice;

    @GetMapping
    public ResponseEntity<?> getAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // from security context we will catch username
        String username = authentication.getName();
        User user =userservice.findByUsername(username);// from detabase we got username
        List<journlEntry> getall = user.getJournalEntries();
        if(getall!=null && getall.isEmpty()){
            return new ResponseEntity<>(getall,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody  journlEntry entry){

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // from security context we will catch username
            String username = authentication.getName();
            jservice.saveentry(entry,username);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/id/{getid}")
    public ResponseEntity<journlEntry> getidgernlid(@PathVariable ObjectId getid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // from security context we will catch username
        String username = authentication.getName();

        User user = userservice.findByUsername(username);
        // find among all userentries that myid(getid) is present or not
        List<journlEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(getid)).collect(Collectors.toList());
        if(!collect.isEmpty()){ //not e,pty meance give currect user and id
            Optional<journlEntry> findid = jservice.findid(getid);
            if(findid.isPresent()){
                return new ResponseEntity<>(findid.get(), HttpStatus.OK);
            }
        }


        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deletemapid(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // from security context we will catch username
        String username = authentication.getName();

        boolean removed =jservice.deletebyid(myid,username);
        if(removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/id/{myid}")
    public ResponseEntity<?> putmapid(@PathVariable ObjectId myid,@RequestBody journlEntry newentry){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        // First of all find user details u
        User user = userservice.findByUsername(username);
        // find among all userentries that myid(getid) is present or not
        List<journlEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()){ //not e,pty meance give currect user and id
            Optional<journlEntry> journalEntry = jservice.findid(myid);
            if(journalEntry.isPresent()){
                journlEntry old = journalEntry.get();
                old.setTitle(newentry.getTitle()!=null && !newentry.getTitle().equals("")?newentry.getTitle(): old.getTitle());
                old.setId(newentry.getId()!=null && !newentry.getId().equals("")?newentry.getId():old.getId());
                jservice.saveentry(old);
                return new ResponseEntity<>(old,HttpStatus.OK); // if old one is found then retuen ok


            }
        }


        journlEntry oldentry = jservice.findid(myid).orElse(null);


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
