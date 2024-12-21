package org.example.questionservice.service;

import org.example.questionservice.dao.QuestionDao;
import org.example.questionservice.model.Question;
import org.example.questionservice.model.QuestionWrapper;
import org.example.questionservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(List<Question> question) {
        questionDao.saveAll(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Integer>> generateQuestions(String category, int numQ) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(List<Integer> ids) {
        List<QuestionWrapper> questions = new ArrayList<>();
        for (Integer id:ids){
            Question question = questionDao.findById(id).get();
            questions.add(new QuestionWrapper(question.getId(),question.getQuestionTitle(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4()));
        }
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<Response> responses) {
        int score = 0;
        for (Response response:responses){
            Question question = questionDao.findById(response.getId()).get();
            if (question.getRightAnswer().equals(response.getResponse())){
                score++;
            }
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
    //generate questions for quiz
    //get Questions by iD
    //get Score



}
