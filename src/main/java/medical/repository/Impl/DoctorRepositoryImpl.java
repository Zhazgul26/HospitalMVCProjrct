package medical.repository.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Department;
import medical.entity.Doctor;
import medical.repository.DoctorRepository;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor

public class DoctorRepositoryImpl implements DoctorRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Doctor doctor) {
      entityManager.persist(doctor);
    }

    @Override
    public List<Doctor> getAll(Long id) {

            return entityManager.createQuery("select d from Doctor d where d.hospital.id = :id", Doctor.class).setParameter("id",id).getResultList();
    }

    @Override
    public void deleteById(Long id) {
         entityManager.remove(entityManager.find(Doctor.class,id));
        }




    @Override
    public Doctor getById(Long id) {
 return entityManager.find(Doctor.class ,id);
    }

    @Override
    public Doctor update(Doctor newDoctor) {

        return newDoctor;
    }

    @Override
    public List<Department> getAllDepartmentDoctorById(Long doctorId) {
        return entityManager.createQuery("select d from Department d join d.doctors doctor where doctor.id=:id", Department.class).setParameter("id",doctorId).getResultList();
    }


}
