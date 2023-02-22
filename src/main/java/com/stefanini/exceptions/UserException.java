package com.stefanini.exceptions;

public class UserException extends BadRequestException{
    public UserException(){
        super();
    }
    public UserException(String error){
        super(error);
    }
}
