/*
package com.akasham.myapp.controller;

import com.akasham.myapp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    private final Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getJournalEntries() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public JournalEntry createJournalEntry(@RequestBody JournalEntry journalEntry) {
        journalEntries.put(journalEntry.getId(), journalEntry);
        return journalEntry;
    }

    @GetMapping("/{id}")
    public JournalEntry getJournalEntry(@PathVariable Long id) {
        return journalEntries.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteJournalEntry(@PathVariable Long id) {
        journalEntries.remove(id);
    }

    @PutMapping("/{id}")
    public JournalEntry updateJournalEntry(@PathVariable Long id, @RequestBody JournalEntry journalEntry) {
        journalEntry.setId(id);
        journalEntries.put(id, journalEntry);
        return journalEntry;
    }

}
*/
