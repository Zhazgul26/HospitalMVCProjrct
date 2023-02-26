package medical.repository.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Appointment;
import medical.entity.Department;
import medical.entity.Doctor;
import medical.entity.Hospital;
import medical.repository.DoctorRepository;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class DoctorRepositoryImpl implements DoctorRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Doctor save(Doctor doctor) {
        try {
            entityManager.persist(doctor);
            return doctor;
        } catch (
                HibernateException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctor> getAll(Long id) {

            return entityManager.createQuery("select d from Doctor d where d.hospital.id = :id", Doctor.class).setParameter("id",id).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        boolean delete = false;
        try {
            Doctor doctor = entityManager.find(Doctor.class, id);
            entityManager.remove(entityManager.merge(doctor));
            delete = true;
        }catch (HibernateException exception){
            System.out.println(exception.getMessage());
        }
        System.out.println(delete ? "Doctor Deleted Successfully": "Doctor was not deleted");
    }


    @Override
    public Doctor getById(Long id) {
        try{
            Doctor doctor = entityManager.find(Doctor.class,id );
            return doctor;

        }catch (HibernateException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @Override
    public void update(Long id, Doctor newDoctor) {
        boolean updated = false;
        try{
            Doctor doctor = entityManager.find(Doctor.class, id);
            doctor.setFirstName(newDoctor.getFirstName());
            doctor.setLastName(newDoctor.getLastName());
            doctor.setEmail(newDoctor.getEmail());
            doctor.setPosition(newDoctor.getPosition());
            entityManager.merge(doctor);
        }catch (HibernateException exception){
            System.out.println(exception.getMessage());
        }
        System.out.println(updated ? "Doctor is updated successfully" : "Doctor was not updated");

    }

    @Override
    public void assignDoctor(Long appointmentId, Long doctorId) throws IOException {
        Doctor doctor = entityManager.find(Doctor.class, doctorId);
        Appointment appointment = entityManager.find(Appointment.class, appointmentId);
        if(appointment.getDoctor()!=null){
            for (Doctor d: appointment.getHospital().getDoctors()) {
                if(d.getId() == doctorId){
                    throw new IOException("this is have signed");
                }
            }
        }
        doctor.addAppointments(appointment);
        appointment.setDoctor(doctor);
        entityManager.merge(doctor);
        entityManager.merge(appointment);
    }

}
