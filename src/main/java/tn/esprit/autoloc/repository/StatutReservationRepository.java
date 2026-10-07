package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.entities.StatutReservation;

public interface StatutReservationRepository extends JpaRepository<StatutReservation, Long> {
}
