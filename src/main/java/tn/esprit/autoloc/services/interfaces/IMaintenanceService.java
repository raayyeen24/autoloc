package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Maintenance;
import java.util.List;

public interface IMaintenanceService {
    Maintenance ajouterMaintenance(Maintenance entity);
    Maintenance updateMaintenance(Maintenance entity);
    void deleteMaintenance(Long id);
    Maintenance getMaintenance(Long id);
    List<Maintenance> getAllMaintenances();
}
