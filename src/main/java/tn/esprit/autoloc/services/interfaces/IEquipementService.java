
package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Equipement;
import java.util.List;

public interface IEquipementService {

    public Equipement ajouterEquipement(Equipement e);

    public Equipement updateEquipement(Equipement e);

    public void deleteEquipement(Long id);

    public Equipement getEquipement(Long id);

    public List<Equipement> getAllEquipements();
}
