package com.akasham.myapp.controller;

import com.akasham.myapp.entity.JournalEntry;
import com.akasham.myapp.entity.User;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<?>> getAll() {
        List<User> users = userService.getAll();
        if (users != null && !users.isEmpty())
            return new ResponseEntity<>(users, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            user.setCreatedDate(LocalDateTime.now());
            user.setUpdatedDate(LocalDateTime.now());
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable ObjectId id) {
        Optional<User> users = userService.findById(id);
        if (users.isPresent()) {
            return new ResponseEntity<>(users.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId id) {
        Optional<User> users = userService.findById(id);
        if (users.isPresent()) {
            userService.deleteById(id);
            return new ResponseEntity<>(users.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateJournalEntry(@RequestBody User newUser, @PathVariable String username) {
        User userInDB = userService.findByUsername(username);
        if (userInDB != null) {
            userInDB.setUsername(newUser.getUsername());
            userInDB.setPassword(newUser.getPassword());
            userInDB.setRole(newUser.getRole());
            userInDB.setUpdatedDate(LocalDateTime.now());
            userService.saveUser(userInDB);
            return new ResponseEntity<>(userInDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
