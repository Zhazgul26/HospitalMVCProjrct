package medical.repository.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.*;
import medical.repository.AppointmentRepository;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class AppointmentRepositoryImpl implements AppointmentRepository {
    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    public String save(Appointment appointment) {
        try {
            entityManager.merge(appointment);
            return "Successfully saved";
        } catch (
                HibernateException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @Override
    public Appointment getById(Long id) {
        try{
            Appointment appointment = entityManager.find(Appointment.class,id );
            return appointment;

        }catch (HibernateException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @Override
    public List<Appointment> getAll(Long id) {
        return entityManager.createQuery("select j from  Hospital d join d.appointments j where d.id=:id",Appointment.class).setParameter("id",id).getResultList();
    }

    @Override
    public void update(Long id, Appointment newAppointment) {
        boolean updated = false;
        try{
            Appointment oldAppointment = entityManager.find(Appointment.class, id);
            oldAppointment.setDate(newAppointment.getDate());
            oldAppointment.setDepartment(entityManager.find(Department.class,newAppointment.getDepartment()));
            oldAppointment.setDoctor(entityManager.find(Doctor.class,newAppointment.getDoctor()));
            oldAppointment.setPatient(entityManager.find(Patient.class,newAppointment.getPatient()));

        }catch (HibernateException exception){
        System.out.println(exception.getMessage());
    }
        System.out.println(updated ? "Appointment  is updated successfully" : "Appointment was not updated");

}


    @Override
    public void delete(Long id) {
        try {
            List<Hospital> hospitals = entityManager.createQuery("select h from Hospital h ", Hospital.class).getResultList();
            hospitals.forEach(s -> s.getAppointments().removeIf(a -> a.getId().equals(id)));
            entityManager.remove(entityManager.find(Appointment.class, id));
        }catch (HibernateException exception){
            System.out.println(exception.getMessage());

        }
    }
}