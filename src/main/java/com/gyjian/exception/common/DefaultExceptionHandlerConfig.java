package com.gyjian.exception.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;


/**
 * 自定义错误处理器
 */

@Slf4j
@Controller
@RestControllerAdvice
public class DefaultExceptionHandlerConfig {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> bindExceptionHandler(BindException e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> constraintViolationExceptionHandler(ConstraintViolationException e) {
        e.printStackTrace();
        StringBuilder errName = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            errName.append(violation.getMessage()).append(";");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errName.toString());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        e.printStackTrace();
        StringBuilder errName = new StringBuilder();
        if (!StringUtils.isBlank(e.getBindingResult().getFieldErrors().get(0).getField())) {
            errName.append(e.getBindingResult().getFieldErrors().get(0).getField());
            errName.append(":");
        }
        errName.append(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errName.toString());
    }

    @ExceptionHandler(MyBindException.class)
    public ResponseEntity<String> unauthorizedExceptionHandler(MyBindException e){
        log.error("XinliBackendBindException Message :{}",e.getMessage());
        return ResponseEntity.status(e.getHttpStatusCode()).body(e.getMessage());
    }
}
