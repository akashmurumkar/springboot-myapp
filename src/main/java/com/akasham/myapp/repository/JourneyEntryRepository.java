package com.akasham.myapp.repository;

import com.akasham.myapp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JourneyEntryRepository extends MongoRepository<JournalEntry, String> {

    // This Repository is a MongoDB Repository
    // This interface defines the operations that can be performed on the JournalEntry collection


}
