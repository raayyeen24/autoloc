package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Contrat;
import java.util.List;

public interface IContratService {

    public Contrat ajouterContrat(Contrat c);

    public Contrat updateContrat(Contrat c);

    public void deleteContrat(Long id);

    public Contrat getContrat(Long id);

    public List<Contrat> getAllContrats();
}
