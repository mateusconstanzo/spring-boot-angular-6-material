package br.com.events;

import br.com.events.commons.exception.ServiceException;
import br.com.events.event.exception.EventNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EventNotFoundException.class)
    public void notfoung() {
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ServiceException.class)
    public void service() {
    }

}
