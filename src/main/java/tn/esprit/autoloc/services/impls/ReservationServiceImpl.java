package tn.esprit.autoloc.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.entities.Reservation;
import tn.esprit.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.services.interfaces.IReservationService;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReservationServiceImpl implements IReservationService {
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public Reservation ajouterReservation(Reservation entity) {
        if (entity.getIdReservation() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Do not supply idReservation when creating a reservation");
        }
        return reservationRepository.save(entity);
    }

    @Override
    @Transactional
    public Reservation updateReservation(Reservation entity) {
        getReservation(entity.getIdReservation());
        return reservationRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteReservation(Long id) {
        reservationRepository.delete(getReservation(id));
    }

    @Override
    public Reservation getReservation(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A positive ID is required");
        }
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Reservation not found with ID " + id));
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
