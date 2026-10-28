package tn.esprit.autoloc.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.entities.Paiement;
import tn.esprit.autoloc.repository.PaiementRepository;
import tn.esprit.autoloc.services.interfaces.IPaiementService;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PaiementServiceImpl implements IPaiementService {
    private final PaiementRepository paiementRepository;

    @Override
    @Transactional
    public Paiement ajouterPaiement(Paiement entity) {
        if (entity.getIdPaiement() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Do not supply idPaiement when creating a paiement");
        }
        return paiementRepository.save(entity);
    }

    @Override
    @Transactional
    public Paiement updatePaiement(Paiement entity) {
        getPaiement(entity.getIdPaiement());
        return paiementRepository.save(entity);
    }

    @Override
    @Transactional
    public void deletePaiement(Long id) {
        paiementRepository.delete(getPaiement(id));
    }

    @Override
    public Paiement getPaiement(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A positive ID is required");
        }
        return paiementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Paiement not found with ID " + id));
    }

    @Override
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }
}
