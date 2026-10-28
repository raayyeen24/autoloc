package tn.esprit.autoloc.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.services.interfaces.IVehiculeService;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class VehiculeServiceImpl implements IVehiculeService {
    private final VehiculeRepository vehiculeRepository;

    @Override
    @Transactional
    public Vehicule ajouterVehicule(Vehicule entity) {
        if (entity.getIdVehicule() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Do not supply idVehicule when creating a vehicule");
        }
        return vehiculeRepository.save(entity);
    }

    @Override
    @Transactional
    public Vehicule updateVehicule(Vehicule entity) {
        getVehicule(entity.getIdVehicule());
        return vehiculeRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteVehicule(Long id) {
        vehiculeRepository.delete(getVehicule(id));
    }

    @Override
    public Vehicule getVehicule(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A positive ID is required");
        }
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Vehicule not found with ID " + id));
    }

    @Override
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }
}
