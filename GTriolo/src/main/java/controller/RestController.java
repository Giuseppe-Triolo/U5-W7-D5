package controller;

import entities.User;
import entities.event;
import entities.Prenotazione;
import controller.UserController;
import controller.EventController;
import controller.PrenotazioneController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private UserController userController;

    @Autowired
    private EventController eventController;

    @Autowired
    private PrenotazioneController prenotazioneController;

    @PostMapping("/users/register")
    public void registerUser(@RequestBody User user) {
        userController.registerUser(user);
    }

    @PostMapping("/users/login")
    public User loginUser(@RequestParam String username, @RequestParam String password) {
        return userController.loginUser(username, password);
    }

    @PostMapping("/events/create")
    public void createEvent(@RequestBody event event) {
        eventController.createEvent(event);
    }

    @GetMapping("/events")
    public List<event> getAllEvents() {
        return eventController.getAllEvents();
    }



    @PostMapping("/reservations/create")
    public void createReservation(@RequestBody Prenotazione reservation) {
        prenotazioneController.createReservation(reservation);
    }

    @GetMapping("/reservations/user/{userId}")
    public List<Prenotazione> getReservationsByUser(@PathVariable int userId) {
        return prenotazioneController.getReservationsByUser(userId);
    }

    @GetMapping("/reservations/event/{eventId}")
    public List<Prenotazione> getReservationsByEvent(@PathVariable int eventId) {
        return prenotazioneController.getReservationsByEvent(eventId);
    }


