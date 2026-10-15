
package tn.esprit.autoloc.services.impls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Contrat;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.services.interfaces.IContratService;

import java.util.List;

@AllArgsConstructor
@Service
public class ContratServiceImpl implements IContratService {

    ContratRepository contratRepository;

    @Override
    public Contrat ajouterContrat(Contrat c) {
        return contratRepository.save(c);
    }

    @Override
    public Contrat updateContrat(Contrat c) {
        return contratRepository.save(c);
    }

    @Override
    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public Contrat getContrat(Long id) {
        return contratRepository.findById(id).get();
    }

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }
}
