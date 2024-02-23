package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.event;
import util.DatabaseConnection;

public class EventDAO {
    private static final String INSERT_EVENT = "INSERT INTO eventi (titolo, descrizione, data, luogo, posti_disponibili) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_EVENTS = "SELECT * FROM eventi";
    private static final String SELECT_EVENT_BY_ID = "SELECT * FROM eventi WHERE id = ?";
    private static final String DELETE_EVENT = "DELETE FROM eventi WHERE id = ?";

    public void createEvent(event event) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_EVENT)) {
            stmt.setString(1, event.getTitolo());
            stmt.setString(2, event.getDescrizione());
            stmt.setDate(3, event.getData());
            stmt.setString(4, event.getLuogo());
            stmt.setInt(5, event.getPostiDisponibili());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new EventDAOException("Errore durante l'inserimento dell'evento nel db", e);
        }
    }

    public List<event> getAllEvents() {
        List<event> events = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_EVENTS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                event event = new event(
                        rs.getInt("id"),
                        rs.getString("titolo"),
                        rs.getString("descrizione"),
                        rs.getDate("data"),
                        rs.getString("luogo"),
                        rs.getInt("posti_disponibili")
                );
                events.add(event);
            }
        } catch (SQLException e) {
            throw new EventDAOException("Errore durante il recupero degli eventi dal db", e);
        }
        return events;
    }

    public event getEventById(int eventId) {
        event event = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_EVENT_BY_ID)) {
            stmt.setInt(1, eventId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    event = new event(
                            rs.getInt("id"),
                            rs.getString("titolo"),
                            rs.getString("descrizione"),
                            rs.getDate("data"),
                            rs.getString("luogo"),
                            rs.getInt("posti_disponibili")
                    );
                }
            }
        } catch (SQLException e) {
            throw new EventDAOException("Errore durante il recupero dell'evento dal db", e);
        }
        return event;
    }

    public void deleteEvent(int eventId) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_EVENT)) {
            stmt.setInt(1, eventId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new EventDAOException("Errore durante l'eliminazione dell'evento dal db", e);
        }
    }
}
