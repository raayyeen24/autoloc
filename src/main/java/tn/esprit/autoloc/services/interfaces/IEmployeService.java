package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Employe;
import java.util.List;

public interface IEmployeService {
    Employe ajouterEmploye(Employe entity);
    Employe updateEmploye(Employe entity);
    void deleteEmploye(Long id);
    Employe getEmploye(Long id);
    List<Employe> getAllEmployes();
}
