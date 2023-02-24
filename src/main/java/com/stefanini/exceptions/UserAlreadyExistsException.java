package com.stefanini.exceptions;

public class UserAlreadyExistsException extends UserException{
    public UserAlreadyExistsException(){
        super();
    }
    public UserAlreadyExistsException(String err){
        super(err);
    }

}
