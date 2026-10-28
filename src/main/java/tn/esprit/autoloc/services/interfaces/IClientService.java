package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Client;
import java.util.List;

public interface IClientService {
    Client ajouterClient(Client entity);
    Client updateClient(Client entity);
    void deleteClient(Long id);
    Client getClient(Long id);
    List<Client> getAllClients();
}
