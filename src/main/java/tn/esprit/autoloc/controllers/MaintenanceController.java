
package tn.esprit.autoloc.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Maintenance;
import tn.esprit.autoloc.services.interfaces.IMaintenanceService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

    IMaintenanceService maintenanceService;

    @PostMapping("/ajouter")
    public Maintenance ajouterMaintenance(@RequestBody Maintenance m) {
        return maintenanceService.ajouterMaintenance(m);
    }

    @PutMapping("/modifier")
    public Maintenance updateMaintenance(@RequestBody Maintenance m) {
        return maintenanceService.updateMaintenance(m);
    }

    @DeleteMapping("/supprimer/{id}")
    public void deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
    }

    @GetMapping("/get/{id}")
    public Maintenance getMaintenance(@PathVariable Long id) {
        return maintenanceService.getMaintenance(id);
    }

    @GetMapping("/getAll")
    public List<Maintenance> getAllMaintenances() {
        return maintenanceService.getAllMaintenances();
    }
}
