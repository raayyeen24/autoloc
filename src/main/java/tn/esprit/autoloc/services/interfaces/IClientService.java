package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Client;
import java.util.List;

public interface IClientService {

    public Client ajouterClient(Client c);

    public Client updateClient(Client c);

    public void deleteClient(Long id);

    public Client getClient(Long id);

    public List<Client> getAllClients();
}
