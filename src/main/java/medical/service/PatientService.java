package medical.service;

import medical.entity.Hospital;
import medical.entity.Patient;

import java.io.IOException;
import java.util.List;

public interface PatientService {
    Patient save(Long hospitalId,Patient patient);
    List<Patient> getAll(Long id);

    void deleteById(Long id);

    Patient getById(Long id);

    void update(Long id, Patient newPatient);

    void assignPatient(Long appointmentId, Long patientId) throws IOException;

}
