package com.khongtrungson.shopapp.dtos.validators;

import ch.qos.logback.core.util.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch,Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        field = constraintAnnotation.field();
        fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        String fieldValue = (String) new BeanWrapperImpl(value).getPropertyValue(field);
        String fieldMatchValue = (String) new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        if(StringUtil.notNullNorEmpty(fieldValue)){
            if(fieldValue.equalsIgnoreCase(fieldMatchValue)){
                return true;
            }
        }
        return false;
    }
}
