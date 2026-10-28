package tn.esprit.autoloc.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.services.interfaces.IAgenceService;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AgenceServiceImpl implements IAgenceService {
    private final AgenceRepository agenceRepository;

    @Override
    @Transactional
    public Agence ajouterAgence(Agence entity) {
        if (entity.getIdAgence() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Do not supply idAgence when creating a agence");
        }
        return agenceRepository.save(entity);
    }

    @Override
    @Transactional
    public Agence updateAgence(Agence entity) {
        getAgence(entity.getIdAgence());
        return agenceRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteAgence(Long id) {
        agenceRepository.delete(getAgence(id));
    }

    @Override
    public Agence getAgence(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A positive ID is required");
        }
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Agence not found with ID " + id));
    }

    @Override
    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }
}
