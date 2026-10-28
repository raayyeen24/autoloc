package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Contrat;
import java.util.List;

public interface IContratService {
    Contrat ajouterContrat(Contrat entity);
    Contrat updateContrat(Contrat entity);
    void deleteContrat(Long id);
    Contrat getContrat(Long id);
    List<Contrat> getAllContrats();
}
