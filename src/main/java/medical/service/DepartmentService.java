package medical.service;

import medical.entity.Department;
import medical.entity.Hospital;

import java.io.IOException;
import java.util.List;

public interface DepartmentService {
    Department save(Long id,Department department);

    List<Department> getAll(Long id);

    void deleteById(Long id);

    Department getById(Long id);

    void update(Long id, Department newDepartment);

    void assignDepartment(Long doctorId, Long departmentId) throws IOException;
    void assignDepartmentToAppointment(Long appointmentId, Long departmentId) throws IOException;

}