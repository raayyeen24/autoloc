package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.entities.Agence;

public interface AgenceRepository extends JpaRepository<Agence, Long> {
}
