package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.entities.AGENCE;

public interface AgenceRepository extends JpaRepository<AGENCE, Long> {
}
