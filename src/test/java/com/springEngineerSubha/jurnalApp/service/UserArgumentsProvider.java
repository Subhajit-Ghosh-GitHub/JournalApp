package com.springEngineerSubha.jurnalApp.service;

import com.springEngineerSubha.jurnalApp.Entry.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                //not use constructer use builder
                Arguments.of(User.builder().username("hellow").password("hellow").build()),
                Arguments.of(User.builder().username("hii").password("hii").build()) //empty password but convert into stringpassword
        );
    }
}
