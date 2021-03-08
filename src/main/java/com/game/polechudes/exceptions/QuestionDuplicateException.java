package com.game.polechudes.exceptions;

public class QuestionDuplicateException  extends RuntimeException{
    public QuestionDuplicateException(){
    }
    public  QuestionDuplicateException(String message){
        super(message);
    }
}
