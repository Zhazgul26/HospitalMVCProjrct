package medical.repository.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Appointment;
import medical.repository.AppointmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class AppointmentRepositoryImpl implements AppointmentRepository {
    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    public Appointment save(Appointment appointment) {
        return null;
    }

    @Override
    public List<Appointment> getAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Appointment getById(Long id) {
        return null;
    }

    @Override
    public void update(Long id, Appointment newAppointment) {

    }
}