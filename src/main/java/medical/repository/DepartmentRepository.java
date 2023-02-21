package medical.repository;

import medical.entity.Department;

import java.util.List;

public interface DepartmentRepository {

    Department save(Department department);
    List<Department> getAll();
    void deleteById(Long id);
    Department getById(Long id);
    void update (Long id, Department newDepartment);
    }

