package medical.service;

import medical.entity.Department;
import medical.entity.Doctor;
import medical.entity.Hospital;

import javax.print.Doc;
import java.io.IOException;
import java.util.List;

public interface DoctorService {
    void save(Long id,Doctor newDoctor);
    List<Doctor> getAll(Long id);



    Doctor getById(Long id);

    Doctor update(Long doctorId,Doctor doctor);
    void deleteById(Long id);

    List<Department> getAllDepartmentDoctorById(Long doctorId);



}
