package com.stefanini.exceptions;

public class UserDoesntExistsException extends UserException{
    public UserDoesntExistsException(){
        super();
    }
    public UserDoesntExistsException(String err){
        super(err);
    }
}
