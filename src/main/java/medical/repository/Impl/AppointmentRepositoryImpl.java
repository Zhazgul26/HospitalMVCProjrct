package medical.repository.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.*;
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
    public void save(Appointment appointment) {
        entityManager.persist(appointment);
    }

    @Override
    public Appointment getById(Long id) {
       return entityManager.find(Appointment.class, id);


    }



    @Override
    public List<Appointment> getAll(Long id) {
        return entityManager.createQuery("select j from  Hospital d join d.appointments j where d.id=:id",Appointment.class).setParameter("id",id).getResultList();
    }

    @Override
    public Appointment update(Appointment newAppointment) {
        entityManager.merge(newAppointment);
        return newAppointment;
    }


    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Appointment.class ,id));
    }
}