package com.example.demo.controller;

import com.example.demo.entity.AddQuestionRequest;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Question> getAllQuestions(){
        List<Question> questions = this.questionService.getAllQuestionsSortedByCreationDate();
        return  questions;
    }

    @PostMapping("/addQuestion")
    @ResponseBody
    public Question addQuestion(@RequestBody AddQuestionRequest request){
        return this.questionService.addQuestion(request.getQuestion(), request.getTagNames());
    }

    @DeleteMapping("/deleteById")
    @ResponseBody
    public String deleteQestion(@RequestParam Long questionId, @RequestParam Long userId){
        return this.questionService.deleteQuestion(questionId,userId);
    }

    @PutMapping("/updateQuestion") //remake it!
    @ResponseBody
    public Question updateQuestion(@RequestBody AddQuestionRequest request, @RequestParam Long userId){
        if(request.getQuestion().getAuthor().getUserId().equals(userId)){
            return this.questionService.addQuestion(request.getQuestion(),request.getTagNames());
        }
        else
            return null;
    }

    @GetMapping("/filterByTag")
    @ResponseBody
    public List<Question> filterByTag(@RequestParam String tagName){
        return this.questionService.getQuestionsByTag(tagName);
    }

    @GetMapping("/filterByUser")
    @ResponseBody
    public List<Question> filterByUser(@RequestParam Long userId){
        return this.questionService.getQuestionsByUser(userId);
    }

    @GetMapping("/searchByTitle")
    @ResponseBody
    public List<Question> searchByTitle(@RequestParam String title){
        return this.questionService.getQuestionsByTitle(title);
    }

   /* @GetMapping("/getAllAnswers")
    @ResponseBody
    public List<Answer> getAllAnswersForQuestion(@RequestParam Long questionId){
        return this.questionService.getAllAnswersForQuestion(questionId);
    }

    @PostMapping("/addAnswer")
    @ResponseBody
    public Answer addAnswer(@RequestParam Long questionId, @RequestBody Answer answer){
        return this.questionService.addAnswerToQuestion(questionId,answer);
    }*/
}
