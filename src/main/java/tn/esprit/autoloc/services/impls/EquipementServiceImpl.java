package tn.esprit.autoloc.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.entities.Equipement;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.services.interfaces.IEquipementService;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class EquipementServiceImpl implements IEquipementService {
    private final EquipementRepository equipementRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    @Transactional
    public Equipement ajouterEquipement(Equipement entity) {
        if (entity.getIdEquipement() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Do not supply idEquipement when creating a equipement");
        }
        return equipementRepository.save(entity);
    }

    @Override
    @Transactional
    public Equipement updateEquipement(Equipement entity) {
        getEquipement(entity.getIdEquipement());
        return equipementRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteEquipement(Long id) {
        equipementRepository.delete(getEquipement(id));
    }

    @Override
    public Equipement getEquipement(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A positive ID is required");
        }
        return equipementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Equipement not found with ID " + id));
    }

    @Override
    public List<Equipement> getAllEquipements() {
        return equipementRepository.findAll();
    }

    // ----------- nouvelles méthodes business -----------
    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule veh = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Vehicule not found with ID " + idVehicule));
        Equipement eq = getEquipement(idEquipement);
        if (!veh.getEquipements().contains(eq)) {
            veh.getEquipements().add(eq);
        }
        return vehiculeRepository.save(veh);
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule veh = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Vehicule not found with ID " + idVehicule));
        Equipement eq = getEquipement(idEquipement);
        veh.getEquipements().remove(eq);
        return vehiculeRepository.save(veh);
    }
}
