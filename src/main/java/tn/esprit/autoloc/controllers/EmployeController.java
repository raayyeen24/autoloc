package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Employe;
import tn.esprit.autoloc.services.interfaces.IEmployeService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employe")
public class EmployeController {

    private final IEmployeService employeService;

    @PostMapping("/ajouter")
    public Employe ajouterEmploye(@Valid @RequestBody Employe e) {
        return employeService.ajouterEmploye(e);
    }

    @PutMapping("/modifier")
    public Employe updateEmploye(@Valid @RequestBody Employe e) {
        return employeService.updateEmploye(e);
    }

    @DeleteMapping("/supprimer/{id}")
    public void deleteEmploye(@PathVariable("id") Long id) {
        employeService.deleteEmploye(id);
    }

    @GetMapping("/get/{id}")
    public Employe getEmploye(@PathVariable("id") Long id) {
        return employeService.getEmploye(id);
    }

    @GetMapping("/getAll")
    public List<Employe> getAllEmployes() {
        return employeService.getAllEmployes();
    }
}
