package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return this.userRepository.save(user);
    }

    public List<User> showUsers(){
        return (List<User>) this.userRepository.findAll();
    }

    public String deleteTeacherById(Long id){
        try {
            this.userRepository.deleteById(id);
            return "Entry deleted";
        }
        catch (Exception e){
            return "failed to delete entry: " + id;
        }
    }
}
