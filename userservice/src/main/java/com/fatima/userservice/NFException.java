package com.fatima.userservice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NFException extends RuntimeException {
   public NFException(Class<?> clazz) {
        super("Resource of type " + clazz.getSimpleName() + " not found");
    }
    public NFException(String message){
        super(message);
    }
}
