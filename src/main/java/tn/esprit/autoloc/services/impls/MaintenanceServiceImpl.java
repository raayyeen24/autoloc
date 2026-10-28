package tn.esprit.autoloc.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.entities.Maintenance;
import tn.esprit.autoloc.repository.MaintenanceRepository;
import tn.esprit.autoloc.services.interfaces.IMaintenanceService;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MaintenanceServiceImpl implements IMaintenanceService {
    private final MaintenanceRepository maintenanceRepository;

    @Override
    @Transactional
    public Maintenance ajouterMaintenance(Maintenance entity) {
        if (entity.getIdMaintenance() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Do not supply idMaintenance when creating a maintenance");
        }
        return maintenanceRepository.save(entity);
    }

    @Override
    @Transactional
    public Maintenance updateMaintenance(Maintenance entity) {
        getMaintenance(entity.getIdMaintenance());
        return maintenanceRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteMaintenance(Long id) {
        maintenanceRepository.delete(getMaintenance(id));
    }

    @Override
    public Maintenance getMaintenance(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A positive ID is required");
        }
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Maintenance not found with ID " + id));
    }

    @Override
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }
}
