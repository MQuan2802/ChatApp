package com.example.demo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface MongoDemoRepository extends MongoRepository<User,String> {


}