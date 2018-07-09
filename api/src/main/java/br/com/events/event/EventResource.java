package br.com.events.event;

import br.com.events.commons.exception.ServiceException;
import br.com.events.event.bean.Event;
import br.com.events.event.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Serviço de Eventos")
@RestController
@RequestMapping(value = "/events")
@CrossOrigin
public class EventResource {

    private final EventService eventService;

    @Autowired
    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    @ApiOperation(value = "Recupera todos os Eventos")
    @GetMapping()
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @ApiOperation(value = "Criar evento")
    @PostMapping()
    public Event save(@RequestBody Event event) throws ServiceException {
        return eventService.save(event);
    }

    @ApiOperation(value = "Atualiza  evento")
    @PutMapping(value = "/{id}")
    public Event update(@PathVariable Integer id, @RequestBody Event event) throws ServiceException {
        return eventService.update(id, event);
    }

    @ApiOperation(value = "Atualiza  evento")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) throws ServiceException {
        eventService.delete(id);
    }

    @ApiOperation(value = "Recupera evento")
    @GetMapping(value = "/{id}")
    public Event getById(@PathVariable Integer id) throws ServiceException {
        return eventService.findById(id);
    }


}
