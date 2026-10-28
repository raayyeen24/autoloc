package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Reservation;
import tn.esprit.autoloc.services.interfaces.IReservationService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping("/ajouter")
    public Reservation ajouterReservation(@Valid @RequestBody Reservation r) {
        return reservationService.ajouterReservation(r);
    }

    @PutMapping("/modifier")
    public Reservation updateReservation(@Valid @RequestBody Reservation r) {
        return reservationService.updateReservation(r);
    }

    @DeleteMapping("/supprimer/{id}")
    public void deleteReservation(@PathVariable("id") Long id) {
        reservationService.deleteReservation(id);
    }

    @GetMapping("/get/{id}")
    public Reservation getReservation(@PathVariable("id") Long id) {
        return reservationService.getReservation(id);
    }

    @GetMapping("/getAll")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }
}
