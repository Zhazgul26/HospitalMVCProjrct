package medical.repository.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Hospital;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor

public class HospitalRepository implements medical.repository.HospitalRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Hospital save(Hospital hospital) {
        try {
            entityManager.persist(hospital);
            return hospital;
        } catch (HibernateException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @Override
    public List<Hospital> getAll() {
        List<Hospital> hospitals = new ArrayList<>();
        try {
            hospitals = entityManager.createQuery("select c from Hospital c", Hospital.class).getResultList();

        } catch (HibernateException exception) {
            System.out.println(exception.getMessage());
        }
        return hospitals;
    }

    @Override
    public void deleteById(Long id) {
        boolean delete = false;
        try {
            Hospital hospital = entityManager.find(Hospital.class, id);
            entityManager.remove(entityManager.merge(hospital));
            delete = true;
        }catch (HibernateException exception){
            System.out.println(exception.getMessage());
        }
        System.out.println(delete ? "Hospital Deleted Successfully": "Hospital was not deleted");
    }

    @Override
    public Hospital getById(Long id) {

        try{
            Hospital hospital = entityManager.find(Hospital.class,id );
                return hospital;

        }catch (HibernateException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @Override
    public void update(Long id, Hospital newHospital) {
        boolean updated = false;
        try{
            Hospital hospital = entityManager.find(Hospital.class, id);
            hospital.setName(newHospital.getName());
            hospital.setAddress(newHospital.getAddress());
            hospital.setImage(newHospital.getImage());
            entityManager.merge(hospital);
        }catch (HibernateException exception){
            System.out.println(exception.getMessage());
        }
        System.out.println(updated ? "Hospital is updated successfully" : "Hospital was not updated");

    }
}