package org.example.questionservice.controller;


import org.example.questionservice.model.Question;
import org.example.questionservice.model.QuestionWrapper;
import org.example.questionservice.model.Response;
import org.example.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody List<Question> question){
        return  questionService.addQuestion(question);
    }
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam int numQ){
        return questionService.generateQuestions(category,numQ);
    }
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>>getQuestions(@RequestBody List<Integer> ids){
        return questionService.getQuestions(ids);
    }
    @PostMapping("calculateScore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses){
        return questionService.calculateScore(responses);
    }



}
