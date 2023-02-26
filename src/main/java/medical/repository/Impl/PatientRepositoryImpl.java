package medical.repository.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Appointment;
import medical.entity.Hospital;
import medical.entity.Patient;
import medical.repository.PatientRepository;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Patient save(Patient patient) {
        try {
            entityManager.persist(patient);
            return patient;
        } catch (
                HibernateException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @Override
    public List<Patient> getAll(Long id) {
       return entityManager.createQuery("select  p from Patient p where p.hospital.id = :id", Patient.class).setParameter("id",id).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        boolean delete = false;
        try {
            Patient patient = entityManager.find(Patient.class, id);
            entityManager.remove(entityManager.merge(patient));
            delete = true;
        }catch (HibernateException exception){
            System.out.println(exception.getMessage());
        }
        System.out.println(delete ? "Patient Deleted Successfully": "Patient was not deleted");
    }


    @Override
    public Patient getById(Long id) {
        try{
            Patient patient = entityManager.find(Patient.class,id );
            return patient;

        }catch (HibernateException exception){
            System.out.println(exception.getMessage());
        }

        return null;
    }

    @Override
    public void update(Long id, Patient newPatient) {
        boolean updated = false;
        try{
            Patient patient = entityManager.find(Patient.class, id);
            patient.setFirstName(newPatient.getFirstName());
            patient.setLastName(newPatient.getLastName());
            patient.setGender(newPatient.getGender());
            patient.setPhoneNumber(newPatient.getPhoneNumber());
            patient.setEmail(newPatient.getEmail());
            entityManager.merge(patient);
        }catch (HibernateException exception){
            System.out.println(exception.getMessage());
        }
        System.out.println(updated ? "Patient is updated successfully" : "Patient was not updated");

    }

    @Override
    public void assignPatient(Long appointmentId, Long patientId) throws IOException {
        Patient patient = entityManager.find(Patient.class, patientId);
        Appointment appointment = entityManager.find(Appointment.class, appointmentId);
        if (appointment.getPatient()!=null){
            for (Patient p: appointment.getHospital().getPatients()) {
                if (p.getId() == patientId){
                    throw new IOException("this is patient added");
                }
            }
        }
        patient.addAppointment(appointment);
        appointment.setPatient(patient);
        entityManager.merge(patient);
        entityManager.merge(appointment);
    }

}

