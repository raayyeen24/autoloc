package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Paiement;
import java.util.List;

public interface IPaiementService {
    Paiement ajouterPaiement(Paiement entity);
    Paiement updatePaiement(Paiement entity);
    void deletePaiement(Long id);
    Paiement getPaiement(Long id);
    List<Paiement> getAllPaiements();
}
