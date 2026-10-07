package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
