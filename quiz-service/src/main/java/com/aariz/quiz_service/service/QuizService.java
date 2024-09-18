package com.aariz.quiz_service.service;



import com.aariz.quiz_service.dao.QuizDao;
import com.aariz.quiz_service.feign.QuizInterface;
import com.aariz.quiz_service.model.Question;
import com.aariz.quiz_service.model.QuestionWrapper;
import com.aariz.quiz_service.model.Quiz;
import com.aariz.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
//    @Autowired
//    QuestionDao questionDao;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

//        List<Integer> questions = // call the generate url - RestTemplate
//
//        Quiz quiz = new Quiz();
//        quiz.setTitle(title);
//        quiz.setQuestions(questions);
//        quizDao.save(quiz);

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id){
//        Optional<Quiz> quiz = quizDao.findById(id);
//        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
//
//        for (Question q : questionsFromDB){
//            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion_title(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
//            questionsForUser.add(qw);
//        }

        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        quizInterface.getQuestionsFromId(questionIds);
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);



//        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
//        Quiz quiz = quizDao.findById(id).get();
////        List<Question> questions = quiz.getQuestions();
//        int right = 0;
////        int i = 0;
////        for(Response response : responses){
////            if(response.getResponse().equals(questions.get(i).getRight_answer())) {
////                right++;
////            }
////            i++;
////        }
        ResponseEntity<Integer> score = quizInterface.getScore(responses);

//        return new ResponseEntity<>(right, HttpStatus.OK);
        return score;
    }
}
