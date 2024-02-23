package service;

import dao.PrenotazioneDAO;
import entities.Prenotazione;
import java.sql.SQLException;
import java.util.List;

public class PrenotazioneService {
    private PrenotazioneDAO reservationDAO;

    public PrenotazioneService() {
        this.reservationDAO = new PrenotazioneDAO();
    }

    public void createReservation(Prenotazione reservation) throws SQLException {
        reservationDAO.createReservation(reservation);
    }

    public List<Prenotazione> getReservationsByUser(int userId) {
        return reservationDAO.getReservationsByUser(userId);
    }

    public List<Prenotazione> getReservationsByEvent(int eventId) {
        return reservationDAO.getReservationsByEvent(eventId);
    }

}
