package medical.service;

import medical.entity.Department;
import medical.entity.Hospital;

import java.util.List;

public interface DepartmentService {
    Department save(Department department);

    List<Department> getAll();

    void deleteById(Long id);

    Department getById(Long id);

    void update(Long id, Department newDepartment);
}