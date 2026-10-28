package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Equipement;
import java.util.List;

public interface IEquipementService {
    Equipement ajouterEquipement(Equipement entity);
    Equipement updateEquipement(Equipement entity);
    void deleteEquipement(Long id);
    Equipement getEquipement(Long id);
    List<Equipement> getAllEquipements();
}
