package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
