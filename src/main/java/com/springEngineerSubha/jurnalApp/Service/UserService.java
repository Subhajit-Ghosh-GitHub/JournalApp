package com.springEngineerSubha.jurnalApp.Service;

import com.springEngineerSubha.jurnalApp.Entry.User;
import com.springEngineerSubha.jurnalApp.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;



    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(); ///password encoder implementaion as BCryptPasswordEncodder

//    public void saveNewEntry(User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword())); // Convert hash and save it
//        user.setRoles(Arrays.asList("USER"));
//        userRepository.save(user);
//    }
    public boolean saveNewEntry(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword())); // Convert hash and save it
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        }catch(Exception e){
            log.error("error occured for{} :",user.getUsername(),e);
            log.trace("trace occured for{} :",user.getUsername(),e);
            log.info("info occured for{} :",user.getUsername(),e);
            log.debug("debug occured for{} :",user.getUsername(),e);
            log.error("error occured for{} :",user.getUsername(),e);

            return false;
        }
    }

    public void saveentry(User user){ // use it letter  saveentry
    // not again encoded passwor in this case
        userRepository.save(user);
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Convert hash and save it
        user.setRoles(Arrays.asList("USER","ADMIN")); // first we have to create a manual Admin sothat he can acess
        userRepository.save(user);
    }

    public List<User> getall(){
        return userRepository.findAll();
    }
    public Optional<User> findid (ObjectId id){
        return userRepository.findById(id);
    }
    public void deletebyid(ObjectId id){
        userRepository.deleteById(id);
    }
    public User findByUsername (String username){
        return userRepository.findByUsername(username);
    }
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

}
