package com.akasham.myapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {

    // @DBRef is used to create a reference to another document
    // Also known as a foreign key in a relational database

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String role;
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
