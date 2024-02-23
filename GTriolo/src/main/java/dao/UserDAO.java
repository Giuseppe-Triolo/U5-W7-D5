package dao;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    private static final String INSERT_USER = "INSERT INTO utenti (username, password, ruolo, email, nome, cognome) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM utenti WHERE username = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createUser(User user) {
        jdbcTemplate.update(INSERT_USER,
                user.getUsername(),
                user.getPassword(),
                user.getRuolo(),
                user.getEmail(),
                user.getNome(),
                user.getCognome());
    }

    public User getUserByUsername(String username) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_USERNAME,
                new Object[]{username},
                (rs, rowNum) -> new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("ruolo"),
                        rs.getString("email"),
                        rs.getString("nome"),
                        rs.getString("cognome")
                ));
    }
}
