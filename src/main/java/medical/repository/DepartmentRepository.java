package medical.repository;

import medical.entity.Department;
import medical.entity.Doctor;

import java.io.IOException;
import java.util.List;

public interface DepartmentRepository {

    void save(Department department);
    List<Department> getAll(Long id);
    void deleteById(Long id);
    Department getById(Long id);
    void update ( Department newDepartment);

    void assignDoctor(Doctor doctor);

}

