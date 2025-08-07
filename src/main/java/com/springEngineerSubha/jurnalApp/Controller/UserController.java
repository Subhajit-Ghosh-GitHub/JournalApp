package com.springEngineerSubha.jurnalApp.Controller;

import com.springEngineerSubha.jurnalApp.Entry.User;
import com.springEngineerSubha.jurnalApp.Repository.UserRepository;
import com.springEngineerSubha.jurnalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")   // add mapping on class
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PutMapping
    public  ResponseEntity<?>updateUser(@RequestBody User user) {
        //username & password call PUT that autmaticily comes not need to pass by
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // from security context we will catch username
        String username = authentication.getName();
        User userinDB = userService.findByUsername(username);
        userinDB.setUsername(user.getUsername());
        userinDB.setPassword(user.getPassword());
        userService.saveNewEntry(userinDB); //serveentry replace

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public  ResponseEntity<?>deleteUserById() {
        //username & password call PUT that autmaticily comes not need to pass by
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUsername(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


