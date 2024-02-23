package controller;

import entities.event;
import service.EventService;
import java.sql.SQLException;
import java.util.List;

public class EventController {
    private EventService eventService;

    public EventController() {
        this.eventService = new EventService();
    }

    public void createEvent(event event) {
        try {
            eventService.createEvent(event);
            System.out.println("Evento creato con successo!");
        } catch (SQLException e) {
            System.out.println("Errore durante la creazione dell'evento: " + e.getMessage());
        }
    }

    public List<event> getAllEvents() {
        return eventService.getAllEvents();
    }
}
