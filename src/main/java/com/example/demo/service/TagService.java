package com.example.demo.service;

import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Tag addTag(Tag tag){
        return this.tagRepository.save(tag);
    }

    public List<Tag> showTags(){
        return (List<Tag>) this.tagRepository.findAll();
    }
}
