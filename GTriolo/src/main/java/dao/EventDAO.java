package dao;

import entities.event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EventDAO {
    private static final String INSERT_EVENT = "INSERT INTO eventi (titolo, descrizione, data, luogo, posti_disponibili) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_EVENTS = "SELECT * FROM eventi";
    private static final String SELECT_EVENT_BY_ID = "SELECT * FROM eventi WHERE id = ?";
    private static final String DELETE_EVENT = "DELETE FROM eventi WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createEvent(event event) {
        jdbcTemplate.update(INSERT_EVENT,
                event.getTitolo(),
                event.getDescrizione(),
                event.getData(),
                event.getLuogo(),
                event.getPostiDisponibili());
    }

    public List<event> getAllEvents() {
        return jdbcTemplate.query(SELECT_ALL_EVENTS,
                (rs, rowNum) -> new event(
                        rs.getInt("id"),
                        rs.getString("titolo"),
                        rs.getString("descrizione"),
                        rs.getDate("data"),
                        rs.getString("luogo"),
                        rs.getInt("posti_disponibili")
                ));
    }

    public event getEventById(int eventId) {
        return jdbcTemplate.queryForObject(SELECT_EVENT_BY_ID,
                new Object[]{eventId},
                (rs, rowNum) -> new event(
                        rs.getInt("id"),
                        rs.getString("titolo"),
                        rs.getString("descrizione"),
                        rs.getDate("data"),
                        rs.getString("luogo"),
                        rs.getInt("posti_disponibili")
                ));
    }

    public void deleteEvent(int eventId) {
        jdbcTemplate.update(DELETE_EVENT, eventId);
    }
}
