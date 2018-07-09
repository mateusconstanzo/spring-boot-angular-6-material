package br.com.events.event.service;

import br.com.events.event.bean.Event;
import br.com.events.commons.exception.ServiceException;

import java.util.List;

public interface EventService {

    Event save(Event event) throws ServiceException;

    Event update(Integer id, Event event) throws ServiceException;

    List<Event> findAll();

    void delete(Integer eventId) throws ServiceException;

    Event findById(Integer id) throws ServiceException;

}
