package com.example.demo.service;

import com.example.demo.Exception.GeneralException;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public List<Answer> getAnswersForQuestion(Long questionId){
        return this.answerRepository.findByQuestionQuestionId(questionId);
    }

    public Answer addAnswerToQuestion(Long questionId, Answer answer){
        answer.setCreationDate(new Timestamp(System.currentTimeMillis()));Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            answer.setQuestion(question);
            return answerRepository.save(answer);
        }
        else throw new GeneralException("question not found when adding an answer");
    }

    public Answer editAnswer(Long answerId, Answer updatedAnswer, Long userId){
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        if(optionalAnswer.isPresent()){
            Answer answer = optionalAnswer.get();
            if(!answer.getAuthor().getUserId().equals(userId)){
                throw new GeneralException("not allowed to modify this answer");
            }
            answer.setText(updatedAnswer.getText());
            answer.setImageUrl(updatedAnswer.getImageUrl());
            return this.answerRepository.save(answer);
        }else throw new GeneralException("answer not found when editing an answer");
    }

    public String deleteAnswer(Long answerId, Long userId){
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        if(optionalAnswer.isPresent()) {
            Answer answer = optionalAnswer.get();
            if (answer.getAuthor().getUserId().equals(userId)) {
                this.answerRepository.deleteById(answerId);
                return "AnswerDeleted";
            } else {
                return "not allowed to delete this answer";
            }
        }else {return "Failed to delete entry with id: " + answerId;}

    }

}
