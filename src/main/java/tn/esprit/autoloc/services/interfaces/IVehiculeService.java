package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Vehicule;
import java.util.List;

public interface IVehiculeService {
    Vehicule ajouterVehicule(Vehicule entity);
    Vehicule updateVehicule(Vehicule entity);
    void deleteVehicule(Long id);
    Vehicule getVehicule(Long id);
    List<Vehicule> getAllVehicules();
}
