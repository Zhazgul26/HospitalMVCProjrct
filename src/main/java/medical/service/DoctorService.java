package medical.service;

import medical.entity.Doctor;
import medical.entity.Hospital;

import javax.print.Doc;
import java.io.IOException;
import java.util.List;

public interface DoctorService {
    Doctor save(Long id,Doctor doctor);
    List<Doctor> getAll(Long id);

    void deleteById(Long id);

    Doctor getById(Long id);

    void update(Long id, Doctor newDoctor);

    void assignDoctor(Long appointmentId, Long doctorId) throws IOException;

}
