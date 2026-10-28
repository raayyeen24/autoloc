package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Paiement;
import tn.esprit.autoloc.services.interfaces.IPaiementService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/paiement")
public class PaiementController {

    private final IPaiementService paiementService;

    @PostMapping("/ajouter")
    public Paiement ajouterPaiement(@Valid @RequestBody Paiement p) {
        return paiementService.ajouterPaiement(p);
    }

    @PutMapping("/modifier")
    public Paiement updatePaiement(@Valid @RequestBody Paiement p) {
        return paiementService.updatePaiement(p);
    }

    @DeleteMapping("/supprimer/{id}")
    public void deletePaiement(@PathVariable("id") Long id) {
        paiementService.deletePaiement(id);
    }

    @GetMapping("/get/{id}")
    public Paiement getPaiement(@PathVariable("id") Long id) {
        return paiementService.getPaiement(id);
    }

    @GetMapping("/getAll")
    public List<Paiement> getAllPaiements() {
        return paiementService.getAllPaiements();
    }
}
