package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.services.interfaces.IAgenceService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agence")

public class AgenceController {

    private final IAgenceService agenceService;

    @PostMapping ("/ajouter")
    public Agence ajouterAgence(@Valid @RequestBody Agence a) {
        return agenceService.ajouterAgence(a);
    }

    @PutMapping("/modifier")
    public Agence updateAgence(@Valid @RequestBody Agence a) {
        return agenceService.updateAgence(a);
    }
    @DeleteMapping("/supprimer/{id}")
    public void deleteAgence(@PathVariable("id") Long id) {
        agenceService.deleteAgence(id);
    }

    @GetMapping("/get/{id}")
    public Agence getAgence(@PathVariable("id") Long id) {
        return agenceService.getAgence(id);
    }

    @GetMapping("/getAll")
    public List<Agence> getAllAgences() {
        return agenceService.getAllAgences();
    }
}
