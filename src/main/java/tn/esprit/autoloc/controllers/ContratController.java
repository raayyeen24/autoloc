
package tn.esprit.autoloc.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Contrat;
import tn.esprit.autoloc.services.interfaces.IContratService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/contrat")
public class ContratController {

    IContratService contratService;

    @PostMapping("/ajouter")
    public Contrat ajouterContrat(@RequestBody Contrat c) {
        return contratService.ajouterContrat(c);
    }

    @PutMapping("/modifier")
    public Contrat updateContrat(@RequestBody Contrat c) {
        return contratService.updateContrat(c);
    }

    @DeleteMapping("/supprimer/{id}")
    public void deleteContrat(@PathVariable Long id) {
        contratService.deleteContrat(id);
    }

    @GetMapping("/get/{id}")
    public Contrat getContrat(@PathVariable Long id) {
        return contratService.getContrat(id);
    }

    @GetMapping("/getAll")
    public List<Contrat> getAllContrats() {
        return contratService.getAllContrats();
    }
}
