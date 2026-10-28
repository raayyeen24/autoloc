package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.services.interfaces.IVehiculeService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vehicule")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @PostMapping("/ajouter")
    public Vehicule ajouterVehicule(@Valid @RequestBody Vehicule v) {
        return vehiculeService.ajouterVehicule(v);
    }

    @PutMapping("/modifier")
    public Vehicule updateVehicule(@Valid @RequestBody Vehicule v) {
        return vehiculeService.updateVehicule(v);
    }

    @DeleteMapping("/supprimer/{id}")
    public void deleteVehicule(@PathVariable("id") Long id) {
        vehiculeService.deleteVehicule(id);
    }

    @GetMapping("/get/{id}")
    public Vehicule getVehicule(@PathVariable("id") Long id) {
        return vehiculeService.getVehicule(id);
    }

    @GetMapping("/getAll")
    public List<Vehicule> getAllVehicules() {
        return vehiculeService.getAllVehicules();
    }
}
