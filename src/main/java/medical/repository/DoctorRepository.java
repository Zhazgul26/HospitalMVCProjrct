package medical.repository;

import medical.entity.Department;
import medical.entity.Doctor;

import java.util.List;

public interface DoctorRepository {

    void save(Doctor doctor);

    List<Doctor> getAll(Long id);

    void deleteById(Long id);

    Doctor getById(Long id);

    Doctor update(Doctor newDoctor);
    List<Department> getAllDepartmentDoctorById(Long doctorId);


}
