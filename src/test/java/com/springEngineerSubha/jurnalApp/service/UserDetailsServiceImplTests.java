package com.springEngineerSubha.jurnalApp.service;

import com.springEngineerSubha.jurnalApp.Entry.User;
import com.springEngineerSubha.jurnalApp.Repository.UserRepository;
import com.springEngineerSubha.jurnalApp.Service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class UserDetailsServiceImplTests {
    @InjectMocks // automatcily instance create inject moc
    private UserDetailsServiceImpl userDetailsService;

    @Mock//@Mock use when we use @injectMocks ,@MockBean  mock will replace by actual repository
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    void loadUserByUsernameTest(){
        //when(userRepository.findByUsername("ram")).thenReturn(User.builder().username("ram").password("inrinrick").build());
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("ram").password("inrinrick").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("ram"); //when its call then not abtual repository call mock will call
        Assertions.assertNotNull(user);
    }
}
