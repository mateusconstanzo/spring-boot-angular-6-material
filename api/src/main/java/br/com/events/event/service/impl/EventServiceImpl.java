package br.com.events.event.service.impl;

import br.com.events.auth.SecurityHolder;
import br.com.events.commons.exception.ServiceException;
import br.com.events.event.bean.Event;
import br.com.events.event.dao.EventDAO;
import br.com.events.event.entity.EventEntity;
import br.com.events.event.exception.EventNotFoundException;
import br.com.events.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventDAO eventDAO;

    @Autowired
    public EventServiceImpl(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    @Transactional
    public Event save(Event event) throws ServiceException {
        try {

            EventEntity entity = eventDAO.save(event.toEntity(SecurityHolder.getUser()));

            return new Event(entity);

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public Event update(Integer id, Event event) throws ServiceException {
        try {

            EventEntity entity = getEventEntity(id);

            entity.update(event);

            eventDAO.save(entity);

            return new Event(entity);

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return eventDAO.findByUser(SecurityHolder.getUser(), new Sort(Sort.Direction.ASC, "startDate"))
                .stream()
                .map(Event::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ServiceException {
        try {

            EventEntity entity = getEventEntity(id);

            eventDAO.delete(entity);

        } catch (EventNotFoundException e) {
            throw e;

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Event findById(Integer id) throws ServiceException {

        EventEntity entity = getEventEntity(id);

        return new Event(entity);
    }

    private EventEntity getEventEntity(Integer id) throws EventNotFoundException {

        Optional<Integer> userId = SecurityHolder.getUserId();

        return eventDAO.findById(id)
                .filter(
                        e -> e.getUser().getId().equals(userId.get())
                )
                .orElseThrow(EventNotFoundException::new);

    }

}
