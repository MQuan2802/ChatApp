package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String userId;

    @Getter
    @Setter
    private UUID uuid;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Date creationDate = new Date();

}
