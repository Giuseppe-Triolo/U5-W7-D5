package entities;

public class User {
    private int id;
    private String username;
    private String password;
    private String ruolo;
    private String email;
    private String nome;
    private String cognome;

    public User(int id, String username, String password, String ruolo, String email, String nome, String cognome) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getRuolo() {
        return ruolo;
    }
    public String getEmail() {
        return email;
    }
    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }


}
