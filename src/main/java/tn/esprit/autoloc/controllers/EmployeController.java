
package tn.esprit.autoloc.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Employe;
import tn.esprit.autoloc.services.interfaces.IEmployeService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/employe")
public class EmployeController {

    IEmployeService employeService;

    @PostMapping("/ajouter")
    public Employe ajouterEmploye(@RequestBody Employe e) {
        return employeService.ajouterEmploye(e);
    }

    @PutMapping("/modifier")
    public Employe updateEmploye(@RequestBody Employe e) {
        return employeService.updateEmploye(e);
    }

    @DeleteMapping("/supprimer/{id}")
    public void deleteEmploye(@PathVariable Long id) {
        employeService.deleteEmploye(id);
    }

    @GetMapping("/get/{id}")
    public Employe getEmploye(@PathVariable Long id) {
        return employeService.getEmploye(id);
    }

    @GetMapping("/getAll")
    public List<Employe> getAllEmployes() {
        return employeService.getAllEmployes();
    }
}
