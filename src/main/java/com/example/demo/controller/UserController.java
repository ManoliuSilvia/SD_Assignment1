package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<User> getAllUsers() {
        List<User> users =this.userService.showUsers();
        return users;
    }

    @PostMapping("/addUser")
    @ResponseBody
    public User addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }

    @DeleteMapping("/deleteById")
    @ResponseBody
    public String deleteTeacherById(@RequestParam Long id){
        return this.userService.deleteTeacherById(id);
    }

    @PutMapping("/updateUser")
    @ResponseBody
    public User updateUser(@RequestBody User user){
        return this.userService.addUser(user);
    }
}
