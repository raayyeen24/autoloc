package tn.esprit.autoloc.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.entities.Contrat;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.services.interfaces.IContratService;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ContratServiceImpl implements IContratService {
    private final ContratRepository contratRepository;

    @Override
    @Transactional
    public Contrat ajouterContrat(Contrat entity) {
        if (entity.getIdContrat() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Do not supply idContrat when creating a contrat");
        }
        return contratRepository.save(entity);
    }

    @Override
    @Transactional
    public Contrat updateContrat(Contrat entity) {
        getContrat(entity.getIdContrat());
        return contratRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteContrat(Long id) {
        contratRepository.delete(getContrat(id));
    }

    @Override
    public Contrat getContrat(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A positive ID is required");
        }
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Contrat not found with ID " + id));
    }

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }
}
