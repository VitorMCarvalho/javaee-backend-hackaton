package com.stefanini.exceptions;

public class InvalidDataException extends UserException{
    public InvalidDataException(){
        super();
    }
    public InvalidDataException(String err){
        super(err);
    }
}
