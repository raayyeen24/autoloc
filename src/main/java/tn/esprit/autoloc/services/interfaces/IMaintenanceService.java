
package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Maintenance;
import java.util.List;

public interface IMaintenanceService {

    public Maintenance ajouterMaintenance(Maintenance m);

    public Maintenance updateMaintenance(Maintenance m);

    public void deleteMaintenance(Long id);

    public Maintenance getMaintenance(Long id);

    public List<Maintenance> getAllMaintenances();
}
