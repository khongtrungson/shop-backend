package com.khongtrungson.shopapp.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse implements Serializable {
    private String timeStamp;
    private int status;
    private String message;
    private String path;
    private Object errors ;

    public void insertError(String error){
        ((ArrayList<String>)errors).add(error);
    }
}
