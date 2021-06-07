package com.bitera.biterausers.controller;

import com.bitera.biterausers.entity.User;
import com.bitera.biterausers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId){
        User user = userService.findById(userId);

        if(user == null) {
            throw new RuntimeException("User id not found -"+userId);
        }
        //retornará al usuario con id pasado en la url
        return user;
    }


    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        user.setId(0);

        userService.save(user);

        return user;

    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {

        userService.save(user);

        return user;
    }


    @DeleteMapping("users/{userId}")
    public String deteteUser(@PathVariable int userId) {

        User user = userService.findById(userId);

        if(user == null) {
            throw new RuntimeException("User id not found -"+userId);
        }

        userService.deleteById(userId);

        return "Deleted user id - "+userId;
    }

}