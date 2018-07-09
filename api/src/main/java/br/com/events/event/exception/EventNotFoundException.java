package br.com.events.event.exception;

import br.com.events.commons.exception.NotFoundException;

public class EventNotFoundException extends NotFoundException {

    public EventNotFoundException() {
        super("Event Not Found");
    }

}
