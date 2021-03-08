package com.game.polechudes.domain.model.binding;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class QuestionBindingModel {

    private Long id;
    @Size(min = 1, max = 15, message = "Name must be between 1 and 15 characters")
    private String name;

    @Size(min = 4, max = 30, message = "Content must be between 4 and 30 characters")
    private String hint;

    @Size(min = 3, max = 15, message = "Answer must be between 3 and 15 characters")
    private String answer;
}
