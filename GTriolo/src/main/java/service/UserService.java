package service;

import dao.UserDAO;
import entities.User;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void registerUser(User user) throws SQLException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPasswordHash(hashedPassword);
        userDAO.createUser(user);
    }

    public User loginUser(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPasswordHash())) {
            return user;
        } else {
            return null;
        }
    }
}
