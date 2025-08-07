package com.springEngineerSubha.jurnalApp.Repository;

import com.springEngineerSubha.jurnalApp.Entry.journlEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalentryRepository extends MongoRepository<journlEntry, ObjectId> {
}
