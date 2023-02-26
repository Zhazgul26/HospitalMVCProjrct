package medical.repository;

import medical.entity.Patient;

import java.io.IOException;
import java.util.List;

public interface PatientRepository {
    Patient save(Patient patient);

    List<Patient> getAll(Long id);

    void deleteById(Long id);

    Patient getById(Long id);

    void update(Long id, Patient newPatient);
    void assignPatient(Long appointmentId, Long patientId) throws IOException;

}
