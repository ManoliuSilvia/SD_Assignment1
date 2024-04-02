package com.example.demo.controller;

import com.example.demo.entity.Question;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionControllerTest {

    @Autowired
    QuestionController controller;

    @Test
    void getAllQuestions() {
        List<Question> questions = controller.getAllQuestions();
        for (Question question: questions){
            System.out.println(question);
        }
    }

    @Test
    void addQuestion() {
        Question q1 = new Question();
    }
}