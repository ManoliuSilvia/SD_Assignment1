package com.example.demo.controller;

import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Autowired
    UserController controller;

    @Test
    void getAllUsers() {
        List<User> users = controller.getAllUsers();
        for (User user: users){
            System.out.println(user);
        }
    }

    @Test
    void addUser() {
        User user1=new User("Maria","12345");
        User user2=new User("Petrica","farafrica");
        User user3=new User("Marcel","Macaragiul");
        controller.addUser(user1);
        controller.addUser(user2);
        controller.addUser(user3);
        assertEquals("Maria",user1.getUsername());
        assertEquals(2,user2.getUserId());
        assertEquals("Macaragiul",user3.getPassword());
    }
}