package org.app.validation.customExceptions;

public class InvalidNumberException extends NumberFormatException {
    public InvalidNumberException(String msg){
        super(msg);
    }
}
