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
    public void save(Hospital hospital) {
        entityManager.persist(hospital);
    }

    @Override
    public List<Hospital> getAll() {
        return entityManager.createQuery("select h from Hospital h", Hospital.class).getResultList();

    }

    @Override
    public void deleteById(Long id) {
        Hospital hospital = getById(id);
        entityManager.remove(entityManager.contains(hospital) ? hospital : entityManager.merge(hospital));
    }

    @Override
    public Hospital getById(Long id) {

        return entityManager.find(Hospital.class, id);

    }

    @Override
    public void update(Long id, Hospital newHospital) {
        Hospital hospital = getById(id);
        hospital.setImage(newHospital.getImage());
        hospital.setName(newHospital.getName());
        hospital.setAddress(newHospital.getAddress());
        entityManager.merge(newHospital);

    }
}