package medical.service;

import medical.entity.Doctor;
import medical.entity.Hospital;

import javax.print.Doc;
import java.util.List;

public interface DoctorService {
    Doctor save(Doctor doctor);
    List<Doctor> getAll();

    void deleteById(Long id);

    Doctor getById(Long id);

    void update(Long id, Doctor newDoctor);
}
