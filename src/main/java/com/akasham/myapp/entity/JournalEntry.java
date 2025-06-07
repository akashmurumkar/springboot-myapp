package com.akasham.myapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {

    // @Document is a MongoDB annotation used to mark a class as a document which is mapped to a collection in the database
    // @Id is a MongoDB annotation used to mark a field as the document's unique identifier

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
