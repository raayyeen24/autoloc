package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Agence;
import java.util.List;

public interface IAgenceService {
    Agence ajouterAgence(Agence entity);
    Agence updateAgence(Agence entity);
    void deleteAgence(Long id);
    Agence getAgence(Long id);
    List<Agence> getAllAgences();
}
