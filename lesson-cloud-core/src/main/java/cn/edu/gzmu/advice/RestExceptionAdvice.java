package cn.edu.gzmu.advice;

import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.model.exception.UserNotFoundException;
import cn.edu.gzmu.model.exception.ResourceException;
import com.alibaba.fastjson.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * @author echo
 * @version 1.0
 * @date 19-5-8 14:51
 */
@RestControllerAdvice
public class RestExceptionAdvice {

    @ExceptionHandler
    public HttpEntity<?> handle(ConstraintViolationException exception) {
        JSONArray errors = new JSONArray();
        exception.getConstraintViolations().forEach(item -> errors.add(item.getMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({IllegalArgumentException.class, ValidationException.class})
    public HttpEntity<?> handle(Exception exception) {
        return ResponseEntity.badRequest().body(new ResourceException(exception.getMessage()));
    }

    @ExceptionHandler({ResourceNotFoundException.class, UserNotFoundException.class})
    public HttpEntity<?> resourceNotFound(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResourceException(exception.getMessage()));
    }
}
