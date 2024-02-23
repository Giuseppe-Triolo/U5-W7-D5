package controller;

import entities.Prenotazione;
import service.PrenotazioneService;
import java.sql.SQLException;
import java.util.List;

public class PrenotazioneController {
    private PrenotazioneService reservationService;

    public PrenotazioneController() {
        this.reservationService = new PrenotazioneService();
    }

    public void createReservation(Prenotazione prenotazione) {
        try {
            reservationService.createReservation(prenotazione);
            System.out.println("Prenotazione effettuata con successo!");
        } catch (SQLException e) {
            System.out.println("Errore durante la creazione della prenotazione: " + e.getMessage());
        }
    }

    public List<Prenotazione> getReservationsByUser(int userId) {
        return reservationService.getReservationsByUser(userId);
    }

    public List<Prenotazione> getReservationsByEvent(int eventId) {
        return reservationService.getReservationsByEvent(eventId);
    }
}
