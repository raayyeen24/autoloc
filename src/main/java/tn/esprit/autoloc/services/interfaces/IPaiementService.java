
package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Paiement;
import java.util.List;

public interface IPaiementService {

    public Paiement ajouterPaiement(Paiement p);

    public Paiement updatePaiement(Paiement p);

    public void deletePaiement(Long id);

    public Paiement getPaiement(Long id);

    public List<Paiement> getAllPaiements();
}
