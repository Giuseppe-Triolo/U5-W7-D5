package controller;

import entities.User;
import service.UserService;
import java.sql.SQLException;

public class UserController {
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public void registerUser(User user) {
        try {
            userService.registerUser(user);
            System.out.println("Utente registrato con successo!");
        } catch (SQLException e) {
            System.out.println("Errore durante la registrazione dell'utente: " + e.getMessage());
        }
    }

    public User loginUser(String username, String password) {
        return userService.loginUser(username, password);
    }
}
