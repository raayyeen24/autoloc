package tn.esprit.autoloc.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.entities.Employe;
import tn.esprit.autoloc.repository.EmployeRepository;
import tn.esprit.autoloc.services.interfaces.IEmployeService;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class EmployeServiceImpl implements IEmployeService {
    private final EmployeRepository employeRepository;

    @Override
    @Transactional
    public Employe ajouterEmploye(Employe entity) {
        if (entity.getIdEmploye() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Do not supply idEmploye when creating a employe");
        }
        return employeRepository.save(entity);
    }

    @Override
    @Transactional
    public Employe updateEmploye(Employe entity) {
        getEmploye(entity.getIdEmploye());
        return employeRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteEmploye(Long id) {
        employeRepository.delete(getEmploye(id));
    }

    @Override
    public Employe getEmploye(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A positive ID is required");
        }
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Employe not found with ID " + id));
    }

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }
}
