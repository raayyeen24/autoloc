package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Maintenance;
import tn.esprit.autoloc.services.interfaces.IMaintenanceService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @PostMapping("/ajouter")
    public Maintenance ajouterMaintenance(@Valid @RequestBody Maintenance m) {
        return maintenanceService.ajouterMaintenance(m);
    }

    @PutMapping("/modifier")
    public Maintenance updateMaintenance(@Valid @RequestBody Maintenance m) {
        return maintenanceService.updateMaintenance(m);
    }

    @DeleteMapping("/supprimer/{id}")
    public void deleteMaintenance(@PathVariable("id") Long id) {
        maintenanceService.deleteMaintenance(id);
    }

    @GetMapping("/get/{id}")
    public Maintenance getMaintenance(@PathVariable("id") Long id) {
        return maintenanceService.getMaintenance(id);
    }

    @GetMapping("/getAll")
    public List<Maintenance> getAllMaintenances() {
        return maintenanceService.getAllMaintenances();
    }
}
