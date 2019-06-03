package cn.edu.gzmu.advice;

import cn.edu.gzmu.model.exception.ResourceException;
import cn.edu.gzmu.validate.exception.ValidateCodeException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 *
 * @author echo
 * @version 1.0
 * @date 19-5-20 16:39
 */
@RestControllerAdvice
public class SecurityExceptionAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpEntity<?> handle(ValidateCodeException exception){
        return ResponseEntity.badRequest().body(new ResourceException(exception.getMessage()));
    }

}
