package com.example.demo.controller;

import com.example.demo.entity.Answer;
import com.example.demo.service.AnswerService;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    

    @GetMapping("/GetAllForQuestion")
    @ResponseBody
    public List<Answer> getAllAnswers(@RequestParam Long questionId) {
        return this.answerService.getAnswersForQuestion(questionId);
    }

    @PostMapping("/addAnswer")
    @ResponseBody
    public Answer addAnswerToQuestion(@RequestParam Long questionId, @RequestBody Answer answer) {
        return this.answerService.addAnswerToQuestion(questionId, answer);
    }


    @DeleteMapping("/deleteAnswer")
    @ResponseBody
    public String deleteAnswer(@RequestParam Long answerId, @RequestParam Long userId) {
        return this.answerService.deleteAnswer(answerId, userId);
    }

    @PutMapping("/updateAnswer")
    @ResponseBody
    public Answer updateAnswer(@RequestParam Long answerId, @RequestBody Answer updatedAnswer, @RequestParam Long userId){
        return this.answerService.editAnswer(answerId,updatedAnswer,userId);
    }
}
