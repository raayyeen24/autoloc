
package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Vehicule;
import java.util.List;

public interface IVehiculeService {

    public Vehicule ajouterVehicule(Vehicule v);

    public Vehicule updateVehicule(Vehicule v);

    public void deleteVehicule(Long id);

    public Vehicule getVehicule(Long id);

    public List<Vehicule> getAllVehicules();
}
