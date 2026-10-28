package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Reservation;
import java.util.List;

public interface IReservationService {
    Reservation ajouterReservation(Reservation entity);
    Reservation updateReservation(Reservation entity);
    void deleteReservation(Long id);
    Reservation getReservation(Long id);
    List<Reservation> getAllReservations();
}
