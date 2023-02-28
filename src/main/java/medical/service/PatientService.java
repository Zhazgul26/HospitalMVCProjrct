package medical.service;

import medical.entity.Hospital;
import medical.entity.Patient;

import java.io.IOException;
import java.util.List;

public interface PatientService {
    void save(Long id,Patient patient);
    List<Patient> getAll(Long id);



    Patient getById(Long id);

   Patient update(Long id, Patient newPatient);
    void delete(Long id);

}
