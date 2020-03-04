package org.university.stm.start.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.university.stm.start.common.message.ErrorMessage;
import org.university.stm.start.common.message.UnAuthMessage;

import javax.security.auth.message.AuthException;
import java.io.IOException;


/**
 * <pre>
 *     global exception to message
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-03-04
 */
@ControllerAdvice(value = "org.university.stm.start")
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public ErrorMessage clusterHardExceptionHandler(IOException e){
        return new ErrorMessage(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthException.class)
    public UnAuthMessage clusterHardExceptionHandler(AuthException e){
        return new UnAuthMessage(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ErrorMessage exceptionHandler(Exception e){
        return new ErrorMessage(e.getMessage());
    }
}

