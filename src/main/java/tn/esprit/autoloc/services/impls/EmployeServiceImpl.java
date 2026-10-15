
package tn.esprit.autoloc.services.impls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Employe;
import tn.esprit.autoloc.repository.EmployeRepository;
import tn.esprit.autoloc.services.interfaces.IEmployeService;

import java.util.List;

@AllArgsConstructor
@Service
public class EmployeServiceImpl implements IEmployeService {

    EmployeRepository employeRepository;

    @Override
    public Employe ajouterEmploye(Employe e) {
        return employeRepository.save(e);
    }

    @Override
    public Employe updateEmploye(Employe e) {
        return employeRepository.save(e);
    }

    @Override
    public void deleteEmploye(Long id) {
        employeRepository.deleteById(id);
    }

    @Override
    public Employe getEmploye(Long id) {
        return employeRepository.findById(id).get();
    }

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }
}
