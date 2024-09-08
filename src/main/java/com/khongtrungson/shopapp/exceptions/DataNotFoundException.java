package com.khongtrungson.shopapp.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String message){
        super(message);
    }
}
