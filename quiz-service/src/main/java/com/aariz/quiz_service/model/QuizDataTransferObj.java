package com.aariz.quiz_service.model;

import lombok.Data;

@Data
public class QuizDataTransferObj {
    String categoryName;
    Integer numQuestions;
    String title;
}
