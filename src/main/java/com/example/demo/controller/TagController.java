package com.example.demo.controller;

import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.repository.TagRepository;
import com.example.demo.service.TagService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Tag> getAllTags() {
        List<Tag> tags =this.tagService.showTags();
        return tags;
    }

    @PostMapping("/addTag")
    @ResponseBody
    public Tag addTag(@RequestBody Tag tag){
        return this.tagService.addTag(tag);
    }
}
