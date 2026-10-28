package tn.esprit.autoloc.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.entities.Client;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.services.interfaces.IClientService;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements IClientService {
    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public Client ajouterClient(Client entity) {
        if (entity.getIdClient() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Do not supply idClient when creating a client");
        }
        return clientRepository.save(entity);
    }

    @Override
    @Transactional
    public Client updateClient(Client entity) {
        getClient(entity.getIdClient());
        return clientRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        clientRepository.delete(getClient(id));
    }

    @Override
    public Client getClient(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A positive ID is required");
        }
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Client not found with ID " + id));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
