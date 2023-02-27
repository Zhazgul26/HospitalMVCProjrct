package medical.repository;

import medical.entity.Department;
import medical.entity.Doctor;

import java.io.IOException;
import java.util.List;

public interface DepartmentRepository {

    Department save(Department department);
    List<Department> getAll(Long id);
    void deleteById(Long id);
    Department getById(Long id);
    void update (Long id, Department newDepartment);

    void assignDepartment(Long doctorId, Long departmentId) throws IOException;
    void assignDepartmentToAppointment(Long appointmentId, Long departmentId) throws IOException;
    void assignDoctor(Doctor doctor);

}

