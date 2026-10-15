package tn.esprit.autoloc.services.impls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.services.interfaces.IAgenceService;

import java.util.List;


@AllArgsConstructor
@Service

public class AgenceServiceImpl implements IAgenceService {

    AgenceRepository agenceRepository ;




    @Override
    public Agence ajouterAgence(Agence a) {
        return agenceRepository.save(a);
    }

    @Override
    public Agence updateAgence(Agence a) {
        return agenceRepository.save(a);
    }

    @Override
    public void deleteAgence(int id) {

    }

    @Override
    public void deleteAgence(long id) {
        agenceRepository.deleteById(id);
    }

    @Override
    public Agence getAgence(int id) {
        return agenceRepository.findById((long) id).get();
    }

    @Override
    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }
}