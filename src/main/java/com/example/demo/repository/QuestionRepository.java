package com.example.demo.repository;

import com.example.demo.entity.Question;
import com.example.demo.entity.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question,Long> {
    List<Question> findByAuthorUserId(Long userId);
    List<Question> findByTitleContainingIgnoreCase(String title);
    List<Question> findByQuestionTagsContaining(Tag tag);
}
