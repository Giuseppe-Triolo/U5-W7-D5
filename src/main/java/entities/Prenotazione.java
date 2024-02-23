package entities;

public class Prenotazione {
    private int id;
    private int idUtente;
    private int idEvento;

    public Prenotazione(int id, int idUtente, int idEvento) {
        this.id = id;
        this.idUtente = idUtente;
        this.idEvento = idEvento;
    }

    public int getId() {
        return id;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
}
