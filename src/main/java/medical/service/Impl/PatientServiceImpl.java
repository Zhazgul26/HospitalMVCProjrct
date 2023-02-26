package medical.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Hospital;
import medical.entity.Patient;
import medical.repository.HospitalRepository;
import medical.repository.PatientRepository;
import medical.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public Patient save(Long hospitalId, Patient patient) {
        Hospital hospital = hospitalRepository.getById(hospitalId);
        hospital.addPatient(patient);
        patient.setHospital(hospital);
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAll(Long id) {
        return patientRepository.getAll(id);
    }

    @Override
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient getById(Long id) {
        return patientRepository.getById(id);
    }

    @Override
    public void update(Long id, Patient newPatient) {
        patientRepository.update(id, newPatient);
    }

    @Override
    public void assignPatient(Long appointmentId, Long patientId) throws IOException {
        patientRepository.assignPatient(appointmentId, patientId);
    }

    public void validation(String phoneNumber) {
        int count = 0;
        if (phoneNumber.length() == 13
                && phoneNumber.charAt(0) == '+'
                && phoneNumber.charAt(1) == '9'
                && phoneNumber.charAt(2) == '9'
                && phoneNumber.charAt(3) == '6') {
            for (Character i : phoneNumber.toCharArray()) {
                if (count != 0) {
                    if (!Character.isDigit(i)) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "number not in valid range");
                    }
                }
                count++;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "number not found");
        }


    }
}
