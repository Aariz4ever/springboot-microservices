package com.aariz.quiz_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Question {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //for automatic id generation
    private Integer id;
    private String category;
    private String question_title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String right_answer;
    private String difficulty_level;
}
