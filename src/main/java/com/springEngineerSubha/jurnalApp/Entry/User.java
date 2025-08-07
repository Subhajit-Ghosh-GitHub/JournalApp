package com.springEngineerSubha.jurnalApp.Entry;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
@Builder
public class User {  //pojo plain old java object
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username; // it should unique
    @NonNull
    private String password;

    @DBRef
    private List<journlEntry> journalEntries=new ArrayList<>();
    private List<String> roles;
}