package medical.repository;

import medical.entity.Department;
import medical.entity.Doctor;
import medical.entity.Hospital;

import java.util.List;

public interface DoctorRepository {

    Doctor save(Doctor doctor);

    List<Doctor> getAll();

    void deleteById(Long id);

    Doctor getById(Long id);

    void update(Long id, Doctor newDoctor);
}
