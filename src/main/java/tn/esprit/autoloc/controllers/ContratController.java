package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Contrat;
import tn.esprit.autoloc.services.interfaces.IContratService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contrat")
public class ContratController {

    private final IContratService contratService;

    @PostMapping("/ajouter")
    public Contrat ajouterContrat(@Valid @RequestBody Contrat c) {
        return contratService.ajouterContrat(c);
    }

    @PutMapping("/modifier")
    public Contrat updateContrat(@Valid @RequestBody Contrat c) {
        return contratService.updateContrat(c);
    }

    @DeleteMapping("/supprimer/{id}")
    public void deleteContrat(@PathVariable("id") Long id) {
        contratService.deleteContrat(id);
    }

    @GetMapping("/get/{id}")
    public Contrat getContrat(@PathVariable("id") Long id) {
        return contratService.getContrat(id);
    }

    @GetMapping("/getAll")
    public List<Contrat> getAllContrats() {
        return contratService.getAllContrats();
    }
}
