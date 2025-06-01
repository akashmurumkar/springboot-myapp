package com.akasham.myapp.service;

import com.akasham.myapp.entity.JournalEntry;
import com.akasham.myapp.repository.JourneyEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalEntryService {

    // This service class is a placeholder used for dependency injection
    // It will be used to access the repository
    // JournalEntryRepository implementation will be provided by Spring

    @Autowired
    private JourneyEntryRepository journalEntryRepository;


    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

}
