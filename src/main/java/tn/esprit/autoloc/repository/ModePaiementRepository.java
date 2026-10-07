package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.entities.ModePaiement;

public interface ModePaiementRepository extends JpaRepository<ModePaiement, Long> {
}
