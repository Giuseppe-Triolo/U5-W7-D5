package service;

import dao.EventDAO;
import entities.event;
import java.sql.SQLException;
import java.util.List;

public class EventService {
    private EventDAO eventDAO;

    public EventService() {
        this.eventDAO = new EventDAO();
    }

    public void createEvent(event event) throws SQLException {
        eventDAO.createEvent(event);
    }

    public List<event> getAllEvents() {
        return eventDAO.getAllEvents();
    }
}
