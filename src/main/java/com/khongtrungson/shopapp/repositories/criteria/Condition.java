package com.khongtrungson.shopapp.repositories.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Condition {
    private String key;//firstName , lastName, ...
    private String operation;// :,<,>,~
    private String value;//.


}
