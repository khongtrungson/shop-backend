package com.khongtrungson.shopapp.exceptions;


import com.khongtrungson.shopapp.dtos.responses.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalExceptionHandler {
    //requestbody Valid Validated
    //modelatrribute Valid Validated ()
    //pathvariable Validated Valid
    //requestparam Validated Valid


    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List<String> errorList = errors.stream().map(error -> error.getDefaultMessage()).toList();
        return ErrorResponse.builder()
                .timeStamp(new Date().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getServletPath())
                .message("bad request")
                .errors(errorList)
                .build();
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstrainstViolationException(
            ConstraintViolationException ex, HttpServletRequest request) {
        return ErrorResponse.builder()
                .timeStamp(new Date().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getServletPath())
                .errors(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList())
                .message("bad request")
                .build();
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String name = ex.getName();
        String type = ex.getRequiredType().getSimpleName();
        Object value = ex.getValue();
        String message = String.format("'%s' should be a valid '%s' and '%s' isn't",
                name, type, value);
        return ErrorResponse.builder()
                .timeStamp(new Date().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getServletPath())
                .errors(message)
                .message("bad request")
                .build();
    }
//    @ExceptionHandler({DataNotFoundException.class,InvalidParamException.class, IOException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleClientException(
//            Exception ex, HttpServletRequest request) {
//        return ErrorResponse.builder()
//                .timeStamp(new Date().toString())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .path(request.getServletPath())
//                .errors(ex.getMessage())
//                .message("bad request")
//                .build();
//    }
//
//    @ExceptionHandler( {BadCredentialsException.class, UsernameNotFoundException.class})
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ErrorResponse handleUsernameAndPasswordException(
//            AuthenticationException ex, HttpServletRequest request) {
//        return ErrorResponse.builder()
//                .timeStamp(new Date().toString())
//                .status(HttpStatus.UNAUTHORIZED.value())
//                .path(request.getServletPath())
//                .errors(ex.getMessage())
//                .message("phone number or password is incorrect")
//                .build();
//    }
//    @ExceptionHandler(InvalidBearerTokenException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ErrorResponse handleInvalidTokenException(
//            InvalidBearerTokenException ex, HttpServletRequest request) {
//        return ErrorResponse.builder()
//                .timeStamp(new Date().toString())
//                .status(HttpStatus.UNAUTHORIZED.value())
//                .path(request.getServletPath())
//                .errors(ex.getMessage())
//                .message("token is invalid maybe expired,incorrect,...")
//                .build();
//    }
//    @ExceptionHandler(AccountStatusException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ErrorResponse handleAccountStatusException(
//            AuthenticationException ex, HttpServletRequest request) {
//        return ErrorResponse.builder()
//                .timeStamp(new Date().toString())
//                .status(HttpStatus.UNAUTHORIZED.value())
//                .path(request.getServletPath())
//                .errors(ex.getMessage())
//                .message("account status is invalid")
//                .build();
//    }
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ErrorResponse handleAccessDinedTokenException(
//            AccessDeniedException ex, HttpServletRequest request) {
//        return ErrorResponse.builder()
//                .timeStamp(new Date().toString())
//                .status(HttpStatus.FORBIDDEN.value())
//                .path(request.getServletPath())
//                .errors(ex.getMessage())
//                .message("you has no role to access the resource")
//                .build();
//    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalException(
            Exception ex, HttpServletRequest request) {
        return ErrorResponse.builder()
                .timeStamp(new Date().toString())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getServletPath())
                .errors(ex.getMessage())
                .message("internal sever error")
                .build();
    }

}
