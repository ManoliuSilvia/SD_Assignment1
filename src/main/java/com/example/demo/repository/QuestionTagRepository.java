package com.example.demo.repository;

import com.example.demo.entity.QuestionTag;
import com.example.demo.entity.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionTagRepository extends CrudRepository<QuestionTag,Long> {
    List<QuestionTag> findAllByTag(Tag tag);
}
