package com.springEngineerSubha.jurnalApp.Controller;


import com.springEngineerSubha.jurnalApp.Entry.User;
import com.springEngineerSubha.jurnalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public") //uncthnticate
public class PublicController {

    @Autowired
    private UserService userService;
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewEntry(user);
    }
}
