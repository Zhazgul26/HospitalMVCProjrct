package medical.repository;

import medical.entity.Patient;

import java.util.List;

public interface PatientRepository {
    Patient save(Patient patient);

    List<Patient> getAll();

    void deleteById(Long id);

    Patient getById(Long id);

    void update(Long id, Patient newPatient);
}
