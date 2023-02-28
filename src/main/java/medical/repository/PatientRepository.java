package medical.repository;

import medical.entity.Patient;

import java.io.IOException;
import java.util.List;

public interface PatientRepository {
    void save(Patient patient);

    List<Patient> getAll(Long id);

    void deleteById(Long id);

    Patient getById(Long id);

    Patient update( Patient newPatient);

}
