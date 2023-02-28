package medical.service;

import medical.entity.Department;
import medical.entity.Doctor;
import medical.entity.Hospital;

import java.io.IOException;
import java.util.List;

public interface DepartmentService {
    void save(Long id,Department department);

    List<Department> getAll(Long id);


    Department getById(Long id);

    void update(Long departmentId,Department department);
    void deleteById(Long id);
    public void assignDoctor(Long doctorId, Doctor doctor) ;

    }