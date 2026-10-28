package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Equipement;
import tn.esprit.autoloc.services.interfaces.IEquipementService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/equipement")
public class EquipementController {

    private final IEquipementService equipementService;

    @PostMapping("/ajouter")
    public Equipement ajouterEquipement(@Valid @RequestBody Equipement e) {
        return equipementService.ajouterEquipement(e);
    }

    @PutMapping("/modifier")
    public Equipement updateEquipement(@Valid @RequestBody Equipement e) {
        return equipementService.updateEquipement(e);
    }

    @DeleteMapping("/supprimer/{id}")
    public void deleteEquipement(@PathVariable("id") Long id) {
        equipementService.deleteEquipement(id);
    }

    @GetMapping("/get/{id}")
    public Equipement getEquipement(@PathVariable("id") Long id) {
        return equipementService.getEquipement(id);
    }

    @GetMapping("/getAll")
    public List<Equipement> getAllEquipements() {
        return equipementService.getAllEquipements();
    }
}
