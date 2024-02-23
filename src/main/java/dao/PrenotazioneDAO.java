package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Prenotazione;
import util.DatabaseConnection;

public class PrenotazioneDAO {
    private static final String INSERT_RESERVATION = "INSERT INTO prenotazioni (id_utente, id_evento) VALUES (?, ?)";
    private static final String SELECT_RESERVATIONS_BY_USER = "SELECT * FROM prenotazioni WHERE id_utente = ?";
    private static final String SELECT_RESERVATIONS_BY_EVENT = "SELECT * FROM prenotazioni WHERE id_evento = ?";
    private static final String DELETE_RESERVATION = "DELETE FROM prenotazioni WHERE id = ?";

    public void createReservation(Prenotazione prenotazione) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_RESERVATION)) {
            stmt.setInt(1, prenotazione.getIdUtente());
            stmt.setInt(2, prenotazione.getIdEvento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PrenotazioneDAOException("Errore durante la creazione della prenotazione nel database", e);
        }
    }

    public List<Prenotazione> getReservationsByUser(int userId) {
        List<Prenotazione> reservations = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_RESERVATIONS_BY_USER)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Prenotazione prenotazione = new Prenotazione(
                            rs.getInt("id"),
                            rs.getInt("id_utente"),
                            rs.getInt("id_evento")
                    );
                    reservations.add(prenotazione);
                }
            }
        } catch (SQLException e) {
            throw new PrenotazioneDAOException("Errore durante il recupero delle prenotazioni dal db", e);
        }
        return reservations;
    }

    public List<Prenotazione> getReservationsByEvent(int eventId) {
        List<Prenotazione> reservations = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_RESERVATIONS_BY_EVENT)) {
            stmt.setInt(1, eventId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Prenotazione prenotazione = new Prenotazione(
                            rs.getInt("id"),
                            rs.getInt("id_utente"),
                            rs.getInt("id_evento")
                    );
                    reservations.add(prenotazione);
                }
            }
        } catch (SQLException e) {
            throw new PrenotazioneDAOException("Errore nel il recupero delle prenotazioni dell'evento dal db", e);
        }
        return reservations;
    }

    public void deleteReservation(int reservationId) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_RESERVATION)) {
            stmt.setInt(1, reservationId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PrenotazioneDAOException("Errore nel l'eliminazione della prenotazione dal db", e);
        }
    }
}
