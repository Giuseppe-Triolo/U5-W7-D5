package dao;

import entities.Prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PrenotazioneDAO {
    private static final String INSERT_RESERVATION = "INSERT INTO prenotazioni (id_utente, id_evento) VALUES (?, ?)";
    private static final String SELECT_RESERVATIONS_BY_USER = "SELECT * FROM prenotazioni WHERE id_utente = ?";
    private static final String SELECT_RESERVATIONS_BY_EVENT = "SELECT * FROM prenotazioni WHERE id_evento = ?";
    private static final String DELETE_RESERVATION = "DELETE FROM prenotazioni WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createReservation(Prenotazione prenotazione) {
        jdbcTemplate.update(INSERT_RESERVATION,
                prenotazione.getIdUtente(),
                prenotazione.getIdEvento());
    }

    public List<Prenotazione> getReservationsByUser(int userId) {
        return jdbcTemplate.query(SELECT_RESERVATIONS_BY_USER,
                new Object[]{userId},
                (rs, rowNum) -> new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("id_utente"),
                        rs.getInt("id_evento")
                ));
    }

    public List<Prenotazione> getReservationsByEvent(int eventId) {
        return jdbcTemplate.query(SELECT_RESERVATIONS_BY_EVENT,
                new Object[]{eventId},
                (rs, rowNum) -> new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("id_utente"),
                        rs.getInt("id_evento")
                ));
    }

    public void deleteReservation(int reservationId) {
        jdbcTemplate.update(DELETE_RESERVATION, reservationId);
    }
}
