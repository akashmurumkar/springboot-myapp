package com.akasham.myapp.controller;

import com.akasham.myapp.entity.JournalEntry;
import com.akasham.myapp.entity.User;
import com.akasham.myapp.service.JournalEntryService;
import com.akasham.myapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    // Best Practices:
    // Controller layer to call service layer
    // Service layer to call repository layer
    // Repository layer to call database

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<?>> getJournalEntries() {
        List<JournalEntry> journalEntries = journalEntryService.getAll();
        if (journalEntries != null && !journalEntries.isEmpty())
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{username}")
    public ResponseEntity<List<?>> getJournalEntriesOfUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if (journalEntries != null && !journalEntries.isEmpty())
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{username}")
    public ResponseEntity<?> createJournalEntryForUser(@RequestBody JournalEntry journalEntry, @PathVariable String username) {
        try {
            journalEntryService.saveEntry(journalEntry, username);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getJournalEntry(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{username}/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id, @PathVariable String username) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
        if (journalEntry.isPresent()) {
            journalEntryService.deleteById(id, username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{username}/{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry, @PathVariable String username) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
        if (journalEntry.isPresent()) {
            try {
                newEntry.setId(id);
                JournalEntry journalEntryToUpdate = journalEntry.get();
                journalEntryToUpdate.setTitle(!newEntry.getTitle().isEmpty() ? newEntry.getTitle() : journalEntryToUpdate.getTitle());
                journalEntryToUpdate.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : journalEntryToUpdate.getContent());
                journalEntryService.saveEntry(journalEntryToUpdate);
                return new ResponseEntity<>(journalEntryToUpdate, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
