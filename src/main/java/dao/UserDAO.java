package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entities.User;
import util.DatabaseConnection;

public class UserDAO {
    private static final String INSERT_USER = "INSERT INTO utenti (username, password, ruolo, email, nome, cognome) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM utenti WHERE username = ?";

    public void createUser(User user) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_USER)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRuolo());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getNome());
            stmt.setString(6, user.getCognome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UserDAOException("Errore durante l'inserimento dell'utente nel database", e);
        }
    }

    public User getUserByUsername(String username) {
        User user = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_USER_BY_USERNAME)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("ruolo"),
                            rs.getString("email"),
                            rs.getString("nome"),
                            rs.getString("cognome")
                    );
                }
            }
        } catch (SQLException e) {
            throw new UserDAOException("Errore durante il recupero dell'utente dal database", e);
        }
        return user;
    }
}
