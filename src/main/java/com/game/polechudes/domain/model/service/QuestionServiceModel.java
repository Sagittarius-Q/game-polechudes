package com.game.polechudes.domain.model.service;

import lombok.Data;

@Data
public class QuestionServiceModel {
    private Long id;
    private String name;
    private String hint;
    private String answer;
}
