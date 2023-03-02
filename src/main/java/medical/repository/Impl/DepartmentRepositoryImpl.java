package medical.repository.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Appointment;
import medical.entity.Department;
import medical.entity.Doctor;
import medical.repository.DepartmentRepository;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Department department) {

        entityManager.persist(department);
    }


    @Override
    public List<Department> getAll(Long id) {
        return entityManager.createQuery("select d from Department d where d.hospital.id=:id", Department.class).setParameter("id",id).getResultList();

    }

    @Override
    public void deleteById(Long id) {
        Department department = entityManager.find(Department.class, id);
        entityManager.remove(department);
    }

    @Override
    public Department getById(Long id) {
        return entityManager.find(Department.class,id);

    }

    @Override
    public void update(Department newDepartment) {
        entityManager.merge(newDepartment);
    }



    @Override
    public void assignDoctor(Doctor doctor) {
        entityManager.merge(doctor);

    }
}





