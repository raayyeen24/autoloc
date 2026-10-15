
package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Reservation;
import java.util.List;

public interface IReservationService {

    public Reservation ajouterReservation(Reservation r);

    public Reservation updateReservation(Reservation r);

    public void deleteReservation(Long id);

    public Reservation getReservation(Long id);

    public List<Reservation> getAllReservations();
}
