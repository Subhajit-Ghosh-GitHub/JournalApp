package com.springEngineerSubha.jurnalApp.service;

import com.springEngineerSubha.jurnalApp.Entry.User;
import com.springEngineerSubha.jurnalApp.Repository.UserRepository;
import com.springEngineerSubha.jurnalApp.Service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.method.P;

import static com.mongodb.assertions.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
//
//    @Disabled
//    @Test
//    public void testFindByUsername(){ // this method meance one test case
////        assertEquals(4,2+2);
////        assertNotNull(userRepository.findByUsername("ram"));
//        User user = userRepository.findByUsername("ram");
//        assertTrue(!user.getJournalEntries().isEmpty());
//
//    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "movie",
            "ram",
            "subhajit"
    })
    public void paramtestfindbyusername(String name){
        assertNotNull(userRepository.findByUsername(name) );
    }

    // you can pass here like this also .. ints=  strings=
//    @ValueSource(strings ={
//            "movie",
//            "ram",
//            "subhajit"
//    })

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewuser(User user){
        assertTrue(userService.saveNewEntry(user));
    }





    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test1(int a,int b,int expected){
        assertEquals(expected,a+b);

    }

}
