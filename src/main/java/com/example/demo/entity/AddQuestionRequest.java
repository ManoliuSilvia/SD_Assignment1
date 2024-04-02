package com.example.demo.entity;

import java.util.List;

public class AddQuestionRequest {
    private Question question;
    private List<String> tagNames;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }
}
