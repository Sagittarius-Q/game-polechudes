package com.game.polechudes.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Question Not Found")
public class QuestionNotFoundException  extends RuntimeException{
    public QuestionNotFoundException(){
    }
    public QuestionNotFoundException(String message){
        super(message);
    }
}
