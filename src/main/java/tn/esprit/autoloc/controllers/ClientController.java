package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Client;
import tn.esprit.autoloc.services.interfaces.IClientService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    private final IClientService clientService;

    @PostMapping("/ajouter")
    public Client ajouterClient(@Valid @RequestBody Client c) {
        return clientService.ajouterClient(c);
    }

    @PutMapping("/modifier")
    public Client updateClient(@Valid @RequestBody Client c) {
        return clientService.updateClient(c);
    }

    @DeleteMapping("/supprimer/{id}")
    public void deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/get/{id}")
    public Client getClient(@PathVariable("id") Long id) {
        return clientService.getClient(id);
    }

    @GetMapping("/getAll")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }
}
