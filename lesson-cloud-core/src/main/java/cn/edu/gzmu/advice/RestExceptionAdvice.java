package cn.edu.gzmu.advice;

import com.alibaba.fastjson.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author echo
 * @version 1.0
 * @date 19-5-8 14:51
 */
@RestControllerAdvice
public class RestExceptionAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpEntity<?> handle(ConstraintViolationException exception){
        JSONArray errors = new JSONArray();
        exception.getConstraintViolations().forEach(item -> {
            errors.add(item.getMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
