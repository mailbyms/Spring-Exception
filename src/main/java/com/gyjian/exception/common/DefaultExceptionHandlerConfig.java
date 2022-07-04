package com.gyjian.exception.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


/**
 * 自定义错误处理器
 */

@Controller
@RestControllerAdvice
public class DefaultExceptionHandlerConfig {
    private static Logger logger = LoggerFactory.getLogger(DefaultExceptionHandlerConfig.class);


    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> bindExceptionHandler(BindException e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> constraintViolationExceptionHandler(MethodArgumentNotValidException e){
        e.printStackTrace();
        StringBuilder errName = new StringBuilder();
        if (!StringUtils.isBlank(e.getBindingResult().getFieldErrors().get(0).getField())){
            errName.append(e.getBindingResult().getFieldErrors().get(0).getField());
            errName.append(":");
        }
        errName.append(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errName.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
//        e.printStackTrace();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        e.printStackTrace();
        StringBuilder errName = new StringBuilder();
        if (!StringUtils.isBlank(e.getBindingResult().getFieldErrors().get(0).getField())){
            errName.append(e.getBindingResult().getFieldErrors().get(0).getField());
            errName.append(":");
        }
        errName.append(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errName.toString());
    }

    @ExceptionHandler(MyBindException.class)
    public ResponseEntity<String> unauthorizedExceptionHandler(MyBindException e){
        logger.error("XinliBackendBindException Message :{}",e.getMessage());
        return ResponseEntity.status(e.getHttpStatusCode()).body(e.getMessage());
    }
}
