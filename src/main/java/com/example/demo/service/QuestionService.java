package com.example.demo.service;

import com.example.demo.Exception.GeneralException;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.entity.QuestionTag;
import com.example.demo.entity.Tag;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.QuestionTagRepository;
import com.example.demo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private QuestionTagRepository questionTagRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public List<Question> getAllQuestionsSortedByCreationDate() {
        List<Question> allQuestions = (List<Question>) questionRepository.findAll();
        // Sort the list by creation date in descending order
        return allQuestions.stream()
                .sorted((q1, q2) -> q2.getCreationDate().compareTo(q1.getCreationDate()))
                .collect(Collectors.toList());
    }

    public Question addQuestion(Question question, List<String> tagNames){
        question.setCreationDate(new Timestamp(System.currentTimeMillis()));
        for(String tagName: tagNames){
            Tag tag = tagRepository.findByTagName(tagName);
            if(tag == null){
                tag= new Tag();
                tag.setTagName(tagName);
                tagRepository.save(tag);
            }
            QuestionTag questionTag = new QuestionTag();
            questionTag.setQuestion(question);
            questionTag.setTag(tag);
            question.getQuestionTags().add(questionTag);
        }
        return this.questionRepository.save(question);
    }

    public String deleteQuestion(Long questionId, Long userId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            if (question.getAuthor().getUserId().equals(userId)) {
                questionRepository.deleteById(questionId);
                return "Entry deleted";
            } else {
                return "not allowed to delete this question";
            }
        } else {
            return "Failed to delete entry with id: " + questionId;
        }
    }

    public Question editQuestion(Long questionId, Question updatedQuestion, Long userId){
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            if (!question.getAuthor().getUserId().equals(userId)) {
                throw new GeneralException("not allowed to modify this question");
            }
            question.setQuestionTags(updatedQuestion.getQuestionTags());
            question.setTitle(updatedQuestion.getTitle());
            question.setText(updatedQuestion.getText());
            question.setImageUrl(updatedQuestion.getImageUrl());
            return this.questionRepository.save(question);
        }else
            throw new GeneralException("question not found when editing it");
    }

    public List<Question> getQuestionsByTag(String tagName){
        Tag tag = tagRepository.findByTagName(tagName);
        if (tag != null) {
            List<QuestionTag> questionTags = questionTagRepository.findAllByTag(tag);
            List<Long> questionIds = questionTags.stream()
                    .map(QuestionTag::getQuestion)
                    .map(Question::getQuestionId)
                    .collect(Collectors.toList());
            if(!questionIds.isEmpty()){
                return (List<Question>) questionRepository.findAllById(questionIds);
            }
        }
        return Collections.emptyList();
    }

    public  List<Question> getQuestionsByUser(Long userId){
        return this.questionRepository.findByAuthorUserId(userId);
    }

    public List<Question>getQuestionsByTitle(String title){
        return this.questionRepository.findByTitleContainingIgnoreCase(title);
    }

    /*public List<Answer>getAllAnswersForQuestion(Long questionId){
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            return question.getAnswers();
        }
        else throw new GeneralException("not found");
    }

    public Answer addAnswerToQuestion(Long questionId, Answer answer){
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            answer.setQuestion(question);
            return answerRepository.save(answer);
        }
        else throw new GeneralException("question not found when adding an answer");
    }*/
}
