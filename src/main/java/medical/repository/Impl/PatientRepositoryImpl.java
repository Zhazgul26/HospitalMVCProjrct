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
    public void save(Patient patient) {
        entityManager.persist(patient);

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
        return entityManager.find(Patient.class, id);
    }


    @Override
    public Patient update( Patient newPatient) {
        return entityManager.merge(newPatient);
    }


}

