package com.akasham.myapp.service;

import com.akasham.myapp.entity.JournalEntry;
import com.akasham.myapp.entity.User;
import com.akasham.myapp.repository.JourneyEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    // This service class is a placeholder used for dependency injection
    // It will be used to access the repository
    // JournalEntryRepository implementation will be provided by Spring

    @Autowired
    private JourneyEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String username) {
        User user = userService.findByUsername(username);

        journalEntry.setCreatedDate(LocalDateTime.now());
        journalEntry.setUpdatedDate(LocalDateTime.now());
        JournalEntry savedEntry = journalEntryRepository.save(journalEntry);

        user.getJournalEntries().add(savedEntry);
        userService.saveUser(user);
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntry.setUpdatedDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String username) {
        User user = userService.findByUsername(username);
        user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
    }

}
