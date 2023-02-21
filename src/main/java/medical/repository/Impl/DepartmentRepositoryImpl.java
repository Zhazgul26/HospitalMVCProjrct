package medical.repository.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Department;
import medical.entity.Hospital;
import medical.repository.DepartmentRepository;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Department save(Department department) {
        try {
            entityManager.persist(department);
            return department;
        } catch (HibernateException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        try {
            departments = entityManager.createQuery("select c from Department c", Department.class).getResultList();

        } catch (HibernateException exception) {
            System.out.println(exception.getMessage());
        }
        return departments;
    }

    @Override
    public void deleteById(Long id) {
        boolean delete = false;
        try {
            Department department = entityManager.find(Department.class, id);
            entityManager.remove(entityManager.merge(department));
            delete = true;
        }catch (HibernateException exception){
            System.out.println(exception.getMessage());
        }
        System.out.println(delete ? "Department  Deleted Successfully": "Department was not deleted");
    }


    @Override
    public Department getById(Long id) {
        try{
            Department department = entityManager.find(Department.class,id );
            return department;

        }catch (HibernateException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
    @Override
    public void update(Long id, Department newDepartment) {
        boolean updated = false;
        try{
            Department department = entityManager.find(Department.class, id);
            department.setName(newDepartment.getName());
            department.setDoctors(newDepartment.getDoctors());
            department.setHospital(newDepartment.getHospital());
            entityManager.merge(department);
        }catch (HibernateException exception){
            System.out.println(exception.getMessage());
        }
        System.out.println(updated ? "Department is updated successfully" : "Department was not updated");

    }

    }



