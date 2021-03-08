package com.game.polechudes.domain.model.view;

import lombok.Data;


@Data
public class QuestionViewModel {
    private Long id;
    private String name;
    private String hint;
    private String answer;

}
