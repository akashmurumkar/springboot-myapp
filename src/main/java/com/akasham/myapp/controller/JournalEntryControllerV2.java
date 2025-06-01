package com.akasham.myapp.controller;

import com.akasham.myapp.entity.JournalEntry;
import com.akasham.myapp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    // Best Practices:
    // Controller layer to call service layer
    // Service layer to call repository layer
    // Repository layer to call database

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getJournalEntries() {
        return journalEntryService.getAllEntries();
    }

    @PostMapping
    public JournalEntry createJournalEntry(@RequestBody JournalEntry journalEntry) {
        journalEntryService.saveEntry(journalEntry);
        return journalEntry;
    }

    @GetMapping("/{id}")
    public JournalEntry getJournalEntry(@PathVariable Long id) {

        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteJournalEntry(@PathVariable Long id) {

    }

    @PutMapping("/{id}")
    public JournalEntry updateJournalEntry(@PathVariable Long id, @RequestBody JournalEntry journalEntry) {

        return journalEntry;
    }

}
