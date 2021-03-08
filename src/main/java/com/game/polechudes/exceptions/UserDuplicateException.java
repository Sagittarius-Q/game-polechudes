package com.game.polechudes.exceptions;

public class UserDuplicateException extends RuntimeException{
    public UserDuplicateException(){

    }
    public UserDuplicateException(String message){
        super(message);
    }
}
