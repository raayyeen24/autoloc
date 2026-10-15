
package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Employe;
import java.util.List;

public interface IEmployeService {

    public Employe ajouterEmploye(Employe e);

    public Employe updateEmploye(Employe e);

    public void deleteEmploye(Long id);

    public Employe getEmploye(Long id);

    public List<Employe> getAllEmployes();
}
