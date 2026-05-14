package com.springEngineerSubha.jurnalApp.Repository;

import com.springEngineerSubha.jurnalApp.Entry.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;





public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username); // user return

    void deleteByUsername(String username);
}// give pojo class User in Entity package as a paramether

